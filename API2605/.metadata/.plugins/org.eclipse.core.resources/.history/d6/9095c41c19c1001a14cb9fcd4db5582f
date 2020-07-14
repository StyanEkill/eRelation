package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedidos {
	private int idPedidos;
	private String cnpjCliente;
	private String cnpjFornecedor;
	private String dataPrevisao;
	private String dataPedido;
	private String dataFinal;
	private int status;
	
	public int getIdPedidos() {
		return idPedidos;
	}

	public void setIdPedidos(int idPedidos) {
		this.idPedidos = idPedidos;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjOrigem) {
		this.cnpjCliente = cnpjOrigem;
	}

	public String getCnpjFornecedor() {
		return cnpjFornecedor;
	}

	public void setCnpjFornecedor(String cnpjDest) {
		this.cnpjFornecedor = cnpjDest;
	}

	public String getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(String dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public java.sql.Date parseUtilSQL(Date d) {
		return new java.sql.Date(d.getTime());
	}

	public Date parseUtilDate(String d) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyy").parse(d);
	}
}
