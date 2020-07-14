package model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

public class Produtos {
	private int idProdutos;
	private String cnpj;
	private String nome;
	private String descricao;
	private double preco;
	private InputStream imagem;
	private boolean excluido;
	
	public int getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(int idProdutos) {
		this.idProdutos = idProdutos;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public InputStream getImagem() {
		return imagem;
	}
	public void setImagem(InputStream imagem) {
		this.imagem = imagem;
	}
	public boolean getExcluido() {
		return excluido;
	}
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
	public InputStream parseImage(String image) {
		byte[] decodedBytes = Base64.getDecoder().decode(image.replace(" ", "+"));
		InputStream inp = new ByteArrayInputStream(decodedBytes);
		return inp;
	}
	
}
