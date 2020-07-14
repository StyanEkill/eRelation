package model;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.mysql.fabric.xmlrpc.Client;

import view.SocketCliente;

public class ConnectionSocket {
	private String title, location;
	private CaixaEntrada ce;
	private Session session;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public CaixaEntrada getCe() {
		return ce;
	}
	public void setCe(CaixaEntrada ce) {
		this.ce = ce;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public ConnectionSocket(){}
	public ConnectionSocket(String title, String location, CaixaEntrada ce) {
		this.title = title;
		this.location = location;
		this.ce = ce;
		this.session = socket();
	}

	public Session socket() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			//parametros.replaceAll("[.-/--]
			String uri = "ws://localhost:8080/WebServlet/socket?user=SISTEMA/";
			Session session = container.connectToServer(SocketCliente.class, URI.create(uri));
			return session;
		} catch (DeploymentException | IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
