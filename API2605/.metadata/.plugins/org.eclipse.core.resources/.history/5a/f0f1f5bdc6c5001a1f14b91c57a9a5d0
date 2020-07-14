package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import control.CaixaEntradaDAO;
import control.ConnectionSocketDAO;
import control.EmpresasDAO;
import control.ItemDAO;
import control.PedidosDAO;
import control.ProdutosDAO;
import model.CaixaEntrada;
import model.ConnectionSocket;
import model.Empresa;
import model.Item;
import model.Pedidos;
import model.Produtos;

@WebServlet("/svPedidos")
public class ServletPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Empresa emp;
	private Item it;
	private Produtos prod;
	private Pedidos ped;
	private CaixaEntrada ce;
	private ConnectionSocket cs;

	private EmpresasDAO empDAO;
	private ItemDAO itDAO;
	private ProdutosDAO prodDAO;
	private PedidosDAO pedDAO;
	private CaixaEntradaDAO ceDAO;
	private ConnectionSocketDAO csDAO;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int acao = Integer.parseInt(req.getParameter("acao"));
		JSONObject parameters = new JSONObject(req.getParameter("valores"));

		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();
		JSONArray resultArray = new JSONArray();

		init();
		System.out.println("svPedido");
		try {
			switch (acao) {

			// CRIA PEDIDO
			case 1:
				if (parameters.has("cnpj_origem") && parameters.has("cnpj_dest") && parameters.has("itens")) {
					JSONArray arr = parameters.getJSONArray("itens");
					ped.setCnpjCliente(parameters.getString("cnpj_origem"));
					ped.setCnpjFornecedor(parameters.getString("cnpj_dest"));
					if (pedDAO.create(ped)) {
						List<Item> lItem = new ArrayList<>();
						for (int i = 0; i < arr.length(); i++) {
							JSONObject obj = arr.getJSONObject(i);
							it = new Item();
							it.setIdProdutos(obj.getInt("id_prod"));
							it.setQnt(obj.getInt("qnt"));
							lItem.add(it);
						}

						if (itDAO.create(lItem)) {
							result.put("cod", 101);

							String empresa = empDAO.read(parameters.getString("cnpj_origem")).getNomeEmpresa();

							ce.setCnpjOrigem(parameters.getString("cnpj_origem"));
							ce.setCnpjDest(parameters.getString("cnpj_dest"));
							ce.setMensagem("A empresa " + empresa + " realizou um novo pedido.");
							ce.setDestino(false);
							ceDAO.create(ce);

							cs = new ConnectionSocket("PEDIDOS", "caixa_entrada", ce);
							csDAO.enviaCaixaEntrada(ce, cs);
						} else {
							result.put("cod", 104);
						}
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// UPDATE PEDIDO
			case 2:
				if (parameters.has("id") && parameters.has("status")) {
					ped.setIdPedidos(parameters.getInt("id"));

					Pedidos pedidos = pedDAO.read(parameters.getInt("id"));

					if (parameters.getInt("status") == 4) {
						Calendar c = Calendar.getInstance();
						Date data = c.getTime();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						ped.setDataFinal(sdf.format(data));
						ped.setDataPrevisao(pedidos.getDataPrevisao());
					} else if (parameters.getInt("status") == 1) {
						String empresa = empDAO.read(pedidos.getCnpjFornecedor()).getNomeEmpresa();

						ce.setCnpjOrigem(pedidos.getCnpjFornecedor());
						ce.setCnpjDest(pedidos.getCnpjCliente());
						ce.setMensagem("A empresa " + empresa + " definiu uma data de entrega para o pedido nº "
								+ parameters.getInt("id") + ".");
						ce.setDestino(false);
						ceDAO.create(ce);

						cs = new ConnectionSocket("PEDIDOS", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);

						ped.setDataPrevisao(parameters.getString("data_prev"));
					} else {
						ped.setDataFinal(null);
						ped.setDataPrevisao(pedidos.getDataPrevisao());
					}

					ped.setStatus(parameters.getInt("status"));
					if (pedDAO.update(ped)) {
						result.put("cod", 101);
					} else {
						result.put("cod", 102);
					}

				} else {
					result.put("cod", 100);
				}
				break;
			// CANCELA PEDIDO
			case 3:
				if (parameters.has("id")) {
					ped = pedDAO.read(parameters.getInt("id"));

					if (pedDAO.delete(parameters.getInt("id"))) {
						result.put("cod", 101);
						String empresa = empDAO.read(ped.getCnpjCliente()).getNomeEmpresa();

						ce.setCnpjOrigem(ped.getCnpjCliente());
						ce.setCnpjDest(ped.getCnpjFornecedor());
						ce.setMensagem(
								"A empresa " + empresa + " cancelou o pedido nº" + parameters.getInt("id") + ".");
						ce.setDestino(false);
						ceDAO.create(ce);

						cs = new ConnectionSocket("PEDIDO", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// LISTA PEDIDOS RECEBIDOS
			case 4:
				if (parameters.has("cnpj")) {
					List<Pedidos> lPedidos = pedDAO.listPedidosRecebidos(parameters.getString("cnpj"));

					for (Pedidos pedidos : lPedidos) {
						List<Item> lIt = itDAO.list(pedidos.getIdPedidos());
						JSONArray array = new JSONArray();
						for (Item itens : lIt) {
							JSONObject obj = new JSONObject();
							prod = prodDAO.read(itens.getIdProdutos());
							obj.put("produto", prod.getNome());
							obj.put("id_prod", prod.getIdProdutos());
							obj.put("qnt", itens.getQnt());
							obj.put("valor", itens.getValor());
							array.put(obj);
						}
						result.put("itens", array);

						emp = empDAO.read(pedidos.getCnpjFornecedor());
						result.put("fornecedor", emp.getNomeEmpresa());

						emp = empDAO.read(pedidos.getCnpjCliente());
						result.put("cliente", emp.getNomeEmpresa());

						result.put("endereco", emp.getEndereco());
						result.put("data_pedido", pedidos.getDataPedido());
						result.put("data_previsao", pedidos.getDataPrevisao());
						result.put("data_final", pedidos.getDataFinal());
						result.put("status", pedidos.getStatus());
						result.put("id", pedidos.getIdPedidos());
						resultArray.put(result);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// LISTA PEDIDOS FEITOS
			case 5:
				if (parameters.has("cnpj")) {
					List<Pedidos> lPedidos = pedDAO.listMeusPedidos(parameters.getString("cnpj"));

					for (Pedidos pedidos : lPedidos) {
						List<Item> lIt = itDAO.list(pedidos.getIdPedidos());
						JSONObject objt = new JSONObject();

						JSONArray array = new JSONArray();

						for (Item itens : lIt) {
							JSONObject obj = new JSONObject();
							prod = prodDAO.read(itens.getIdProdutos());
							obj.put("produto", prod.getNome());
							obj.put("id_prod", prod.getIdProdutos());
							obj.put("qnt", itens.getQnt());
							obj.put("valor", itens.getValor());
							array.put(obj);
						}
						objt.put("itens", array);

						emp = empDAO.read(pedidos.getCnpjCliente());
						objt.put("cliente", emp.getNomeEmpresa());

						emp = empDAO.read(pedidos.getCnpjFornecedor());
						objt.put("fornecedor", emp.getNomeEmpresa());

						objt.put("endereco", emp.getEndereco());
						objt.put("data_pedido", pedidos.getDataPedido());
						objt.put("data_previsao", pedidos.getDataPrevisao());
						objt.put("data_final", pedidos.getDataFinal());
						objt.put("status", pedidos.getStatus());
						objt.put("id", pedidos.getIdPedidos());

						resultArray.put(objt);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			default:
				result.put("cod", 4000);
				break;
			}

		} catch (JSONException | SQLException e) {
			e.printStackTrace();
			result.put("cod", 103);
		}

		if (resultArray.isEmpty() && !result.isEmpty())
			out.write(result.toString());
		else if (!resultArray.isEmpty())
			out.write(resultArray.toString());
	}

	public void init() {
		emp = new Empresa();
		it = new Item();
		prod = new Produtos();
		ped = new Pedidos();
		ce = new CaixaEntrada();

		empDAO = new EmpresasDAO();
		pedDAO = new PedidosDAO();
		itDAO = new ItemDAO();
		prodDAO = new ProdutosDAO();
		ceDAO = new CaixaEntradaDAO();
		csDAO = new ConnectionSocketDAO();
	}
}
