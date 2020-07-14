package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pedidos;
import model.ConnectionDB;

public class PedidosDAO {
	private Connection con;
	private PreparedStatement ps;
	private Pedidos ped;
	private List<Pedidos> lPedidos;

	public boolean create(Pedidos ped) throws SQLException {
		String sql = "INSERT INTO Pedidos VALUES (default,?,?,null,curdate(),null,0)";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, ped.getCnpjCliente());
		ps.setString(2, ped.getCnpjFornecedor());

		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public Pedidos read(int id) throws SQLException {
		String sql = "SELECT * FROM Pedidos WHERE id_pedidos = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			ped = new Pedidos();
			ped.setIdPedidos(rs.getInt("id_pedidos"));
			ped.setCnpjCliente(rs.getString("cnpj_cliente"));
			ped.setCnpjFornecedor(rs.getString("cnpj_fornecedor"));
			ped.setDataFinal(rs.getString("data_final"));
			ped.setDataPrevisao(rs.getString("data_previsao"));
			ped.setDataPedido(rs.getString("data_pedido"));
			ped.setStatus(rs.getInt("status"));
		}
		con.close();
		return ped;
	}

	public boolean update(Pedidos ped) throws SQLException {
		String sql = "UPDATE Pedidos SET data_previsao = ?, data_final = ?, status = ? WHERE id_pedidos = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		
		ps.setString(1, ped.getDataPrevisao());
		ps.setString(2, ped.getDataFinal());
		ps.setInt(3, ped.getStatus());
		ps.setInt(4, ped.getIdPedidos());

		if (ps.executeUpdate() > 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}

	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM Pedidos WHERE id_pedidos = ?;";
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

	public List<Pedidos> listMeusPedidos(String cnpj) throws SQLException {
		String sql = "SELECT * FROM Pedidos WHERE cnpj_cliente = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ResultSet rs = ps.executeQuery();
		lPedidos = new ArrayList<Pedidos>();
		while (rs.next()) {
			ped = new Pedidos();
			ped.setIdPedidos(rs.getInt("id_pedidos"));
			ped.setCnpjCliente(rs.getString("cnpj_cliente"));
			ped.setCnpjFornecedor(rs.getString("cnpj_fornecedor"));
			ped.setDataFinal(rs.getString("data_final"));
			ped.setDataPrevisao(rs.getString("data_previsao"));
			ped.setDataPedido(rs.getString("data_pedido"));
			ped.setStatus(rs.getInt("status"));
			lPedidos.add(ped);
		}
		con.close();
		return lPedidos;
	}
	public List<Pedidos> listPedidosRecebidos(String cnpj) throws SQLException {
		String sql = "SELECT * FROM Pedidos WHERE cnpj_fornecedor = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);
		ResultSet rs = ps.executeQuery();
		lPedidos = new ArrayList<Pedidos>();
		while (rs.next()) {
			ped = new Pedidos();
			ped.setIdPedidos(rs.getInt("id_pedidos"));
			ped.setCnpjCliente(rs.getString("cnpj_cliente"));
			ped.setCnpjFornecedor(rs.getString("cnpj_fornecedor"));
			ped.setDataFinal(rs.getString("data_final"));
			ped.setDataPrevisao(rs.getString("data_previsao"));
			ped.setDataPedido(rs.getString("data_pedido"));
			ped.setStatus(rs.getInt("status"));
			lPedidos.add(ped);
		}
		con.close();
		return lPedidos;
	}

	public int[] infoPedidos(String cnpj) throws SQLException {
		String sql ="SELECT SUM(case when data_final is null then 1 else 0 end) as qnt_pedidos_recebidos_pendentes, "+ 
				"SUM(case when data_final is not null then 1 else 0 end) as qnt_pedidos_entregues FROM `pedidos` where cnpj_fornecedor = ?";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, cnpj);

		ResultSet rs = ps.executeQuery();

		int[] qnt = new int[3];
		if (rs.next()) {
			qnt[0] = rs.getInt("qnt_pedidos_recebidos_pendentes");
			qnt[1] = rs.getInt("qnt_pedidos_entregues");
			qnt[2] = qnt[0]+qnt[1];
			return qnt;
		} else {
			return qnt;
		}
	}
}
