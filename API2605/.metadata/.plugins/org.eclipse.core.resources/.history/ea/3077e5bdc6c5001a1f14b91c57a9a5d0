package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConnectionDB;
import model.Produtos;

public class ProdutosDAO {
	private Connection con;
	private PreparedStatement ps;
	private Produtos prod;
	private List<Produtos> lProdutos;

	public boolean create(Produtos prod) throws SQLException {
		String sql = "INSERT INTO Produtos VALUES (default,?,?,?,?,?, false);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, prod.getCnpj());
		ps.setString(2, prod.getNome());
		ps.setString(3, prod.getDescricao());
		ps.setDouble(4, prod.getPreco());
		ps.setBlob(5, prod.getImagem());
		
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public Produtos read(int id) throws SQLException {
		String sql = "SELECT * FROM Produtos WHERE id_produtos = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			prod = new Produtos();
			prod.setIdProdutos(rs.getInt("id_produtos"));
			prod.setCnpj(rs.getString("cnpj"));
			prod.setNome(rs.getString("nome"));
			prod.setDescricao(rs.getString("descricao"));
			prod.setPreco(rs.getDouble("preco"));
			prod.setImagem(rs.getBinaryStream("imagem"));
			prod.setExcluido(rs.getBoolean("excluido"));
		}
		con.close();
		return prod;
	}

	public boolean update(Produtos prod) throws SQLException {
		String sql = "UPDATE Produtos SET nome = ?, preco = ?, imagem = ?, descricao = ?, excluido = ? WHERE id_produtos = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);

		ps.setString(1, prod.getNome());
		ps.setDouble(2, prod.getPreco());
		ps.setBlob(3, prod.getImagem());
		ps.setString(4, prod.getDescricao());
		ps.setBoolean(5, prod.getExcluido());
		ps.setInt(6, prod.getIdProdutos());
		
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}
	
	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM Produtos WHERE id_produtos = ?;";
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

	public List<Produtos> list(String cnpj) throws SQLException {
		String sql = "SELECT * FROM Produtos where cnpj = ? and excluido = false;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ResultSet rs = ps.executeQuery();
		lProdutos = new ArrayList<Produtos>();
		while (rs.next()) {
			prod = new Produtos();
			prod.setCnpj(rs.getString("cnpj"));
			prod.setIdProdutos(rs.getInt("id_produtos"));
			prod.setCnpj(rs.getString("cnpj"));
			prod.setNome(rs.getString("nome"));
			prod.setDescricao(rs.getString("descricao"));
			prod.setPreco(rs.getDouble("preco"));
			prod.setImagem(rs.getBinaryStream("imagem"));
			prod.setExcluido(rs.getBoolean("excluido"));
			lProdutos.add(prod);
		}
		con.close();
		return lProdutos;
	}
}
