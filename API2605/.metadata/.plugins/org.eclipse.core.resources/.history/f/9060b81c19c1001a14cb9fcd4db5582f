package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConnectionDB;
import model.Empresa;

public class EmpresasDAO {
	private Connection con;
	private PreparedStatement ps;
	private Empresa emp;
	private List<Empresa> lEmpresa;

	public boolean create(Empresa emp) throws SQLException {
		String sql = "INSERT INTO empresas VALUES (?,?,?,?,?,?,?,MD5(?))";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, emp.getCnpj());
		ps.setString(2, emp.getNomeEmpresa());
		ps.setString(3, emp.getEndereco());
		ps.setString(4, emp.getTelefone());
		ps.setBlob(5, emp.getFotoPerfil());
		ps.setString(6, emp.getDescricao());
		ps.setString(7, emp.getTipo());
		ps.setString(8, emp.getSenha());
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public Empresa read(String cnpj) throws SQLException {
		String sql = "SELECT * FROM Empresas WHERE cnpj = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			emp = new Empresa();
			emp.setCnpj(cnpj);
			emp.setNomeEmpresa(rs.getString("nome_empresa"));
			emp.setEndereco(rs.getString("endereco"));
			emp.setTelefone(rs.getString("telefone"));
			emp.setFotoPerfil(rs.getBinaryStream("foto_perfil"));
			emp.setDescricao(rs.getString("descricao"));
			emp.setTipo(rs.getString("tipo"));
			emp.setSenha(rs.getString("senha"));
			con.close();
			return emp;
		} else {
			con.close();
			return null;
		}
	}

	public boolean update(Empresa emp) throws SQLException {
		String sql = "UPDATE Empresas SET nome_empresa = ?, endereco = ?, telefone = ?, foto_perfil = ?, descricao = ?, tipo = ? WHERE cnpj = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);

		ps.setString(1, emp.getNomeEmpresa());
		ps.setString(2, emp.getEndereco());
		ps.setString(3, emp.getTelefone());
		ps.setBlob(4, emp.getFotoPerfil());
		ps.setString(5, emp.getDescricao());
		ps.setString(6, emp.getTipo());
		ps.setString(7, emp.getCnpj());

		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public boolean delete(String cnpj) throws SQLException {
		String sql = "DELETE FROM Empresas WHERE cnpj = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public List<Empresa> list(String cnpj, int limit, String search, String type) throws SQLException {
		String sql = "SELECT * FROM Empresas WHERE (cnpj != ? ";
		
		if(type != null) {
			sql+="and tipo = '"+type+"'";
		}
		sql+=") ";
		if(search != null) {
			sql += "and (cnpj like '%"+search+"%' or nome_empresa like '%"+search+"%' or descricao like '%"+search+"%') ";
		}
		sql+=" ORDER BY 'cnpj' DESC LIMIT ?, 5;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setString(1, cnpj);
		ps.setInt(2, limit);
		
		ResultSet rs = ps.executeQuery();
		lEmpresa = new ArrayList<Empresa>();
		while (rs.next()) {
			emp = new Empresa();
			emp.setCnpj(rs.getString("cnpj"));
			emp.setNomeEmpresa(rs.getString("nome_empresa"));
			emp.setEndereco(rs.getString("endereco"));
			emp.setTelefone(rs.getString("telefone"));
			emp.setFotoPerfil(rs.getBinaryStream("foto_perfil"));
			emp.setDescricao(rs.getString("descricao"));
			emp.setTipo(rs.getString("tipo"));
			lEmpresa.add(emp);
		}
		con.close();
		return lEmpresa;
	}

	public boolean login(String cnpj, String senha) throws SQLException {
		String sql = "SELECT * FROM Empresas WHERE cnpj = ? AND senha = MD5(?);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ps.setString(2, senha);
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
