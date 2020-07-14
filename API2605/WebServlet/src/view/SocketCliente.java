package view;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class SocketCliente {
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Conectado: " + session.getBasicRemote());
	}

	@OnMessage
	public void processMessage(String message) {
		System.out.println("Recebe msg: " + message);
	}

	@OnError
	public void processError(Throwable t) {
		t.printStackTrace();
	}

	@OnClose
	public void onClose(Session session) {
	}
}
