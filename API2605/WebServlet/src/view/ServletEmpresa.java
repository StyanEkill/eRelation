package view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import control.AgendaDAO;
import control.CaixaEntradaDAO;
import control.ConnectionSocketDAO;
import control.EmpresasDAO;
import control.PedidosDAO;
import control.SocioDAO;
import model.Agenda;
import model.CaixaEntrada;
import model.ConnectionSocket;
import model.Empresa;
import model.Socio;

@WebServlet("/svEmpresa")
public class ServletEmpresa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Empresa emp;
	private Agenda agd;
	private Socio soc;
	private CaixaEntrada ce;
	private ConnectionSocket cs;

	private EmpresasDAO empDAO;
	private AgendaDAO agdDAO;
	private SocioDAO socDAO;
	private CaixaEntradaDAO ceDAO;
	private ConnectionSocketDAO csDAO;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int acao = Integer.parseInt(req.getParameter("acao"));
		JSONObject parameters = new JSONObject(req.getParameter("valores"));

		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();
		JSONArray resultArray = new JSONArray();

		init();
		System.out.println("svEmpresa");
		try {
			switch (acao) {
			// LOGIN
			case 1:
				if (parameters.has("cnpj") && parameters.has("senha")) {
					emp.setCnpj(parameters.getString("cnpj"));
					emp.setSenha(parameters.getString("senha"));

					if (empDAO.login(emp.getCnpj(), emp.getSenha())) {
						result.put("cnpj", emp.getCnpj());
					} else {
						result.put("cod", 300);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// CADASTRA EMPRESA
			case 2:
				if (parameters.has("cnpj") && parameters.has("endereco") && parameters.has("empresa")
						&& parameters.has("senha") && parameters.has("telefone") && parameters.has("tipo")) {
					if (emp.isCNPJ(parameters.getString("cnpj"))) {
						emp.setCnpj(parameters.getString("cnpj"));
						emp.setEndereco(parameters.getString("endereco"));
						emp.setNomeEmpresa(parameters.getString("empresa"));
						emp.setSenha(parameters.getString("senha"));
						emp.setTelefone(parameters.getString("telefone"));
						emp.setTipo(parameters.getString("tipo"));

						if (parameters.has("descricao"))
							emp.setDescricao(parameters.getString("descricao"));
						else
							emp.setDescricao(null);

						if (parameters.has("foto_perfil")) {
							emp.setFotoPerfil(emp.parseImage(parameters.getString("foto_perfil")));
						} else
							emp.setFotoPerfil(null);

						if (empDAO.create(emp)) {
							result.put("cod", 101);
						} else {
							result.put("cod", 102);
						}
					} else {
						result.put("cod", 500);
					}
				} else {
					result.put("cod", 100);
				}
				break;

			// BUSCA EMPRESA POR CNPJ
			case 3:
				if (parameters.has("cnpj")) {
					emp.setCnpj(parameters.getString("cnpj"));

					Empresa empresa = empDAO.read(emp.getCnpj());
					result.put("cnpj", empresa.getCnpj());
					result.put("empresa", empresa.getNomeEmpresa());
					result.put("endereco", empresa.getEndereco());
					result.put("telefone", empresa.getTelefone());
					result.put("descricao", empresa.getDescricao());
					result.put("tipo", empresa.getTipo());

				} else {
					result.put("cod", 100);
				}
				break;

			// UPDATE EMPRESAS
			case 4:
				if (parameters.has("cnpj") && parameters.has("endereco") && parameters.has("empresa")
						&& parameters.has("telefone") && parameters.has("tipo")) {
					emp.setCnpj(parameters.getString("cnpj"));
					emp.setEndereco(parameters.getString("endereco"));
					emp.setNomeEmpresa(parameters.getString("empresa"));
					emp.setTelefone(parameters.getString("telefone"));
					emp.setTipo(parameters.getString("tipo"));
					emp.setDescricao(parameters.getString("descricao"));

					if (parameters.has("foto_perfil"))
						emp.setFotoPerfil(emp.parseImage(parameters.getString("foto_perfil")));
					else if (empDAO.read(parameters.getString("cnpj")).getFotoPerfil() != null)
						emp.setFotoPerfil(empDAO.read(parameters.getString("cnpj")).getFotoPerfil());
					else
						emp.setFotoPerfil(null);

					if (parameters.has("descricao"))
						emp.setDescricao(parameters.getString("descricao"));
					else
						emp.setDescricao(null);

					if (empDAO.update(emp)) {
						result.put("cod", 101);
					} else {
						result.put("cod", 102);
					}

				} else {
					result.put("cod", 100);
				}
				break;

			// LISTA EMPRESAS
			case 5:
				List<Empresa> lEmp;

				String tipo = null;
				String search = null;

				if (parameters.has("search"))
					search = parameters.getString("search");
				if (parameters.has("type"))
					tipo = parameters.getString("type");

				lEmp = empDAO.list(parameters.getString("cnpj"), parameters.getInt("limit"), search, tipo);

				for (Empresa empresa : lEmp) {
					result = new JSONObject();
					result.put("cnpj", empresa.getCnpj());
					result.put("empresa", empresa.getNomeEmpresa());
					result.put("telefone", empresa.getTelefone());
					result.put("descricao", empresa.getDescricao());
					result.put("senha", empresa.getSenha());
					result.put("tipo", empresa.getTipo());
					resultArray.put(result);
				}
				break;
			// RESUMO PERFIL
			case 6:
				if (parameters.has("cnpj")) {
					PedidosDAO pedDAO = new PedidosDAO();
					int[] infoPedidos = pedDAO.infoPedidos(parameters.getString("cnpj"));
					int[] infoSocios = socDAO.infoSocio(parameters.getString("cnpj"));

					result.put("pedidos_pendentes", infoPedidos[0]);
					result.put("pedidos_realizados", infoPedidos[1]);
					result.put("total_pedidos", infoPedidos[2]);
					result.put("qnt_solicitacoes", infoSocios[0]);
					result.put("qnt_socios", infoSocios[1]);
				} else {
					result.put("cod", 100);
				}
				break;
			/*------------------------------------------------------------------ SOCIO/SOLICITAÇÃO ------------------------------------------------------------*/
			// ENVIAR SOLICITACAO
			case 7:
				if (parameters.has("cnpj_dest") && parameters.has("cnpj_origem")) {
					soc.setCnpjOrigem(parameters.getString("cnpj_origem"));
					soc.setCnpjDest(parameters.getString("cnpj_dest"));
					if (socDAO.create(soc)) {
						result.put("cod", 101);

						ce.setCnpjDest(parameters.getString("cnpj_dest"));
						ce.setCnpjOrigem(parameters.getString("cnpj_origem"));

						String empresa = empDAO.read(parameters.getString("cnpj_origem")).getNomeEmpresa();
						ce.setMensagem("A empresa " + empresa
								+ " deseja fechar uma nova parceria com você. Vá para as solicitações.");
						ce.setDestino(false);
						ceDAO.create(ce);

						cs = new ConnectionSocket("SOLICITAÇÃO", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;

			// BUSCA SOCIO
			case 8:
				if (parameters.has("cnpj_origem") && parameters.has("cnpj_dest")) {
					soc.setCnpjOrigem(parameters.getString("cnpj_origem"));
					soc.setCnpjDest(parameters.getString("cnpj_dest"));

					Socio socio = socDAO.read(soc.getCnpjOrigem(), soc.getCnpjDest());
					if (socio != null) {
						result.put("cnpj_origem", socio.getCnpjOrigem());
						result.put("cnpj_dest", socio.getCnpjDest());
						result.put("data", socio.getDataContratacao());
					} else {
						result.put("cod", 205);
					}
				} else {
					result.put("cod", 100);
				}
				break;

			// ACEITA SOLICITACAO
			case 9:
				if (parameters.has("cnpj_dest") && parameters.has("cnpj_origem")) {
					soc.setCnpjOrigem(parameters.getString("cnpj_origem"));
					soc.setCnpjDest(parameters.getString("cnpj_dest"));

					if (socDAO.update(soc.getCnpjOrigem(), soc.getCnpjDest())) {
						result.put("cod", 101);
						String empresa = empDAO.read(parameters.getString("cnpj_origem")).getNomeEmpresa();
						ce.setMensagem("A empresa " + empresa + " aceitou sua solicitação. Agora vocês são parceiros.");

						ce.setCnpjDest(soc.getCnpjOrigem());
						ce.setCnpjOrigem(soc.getCnpjDest());
						ce.setDestino(false);
						ceDAO.create(ce);

						cs = new ConnectionSocket("SOLICITAÇÃO", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// RECUSA OU CANCELA SOLICITACAO
			case 10:
				if (parameters.has("cnpj_dest") && parameters.has("cnpj_origem")) {
					soc.setCnpjOrigem(parameters.getString("cnpj_origem"));
					soc.setCnpjDest(parameters.getString("cnpj_dest"));

					Socio socio = socDAO.read(parameters.getString("cnpj_origem"), parameters.getString("cnpj_dest"));
					if (socDAO.delete(soc.getCnpjOrigem(), soc.getCnpjDest())) {
						result.put("cod", 101);

						ce.setCnpjDest(parameters.getString("cnpj_dest"));
						ce.setCnpjOrigem(parameters.getString("cnpj_origem"));

						String empresa = empDAO.read(parameters.getString("cnpj_origem")).getNomeEmpresa();

						cs = new ConnectionSocket("SOLICITAÇÃO", "solicitacao", ce);
						if (socio.getDataContratacao() != null) {
							ce.setMensagem("A empresa " + empresa
									+ " quebrou o quebrou o vinculo que havia com a sua empresa");
							ce.setDestino(true);
							csDAO.enviaCaixaEntrada(ce, cs);
							ceDAO.create(ce);
						} else if (socio.getCnpjDest().equals(parameters.getString("cnpj_origem"))) {
							ce.setMensagem("A empresa " + empresa + " recusou a sua proposta de contrato.");
							ce.setDestino(false);
							csDAO.enviaCaixaEntrada(ce, cs);
							ceDAO.create(ce);
						}
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// LISTA SOCIOS
			case 11:
				if (parameters.has("cnpj_origem")) {
					soc.setCnpjOrigem(parameters.getString("cnpj_origem"));

					List<Socio> lSoc = socDAO.list(soc.getCnpjOrigem());
					for (Socio socio : lSoc) {
						result = new JSONObject();
						result.put("cnpj_origem", socio.getCnpjOrigem());
						result.put("cnpj_dest", socio.getCnpjDest());
						result.put("data", socio.getDataContratacao());
						resultArray.put(result);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// LISTA SOLICITACOES
			case 12:
				if (parameters.has("cnpj_origem")) {
					soc.setCnpjOrigem(parameters.getString("cnpj_origem"));

					List<Socio> lSoc = socDAO.listSoliD(soc.getCnpjOrigem());
					for (Socio socio : lSoc) {
						result = new JSONObject();
						result.put("cnpj_origem", socio.getCnpjOrigem());
						result.put("cnpj_dest", socio.getCnpjDest());
						result.put("data", socio.getDataContratacao());
						resultArray.put(result);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			/*------------------------------------------------------------------ AGENDA ------------------------------------------------------------*/

			// MARCA COMPROMISSO
			case 13:
				if (parameters.has("cnpj_origem") && parameters.has("cnpj_dest") && parameters.has("titulo")
						&& parameters.has("descricao") && parameters.has("data") && parameters.has("horario")) {
					agd.setCnpjOrigem(parameters.getString("cnpj_origem"));
					agd.setCnpjDest(parameters.getString("cnpj_dest"));
					agd.setTitulo(parameters.getString("titulo"));
					agd.setDescricao(parameters.getString("descricao"));
					agd.setData(parameters.getString("data"));
					agd.setHorario(parameters.getString("horario"));

					if (agdDAO.create(agd)) {
						result.put("cod", 101);
						ce.setCnpjDest(parameters.getString("cnpj_dest"));
						ce.setCnpjOrigem(parameters.getString("cnpj_origem"));

						String empresa = empDAO.read(parameters.getString("cnpj_origem")).getNomeEmpresa();

						ce.setMensagem(
								"A empresa " + empresa + " deseja marcar um evento com você. Vá para a sua genda.");
						ce.setDestino(false);
						ceDAO.create(ce);

						cs = new ConnectionSocket("AGENDA", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// CONFIRMA REUNIAO
			case 14:
				if (parameters.has("id")) {
					agd = agdDAO.read(parameters.getInt("id"));
					agd.setAgendado(true);

					if (agdDAO.update(agd)) {
						result.put("cod", 101);

						agd = agdDAO.read(parameters.getInt("id"));
						ce.setCnpjOrigem(agd.getCnpjDest());
						ce.setCnpjDest(agd.getCnpjOrigem());

						String empresa = empDAO.read(ce.getCnpjOrigem()).getNomeEmpresa();
						ce.setMensagem("A empresa " + empresa + " confirmou sua presença no evento " + agd.getTitulo());
						ce.setDestino(false);

						cs = new ConnectionSocket("AGENDA", "agenda", ce);
						csDAO.enviaCaixaEntrada(ce, cs);
						ceDAO.create(ce);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;

			// DELETA COMPROMISSO
			case 15:
				if (parameters.has("id") && parameters.has("cnpj")) {
					agd = agdDAO.read(parameters.getInt("id"));

					if (agdDAO.delete(parameters.getInt("id"))) {
						result.put("cod", 101);

						String empresa = empDAO.read(parameters.getString("cnpj")).getNomeEmpresa();

						if (agd.getCnpjOrigem().equals(parameters.getString("cnpj")) && agd.getAgendado()) {
							ce.setCnpjOrigem(agd.getCnpjOrigem());
							ce.setCnpjDest(agd.getCnpjDest());
							ce.setMensagem("A empresa " + empresa + " cancelou o evento: " + agd.getTitulo() + ".");
							ce.setDestino(false);
							ceDAO.create(ce);
						} else if (!agd.getCnpjOrigem().equals(parameters.getString("cnpj")) && !agd.getAgendado()) {
							ce.setCnpjOrigem(agd.getCnpjDest());
							ce.setCnpjDest(agd.getCnpjOrigem());
							ce.setMensagem("A empresa " + empresa + " recusou o evento: " + agd.getTitulo() + ".");
							ce.setDestino(false);
							ceDAO.create(ce);
						} else if (!agd.getCnpjOrigem().equals(parameters.getString("cnpj"))) {
							ce.setCnpjOrigem(agd.getCnpjDest());
							ce.setCnpjDest(agd.getCnpjOrigem());
							ce.setMensagem("A empresa " + empresa + " não poderá mais comparecer ao evento: "
									+ agd.getTitulo() + ".");
							ce.setDestino(false);
							ceDAO.create(ce);
						}

						cs = new ConnectionSocket("AGENDA", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);

					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;

			// UPDATE COMPROMISSO
			case 16:
				if (parameters.has("titulo") && parameters.has("descricao") && parameters.has("data")
						&& parameters.has("horario") && parameters.has("id") && parameters.has("agendado")) {
					agd.setTitulo(parameters.getString("titulo"));
					agd.setDescricao(parameters.getString("descricao"));
					agd.setData(parameters.getString("data"));
					agd.setHorario(parameters.getString("horario"));
					agd.setIdCompromisso(parameters.getInt("id"));
					agd.setAgendado(parameters.getBoolean("agendado"));

					if (agdDAO.update(agd)) {
						result.put("cod", 101);

						agd = agdDAO.read(parameters.getInt("id"));

						if (agd.getCnpjOrigem().equals(parameters.getString("cnpj")) && agd.getAgendado()) {
							String empresa = empDAO.read(agd.getCnpjOrigem()).getNomeEmpresa();
							ce.setCnpjOrigem(agd.getCnpjOrigem());
							ce.setCnpjDest(agd.getCnpjDest());
							ce.setMensagem("A empresa " + empresa + " mudou: " + agd.getTitulo() + ".");
							ce.setDestino(false);
							ceDAO.create(ce);
						} else if (!agd.getCnpjOrigem().equals(parameters.getString("cnpj"))) {
							String empresa = empDAO.read(agd.getCnpjDest()).getNomeEmpresa();
							ce.setCnpjOrigem(agd.getCnpjDest());
							ce.setCnpjDest(agd.getCnpjOrigem());
							ce.setMensagem("A empresa " + empresa + " recusou o evento: " + agd.getTitulo() + ".");
							ce.setDestino(false);
							ceDAO.create(ce);
						}

						cs = new ConnectionSocket("AGENDA", "caixa_entrada", ce);
						csDAO.enviaCaixaEntrada(ce, cs);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// LISTA AGENDA
			case 17:
				if (parameters.has("cnpj_origem")) {
					agd.setCnpjOrigem(parameters.getString("cnpj_origem"));

					for (Agenda agenda : agdDAO.list(agd.getCnpjOrigem())) {
						result = new JSONObject();
						result.put("id", agenda.getIdCompromisso());
						result.put("cnpj_origem", agenda.getCnpjOrigem());
						result.put("cnpj_dest", agenda.getCnpjDest());
						result.put("data", agenda.getData());
						result.put("titulo", agenda.getTitulo());
						result.put("descricao", agenda.getDescricao());
						result.put("horario", agenda.getHorario());
						result.put("agendado", agenda.getAgendado());

						if (agenda.getCnpjDest().equals("cnpj_origem")) {
							emp = empDAO.read(agenda.getCnpjOrigem());
						} else {
							emp = empDAO.read(agenda.getCnpjDest());
						}

						result.put("empresa", emp.getNomeEmpresa());
						resultArray.put(result);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// VERIFICA SE HÁ ALGUMA MENSAGEM NA CAIXA DE ENTRADA NÃO LIDA
			case 18:
				if (parameters.has("cnpj")) {
					result.put("verify", ceDAO.read(parameters.getString("cnpj")));
				} else {
					result.put("cod", 100);
				}
				break;
			// UPDATE CAIXA ENTRADA
			case 19:
				if (parameters.has("id")) {
					if (ceDAO.update(parameters.getInt("id"))) {
						result.put("cod", 101);
					} else {
						result.put("cod", 102);
					}
				} else {
					result.put("cod", 100);
				}
				break;
			// LISTA CAIXA DE ENTRADA
			case 20:
				if (parameters.has("cnpj")) {
					List<CaixaEntrada> lCaixaEntrada = ceDAO.list(parameters.getString("cnpj"));
					for (CaixaEntrada caixaentrada : lCaixaEntrada) {
						if (!caixaentrada.getStatus()) {
							ceDAO.update(caixaentrada.getIdCaixaEntrada());
						}
						result = new JSONObject();
						result.put("id", caixaentrada.getIdCaixaEntrada());
						result.put("mensagem", caixaentrada.getMensagem());
						result.put("data", caixaentrada.getData());
						result.put("horario", caixaentrada.getHorario());
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
		else 
			out.write(resultArray.toString());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedImage buffer;
		empDAO = new EmpresasDAO();

		try {
			Empresa empresa = empDAO.read(req.getParameter("cnpj"));

			if (empresa.getFotoPerfil() != null) {
				buffer = ImageIO.read(empresa.getFotoPerfil());
			} else {
				buffer = ImageIO.read(
						new File(req.getServletContext().getRealPath("img") + File.separator + "industria.jpg"));
			}
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ImageIO.write(buffer, "jpg", b);

			byte[] imgArray = b.toByteArray();
			ServletOutputStream sos = resp.getOutputStream();
			sos.write(imgArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		emp = new Empresa();
		agd = new Agenda();
		soc = new Socio();
		ce = new CaixaEntrada();

		empDAO = new EmpresasDAO();
		agdDAO = new AgendaDAO();
		socDAO = new SocioDAO();
		ceDAO = new CaixaEntradaDAO();
		csDAO = new ConnectionSocketDAO();
	}
}
