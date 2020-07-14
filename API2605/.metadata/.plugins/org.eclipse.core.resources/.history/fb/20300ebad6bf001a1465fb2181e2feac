package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.ConnectionDB;
import model.Agenda;

public class AgendaDAO {
	private Connection con;
	private PreparedStatement ps;
	private Agenda agd;
	private List<Agenda> lAgenda;

	public boolean create(Agenda agd) throws SQLException {
		String sql = "INSERT INTO Agenda VALUES (default,?,?,?,?,?,?,false)";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, agd.getCnpjOrigem());
		ps.setString(2, agd.getCnpjDest());
		ps.setString(3, agd.getData());
		ps.setString(4, agd.getHorario());
		ps.setString(5, agd.getDescricao());
		ps.setString(6, agd.getTitulo());

		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public Agenda read(int id) throws SQLException {
		String sql = "SELECT * FROM Agenda WHERE id_compromisso = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			agd = new Agenda();
			agd.setIdCompromisso(id);
			agd.setCnpjOrigem(rs.getString("cnpj_origem"));
			agd.setCnpjDest(rs.getString("cnpj_dest"));
			agd.setData(rs.getString("data"));
			agd.setHorario(rs.getString("horario"));
			agd.setDescricao(rs.getString("descricao"));
			agd.setTitulo(rs.getString("titulo"));
			agd.setAgendado(rs.getBoolean("agendado"));
		}
		con.close();
		return agd;
	}

	public Agenda count(String cnpj) throws SQLException {
		String sql = "SELECT count(*) as qnt_realzado FROM Agenda WHERE cnpj_origem = \"23.114.360/0001-10\" or cnpj_dest = \"23.114.360/0001-10\" and agendado = true and data < curdate()\r\n" + 
				"inner join\r\n" + 
				"SELECT count(*) as qnt_pendentes  FROM Agenda WHERE cnpj_origem = \"23.114.360/0001-10\" or cnpj_dest = \"23.114.360/0001-10\" and agendado = true and data > curdate()\r\n" + 
				"union\r\n" + 
				"SELECT count(*) as total FROM Agenda WHERE cnpj_origem = \"23.114.360/0001-10\" or cnpj_dest = \"23.114.360/0001-10\" and agendado = true";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ps.setString(2, cnpj);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			agd = new Agenda();
			agd.setIdCompromisso(rs.getInt("id"));
			agd.setCnpjOrigem(rs.getString("cnpj_origem"));
			agd.setCnpjDest(rs.getString("cnpj_dest"));
			agd.setData(rs.getString("data"));
			agd.setHorario(rs.getString("horario"));
			agd.setDescricao(rs.getString("descricao"));
			agd.setTitulo(rs.getString("titulo"));
			agd.setAgendado(rs.getBoolean("agendado"));
		}
		con.close();
		return agd;
	}
	

	public boolean update(Agenda agd) throws SQLException {
		String sql = "UPDATE Agenda SET data = ?, horario = ?, descricao = ?, titulo = ?, agendado = ? WHERE id_compromisso = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setString(1, agd.getData());
		ps.setString(2, agd.getHorario());
		ps.setString(3, agd.getDescricao());
		ps.setString(4, agd.getTitulo());
		ps.setBoolean(5, agd.getAgendado());
		ps.setInt(6, agd.getIdCompromisso());

		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM Agenda WHERE id_compromisso = ?;";
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

	public List<Agenda> list(String cnpj) throws SQLException {
		String sql = "SELECT * FROM Agenda WHERE cnpj_origem = ? OR cnpj_dest = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ps.setString(2, cnpj);
		ResultSet rs = ps.executeQuery();
		lAgenda = new ArrayList<Agenda>();
		while (rs.next()) {
			agd = new Agenda();
			agd.setIdCompromisso(rs.getInt("id_compromisso"));
			agd.setCnpjOrigem(rs.getString("cnpj_origem"));
			agd.setCnpjDest(rs.getString("cnpj_dest"));
			agd.setData(rs.getString("data"));
			agd.setHorario(rs.getString("horario"));
			agd.setDescricao(rs.getString("descricao"));
			agd.setTitulo(rs.getString("titulo"));
			agd.setAgendado(rs.getBoolean("agendado"));
			lAgenda.add(agd);
		}
		con.close();
		return lAgenda;
	}

}
