package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConnectionDB;
import model.Socio;

public class SocioDAO {
	private Connection con;
	private PreparedStatement ps;
	private Socio soc;
	private List<Socio> lSocio;

	public boolean create(Socio soc) throws SQLException {
		String sql = "INSERT INTO Socios VALUES (?,?,null);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setString(1, soc.getCnpjOrigem());
		ps.setString(2, soc.getCnpjDest());
		
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public boolean update(String cnpjOrigem, String cnpjDest) throws SQLException {
		String sql = "UPDATE socios SET data_contratacao = curdate() WHERE cnpj_dest = ? AND cnpj_origem = ? OR cnpj_dest = ? AND cnpj_origem = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjDest);
		ps.setString(2, cnpjOrigem);
		ps.setString(4, cnpjDest);
		ps.setString(3, cnpjOrigem);
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}
	
	public boolean delete(String cnpjOrigem, String cnpjDest) throws SQLException {
		String sql = "DELETE FROM Socios WHERE cnpj_origem = ? AND cnpj_dest = ? OR cnpj_origem = ? AND cnpj_dest = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjOrigem);
		ps.setString(2, cnpjDest);
		ps.setString(4, cnpjOrigem);
		ps.setString(3, cnpjDest);
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public List<Socio> list(String cnpjOrigem) throws SQLException {
		String sql = "SELECT * FROM Socios where cnpj_dest = ? AND data_contratacao is not null OR cnpj_origem = ? AND data_contratacao is not null;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjOrigem);
		ps.setString(2, cnpjOrigem);
		ResultSet rs = ps.executeQuery();
		lSocio = new ArrayList<Socio>();
		while (rs.next()) {
			soc = new Socio();
			soc.setCnpjDest(rs.getString("cnpj_dest"));
			soc.setCnpjOrigem(rs.getString("cnpj_origem"));
			soc.setDataContratacao(rs.getDate("data_contratacao"));
			lSocio.add(soc);
		}
		con.close();
		return lSocio;
	}

	public List<Socio> listSoliD(String cnpjOrigem) throws SQLException {
		String sql = "SELECT * FROM Socios where cnpj_dest = ? AND data_contratacao is null OR cnpj_origem = ? AND data_contratacao is null;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjOrigem);
		ps.setString(2, cnpjOrigem);
		ResultSet rs = ps.executeQuery();
		lSocio = new ArrayList<Socio>();
		while (rs.next()) {
			soc = new Socio();
			soc.setCnpjDest(rs.getString("cnpj_dest"));
			soc.setCnpjOrigem(rs.getString("cnpj_origem"));
			soc.setDataContratacao(rs.getDate("data_contratacao"));
			lSocio.add(soc);
		}
		con.close();
		return lSocio;
	}

	public Socio read(String cnpjOrigem,String cnpjDest) throws SQLException {
		String sql = "SELECT * FROM Socios where cnpj_dest = ? AND cnpj_origem = ? OR cnpj_origem = ? AND cnpj_dest = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpjOrigem);
		ps.setString(2, cnpjDest);
		ps.setString(3, cnpjOrigem);
		ps.setString(4, cnpjDest);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			soc = new Socio();
			soc.setCnpjDest(rs.getString("cnpj_dest"));
			soc.setCnpjOrigem(rs.getString("cnpj_origem"));
			soc.setDataContratacao(rs.getDate("data_contratacao"));
			con.close();
			return soc;
		} else {
			con.close();
			return null;
		}
	}
	public int[] infoSocio(String cnpj) throws SQLException {
		String sql ="SELECT SUM(case when (cnpj_dest = ? and data_contratacao is null) then 1 else 0 end) as qnt_solicitacoes, "+ 
				"SUM(case when ((cnpj_dest = ? or cnpj_origem = ?) and data_contratacao is not null) then 1 else 0 end) as qnt_socios  FROM `socios`";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ps.setString(2, cnpj);
		ps.setString(3, cnpj);

		ResultSet rs = ps.executeQuery();

		int[] qnt = new int[2];
		if (rs.next()) {
			qnt[0] = rs.getInt("qnt_solicitacoes");
			qnt[1] = rs.getInt("qnt_socios");
			return qnt;
		} else {
			return qnt;
		}
	}

}
