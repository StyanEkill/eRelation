package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ConnectionDB;
import model.Mensagem;

public class MensagemDAO {
	private Connection con;
	private PreparedStatement ps;
	private Mensagem msg;
	private List<Mensagem> lMensagem;

	public boolean create(Mensagem msg) throws SQLException {
		String sql = "INSERT INTO Mensagem VALUES (default,?,?,?,curdate(),curtime(), false);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setString(1, msg.getCnpjDest());
		ps.setString(2, msg.getCnpjOrigem());
		ps.setString(3, msg.getMensagem());
		
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public boolean update(int id) throws SQLException {
		String sql = "UPDATE Mensagem SET status = true WHERE id_mensagem = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}
	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM Mensagem WHERE id_mensagem = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}
	public List<Date> list(String cnpjOrigem, String cnpjDest) throws SQLException {
		String sql = "SELECT data_postagem FROM Mensagem where cnpj_dest = ? AND cnpj_origem = ? OR cnpj_dest = ? AND cnpj_origem = ? GROUP BY data_postagem;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjDest);
		ps.setString(2, cnpjOrigem);
		ps.setString(4, cnpjDest);
		ps.setString(3, cnpjOrigem);
		
		ResultSet rs = ps.executeQuery();
		List<Date> lData = new ArrayList<Date>();
		
		while (rs.next()) {
			lData.add(rs.getDate("data_postagem"));
		}
		con.close();
		return lData;
	}
	public List<Mensagem> list(String cnpjOrigem, String cnpjDest, String data) throws SQLException {
		String sql = "SELECT * FROM Mensagem where cnpj_dest = ? AND cnpj_origem = ?  AND data_postagem = ? OR cnpj_dest = ? AND cnpj_origem = ? AND data_postagem = ? AND status = true;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjDest);
		ps.setString(2, cnpjOrigem);
		ps.setString(3, data);
		ps.setString(5, cnpjDest);
		ps.setString(4, cnpjOrigem);
		ps.setString(6, data);
		ResultSet rs = ps.executeQuery();
		lMensagem = new ArrayList<Mensagem>();
		while (rs.next()) {
			msg = new Mensagem();
			msg.setCnpjDest(rs.getString("cnpj_dest"));
			msg.setIdMensagem(rs.getInt("id_mensagem"));
			msg.setMensagem(rs.getString("mensagem"));
			msg.setCnpjOrigem(rs.getString("cnpj_origem"));
			msg.setDataPostagem(msg.parseUtilSQL(rs.getDate("data_postagem")));
			msg.setHorarioPostagem(rs.getString("horario_postagem"));
			msg.setStatus(rs.getBoolean("status"));
			lMensagem.add(msg);
		}
		con.close();
		return lMensagem;
	}

	public List<Mensagem> list(String cnpj) throws SQLException {
		String sql = "SELECT DISTINCT cnpj_origem as cnpj from mensagem where cnpj_dest = ? UNION SELECT DISTINCT cnpj_dest as cnpj from mensagem where cnpj_origem = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ps.setString(2, cnpj);
		ResultSet rs = ps.executeQuery();
		lMensagem = new ArrayList<Mensagem>();
		while (rs.next()) {
			msg = new Mensagem();
			msg.setCnpjDest(rs.getString("cnpj"));
			lMensagem.add(msg);
		}
		con.close();
		return lMensagem;
	}
	public Mensagem read(String cnpjDest, String cnpjOrigem) throws SQLException {
		String sql = "SELECT * from mensagem where cnpj_dest = ? AND cnpj_origem = ? OR cnpj_origem = ? AND cnpj_dest = ? ORDER BY `id_mensagem`  DESC LIMIT 1";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjDest);
		ps.setString(2, cnpjOrigem);
		ps.setString(3, cnpjDest);
		ps.setString(4, cnpjOrigem);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			msg = new Mensagem();
			msg.setIdMensagem(rs.getInt("id_mensagem"));
			msg.setMensagem(rs.getString("mensagem"));
			msg.setCnpjOrigem(rs.getString("cnpj_origem"));
			msg.setCnpjDest(rs.getString("cnpj_dest"));
			msg.setDataPostagem(msg.parseUtilSQL(rs.getDate("data_postagem")));
			msg.setHorarioPostagem(rs.getString("horario_postagem"));
			msg.setStatus(rs.getBoolean("status"));
			con.close();
			return msg;
		} else {
			con.close();
			return null;
		}
	}
	public List<Mensagem> readQntFalse(String cnpjDest, String cnpjOrigem) throws SQLException {
		String sql = "SELECT * from mensagem where cnpj_dest = ? AND cnpj_origem = ? AND status = false";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjDest);
		ps.setString(2, cnpjOrigem);
		ResultSet rs = ps.executeQuery();
		lMensagem = new ArrayList<Mensagem>();
		while (rs.next()) {
			msg = new Mensagem();
			msg.setIdMensagem(rs.getInt("id_mensagem"));
			msg.setMensagem(rs.getString("mensagem"));
			msg.setCnpjOrigem(rs.getString("cnpj_origem"));
			msg.setCnpjDest(rs.getString("cnpj_dest"));
			msg.setDataPostagem(msg.parseUtilSQL(rs.getDate("data_postagem")));
			msg.setHorarioPostagem(rs.getString("horario_postagem"));
			System.out.println(rs.getBoolean("status"));
			msg.setStatus(rs.getBoolean("status"));
			lMensagem.add(msg);
		} 
		con.close();
		return lMensagem;
	}
	public boolean readMsg(String cnpj) throws SQLException {
		String sql = "SELECT * from mensagem where cnpj_dest = ? AND status = false";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}
}
