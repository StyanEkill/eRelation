package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConnectionDB;
import model.Item;

public class ItemDAO {
	private Connection con;
	private PreparedStatement ps;
	private Item item;
	private List<Item> lItens;

	public boolean create(List<Item> lItem) throws SQLException {
		String sql= "INSERT INTO Itens VALUES";
		int i = 0;
		for(Item item : lItem) {
			sql += "(default,(SELECT MAX(id_pedidos) from pedidos),"+item.getIdProdutos()+","+item.getQnt()+", (SELECT preco FROM produtos WHERE id_produtos = "+item.getIdProdutos()+"))";
			if(i < (lItem.size() - 1)) { sql += ",";}
			i++;
		}

		System.out.println(sql);
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM Itens WHERE id_itens_pedido = ?;";
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

	public List<Item> list(int id) throws SQLException {
		String sql = "SELECT * FROM Itens WHERE id_pedidos = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		lItens = new ArrayList<Item>();
		while (rs.next()) {
			item = new Item();
			item.setIdItensPedido(rs.getInt("id_itens_pedido"));
			item.setIdPedidos(rs.getInt("id_pedidos"));
			item.setIdProdutos(rs.getInt("id_produtos"));
			item.setQnt(rs.getInt("qnt"));
			item.setValor(rs.getFloat("valor"));
			lItens.add(item);
		}
		con.close();
		return lItens;
	}

}
