package model;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Mensagem {
	private int idMensagem;
	private String cnpjDest;
	private String cnpjOrigem;
	private String mensagem;
	private Date dataPostagem;
	private String horarioPostagem;
	private boolean status;

	public int getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(int idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getCnpjDest() {
		return cnpjDest;
	}

	public void setCnpjDest(String cnpjDest) {
		this.cnpjDest = cnpjDest;
	}

	public String getCnpjOrigem() {
		return cnpjOrigem;
	}

	public void setCnpjOrigem(String cnpjOrigem) {
		this.cnpjOrigem = cnpjOrigem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public String getHorarioPostagem() {
		return horarioPostagem;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}

	public void setHorarioPostagem(String horarioPostagem) {
		this.horarioPostagem = horarioPostagem;
	}

	public java.sql.Date parseUtilSQL(Date d) {
		return new java.sql.Date(d.getTime());
	}

	public String parseDate(Date d) {
		return new SimpleDateFormat("dd/MM/yyyy").format(d);
	}
	public String parseTime(String time) {
		DateTimeFormatter parserHora= DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime hora = LocalTime.parse(time, parserHora);
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String horaFormatada = formatterHora.format(hora);
		return horaFormatada;
	}
}
