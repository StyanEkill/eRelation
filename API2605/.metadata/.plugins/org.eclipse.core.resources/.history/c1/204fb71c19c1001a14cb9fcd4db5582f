package control;

import java.io.IOException;

import org.json.JSONObject;

import model.CaixaEntrada;
import model.ConnectionSocket;

public class ConnectionSocketDAO {
	private JSONObject js;
	public void enviaCaixaEntrada(CaixaEntrada ce, ConnectionSocket cs) throws IOException {
		js = new JSONObject();
		js.put("action", "caixa_entrada");
		js.put("title", cs.getTitle());
		js.put("userFrom", ce.getCnpjDest());
		js.put("href", cs.getLocation());
		js.put("message",ce.getMensagem());
		cs.getSession().getBasicRemote().sendText(js.toString());
	}
}
