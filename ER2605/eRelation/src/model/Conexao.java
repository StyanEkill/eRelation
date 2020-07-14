package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Conexao {
	int port = 8080;
	String ip = "192.168.1.10";
	
	public int getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}

	public String conexao(JSONObject valores, int acao, String servlet) {
		String apnd = "", parametros = "acao="+acao+"&valores="+valores.toString(), linha = parametros;
		try {
			URL url = new URL("http://"+this.getIp()+":"+this.getPort()+"/WebServlet/"+servlet);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(parametros);

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((linha = br.readLine()) != null)
				apnd += linha;

			return apnd;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
