package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import control.MensagemDAO;
import model.CaixaEntrada;
import model.ConnectionSocket;
import model.Empresa;
import model.Mensagem;

@WebServlet("/svMensagem")
public class ServletMensagem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Empresa emp;
	private Mensagem msg;
	private CaixaEntrada ce;
	private ConnectionSocket cs;

	private EmpresasDAO empDAO;
	private MensagemDAO msgDAO = new MensagemDAO();
	private CaixaEntradaDAO ceDAO;
	private ConnectionSocketDAO csDAO;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int acao = Integer.parseInt(req.getParameter("acao"));
		JSONObject parameters = new JSONObject(req.getParameter("valores"));

		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();
		JSONArray resultArray = new JSONArray();

		init();
		System.out.println("svMensagem "+acao);
		try {
			switch (acao) {
			// ENVIA MENSAGEM
			case 1:
				if (parameters.has("cnpj_dest") && parameters.has("cnpj_origem") && parameters.has("msg")) {
					msg.setCnpjDest(parameters.getString("cnpj_dest"));
					msg.setCnpjOrigem(parameters.getString("cnpj_origem"));
					msg.setMensagem(parameters.getString("msg"));

					if (msgDAO.read(parameters.getString("cnpj_dest"), parameters.getString("cnpj_origem")) == null) {
						ce.setCnpjDest(parameters.getString("cnpj_dest"));
						ce.setCnpjOrigem(parameters.getString("cnpj_origem"));

						String empresa = empDAO.read(parameters.getString("cnpj_origem")).getNomeEmpresa();

						ce.setMensagem("A empresa " + empresa
								+ " iniciou uma nova conversa com você. Vá para as suas conversas.");
						ce.setDestino(false);
						ceDAO.create(ce);

						cs = new ConnectionSocket("MENSAGEM", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);
					}
					if (msgDAO.create(msg)) {
						result.put("cod", 101);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", parameters.toString());
				}
				break;

			// VERIFICA SE HÁ ALGUMA MENSAGEM NÃO LIDA
			case 2:
				if (parameters.has("cnpj")) {
					result.put("verify", msgDAO.readMsg(parameters.getString("cnpj")));
				} else {
					result.put("cod", 100);
				}
				break;
			// UPDATE MENSAGEM
			case 3:
				if (parameters.has("cnpj_dest") && parameters.has("cnpj_origem")) {
					List<Mensagem> nLidas = msgDAO.readQntFalse(parameters.getString("cnpj_origem"),
							parameters.getString("cnpj_dest"));
					for (Mensagem mensagem : nLidas) {
						if (msgDAO.update(mensagem.getIdMensagem())) {
							result.put("cod", 101);
						} else {
							result.put("cod", 102);
						}
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// LISTA MENSAGENS
			case 4:
				if (parameters.has("cnpj_dest") && parameters.has("cnpj_origem")) {
					msg.setCnpjDest(parameters.getString("cnpj_dest"));
					msg.setCnpjOrigem(parameters.getString("cnpj_origem"));

					List<Mensagem> nLidas = msgDAO.readQntFalse(msg.getCnpjOrigem(), msg.getCnpjDest());
					JSONArray array = new JSONArray();
					for (Mensagem mensagem : nLidas) {
						JSONObject objt = new JSONObject();
						objt.put("id", mensagem.getIdMensagem());
						objt.put("cnpj_origem", mensagem.getCnpjOrigem());
						objt.put("horario", mensagem.parseTime(mensagem.getHorarioPostagem()));
						objt.put("data", mensagem.parseDate(mensagem.getDataPostagem()));
						objt.put("message", mensagem.getMensagem());
						objt.put("status", mensagem.getStatus());
						array.put(objt);
					}
					result.put("nao_lidas", array);

					List<Date> lData = msgDAO.list(msg.getCnpjOrigem(), msg.getCnpjDest());
					JSONArray arr = new JSONArray();
					for (Date data : lData) {
						JSONObject objts = new JSONObject();
						array = new JSONArray();
						List<Mensagem> lMsg = msgDAO.list(msg.getCnpjOrigem(), msg.getCnpjDest(),
								String.valueOf(msg.parseUtilSQL(data)));

						for (Mensagem mensagem : lMsg) {
							JSONObject objt = new JSONObject();
							objt.put("id", mensagem.getIdMensagem());
							objt.put("cnpj_origem", mensagem.getCnpjOrigem());
							objt.put("horario", mensagem.parseTime(mensagem.getHorarioPostagem()));
							objt.put("data", mensagem.parseDate(mensagem.getDataPostagem()));
							objt.put("message", mensagem.getMensagem());
							objt.put("status", mensagem.getStatus());
							array.put(objt);
						}
						objts.put("log", array);
						objts.put("dia", msg.parseDate(data));

						arr.put(objts);
					}
					result.put("lidas", arr);
				} else {
					result.put("cod", 100);
				}

				break;
			// LISTA CONVERSAS
			case 5:
				if (parameters.has("cnpj")) {
					List<Mensagem> lConversas = msgDAO.list(parameters.getString("cnpj"));
					for (Mensagem mensagem : lConversas) {
						Mensagem msg = msgDAO.read(mensagem.getCnpjDest(), parameters.getString("cnpj"));
						emp = empDAO.read(mensagem.getCnpjDest());

						result = new JSONObject();
						result.put("empresa", emp.getNomeEmpresa());
						result.put("cnpj_conversa", mensagem.getCnpjDest());
						result.put("cnpj", msg.getCnpjOrigem());
						result.put("mensagem", msg.getMensagem());
						result.put("data", msg.getDataPostagem());
						result.put("horario", msg.parseTime(msg.getHorarioPostagem()));
						result.put("status", msg.getStatus());

						int qnt = msgDAO.readQntFalse(parameters.getString("cnpj"), mensagem.getCnpjDest()).size();
						result.put("number_message", qnt);
						resultArray.put(result);
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
		msg = new Mensagem();
		ce = new CaixaEntrada();
		cs = new ConnectionSocket();

		empDAO = new EmpresasDAO();
		msgDAO = new MensagemDAO();
		ceDAO = new CaixaEntradaDAO();
		csDAO = new ConnectionSocketDAO();
	}
}
