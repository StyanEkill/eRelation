package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Socio {
	private String cnpjOrigem;
	private String cnpjDest;
	private Date dataContratacao;

	public String getCnpjOrigem() {
		return cnpjOrigem;
	}

	public void setCnpjOrigem(String cnpjOrigem) {
		this.cnpjOrigem = cnpjOrigem;
	}

	public String getCnpjDest() {
		return cnpjDest;
	}

	public void setCnpjDest(String cnpjDest) {
		this.cnpjDest = cnpjDest;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public java.sql.Date parseUtilSQL(Date d) {
		return new java.sql.Date(d.getTime());
	}
	public String parseDate(Date d) {
		return DateFormat.getDateInstance().format(d);
	}
	public String parseUtilDate(String d) {
		return new SimpleDateFormat("dd/MM/yyyy").format(d);
	}
}
