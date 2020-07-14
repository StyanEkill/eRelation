package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CaixaEntrada;
import model.ConnectionDB;

public class CaixaEntradaDAO {
	private Connection con;
	private PreparedStatement ps;
	private CaixaEntrada cent;
	private List<CaixaEntrada> lCaixaEntrada;
	
	public boolean create(CaixaEntrada cv) throws SQLException {
		String sql = "INSERT INTO caixa_entrada VALUES (default,?,?,?,curdate(),curtime(),?, false);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setString(1, cv.getCnpjOrigem());
		ps.setString(2, cv.getCnpjDest());
		ps.setString(3, cv.getMensagem());
		ps.setBoolean(4, cv.getDestino());
		
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}
	
	public List<CaixaEntrada> list(String cnpj) throws SQLException {
		String sql = "SELECT * FROM caixa_entrada WHERE cnpj_dest = ? OR cnpj_origem = ? AND destino = true ORDER BY id_caixa_entrada DESC;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ps.setString(2, cnpj);
		ResultSet rs = ps.executeQuery();
		lCaixaEntrada = new ArrayList<CaixaEntrada>();
		while (rs.next()) {
			cent = new CaixaEntrada();
			cent.setIdCaixaEntrada(rs.getInt("id_caixa_entrada"));
			cent.setCnpjOrigem(rs.getString("cnpj_origem"));
			cent.setCnpjDest(rs.getString("cnpj_dest"));
			cent.setData(rs.getString("data"));
			cent.setHorario(rs.getString("horario"));
			cent.setMensagem(rs.getString("mensagem"));
			cent.setDestino(rs.getBoolean("destino"));
			cent.setStatus(rs.getBoolean("status"));
			lCaixaEntrada.add(cent);
		}
		con.close();
		return lCaixaEntrada;
	}
	public boolean update(int id) throws SQLException {
		String sql = "UPDATE caixa_entrada SET status = true WHERE id_caixa_entrada = ?;";
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

	public boolean read(String cnpj) throws SQLException {
		String sql = "SELECT * from caixa_entrada where cnpj_dest = ? AND status = false";
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
