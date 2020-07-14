package model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

import java.util.InputMismatchException;

public class Empresa {
	private String cnpj;
	private String nomeEmpresa;
	private String endereco;
	private String telefone;
	private InputStream fotoPerfil;
	private String descricao;
	private String tipo;
	private String senha;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public InputStream getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(InputStream fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public InputStream parseImage(String image) {
		byte[] decodedBytes = Base64.getDecoder().decode(image.replace(" ", "+"));
		InputStream inp = new ByteArrayInputStream(decodedBytes);
		return inp;
	}

	public boolean isCNPJ(String cnpj) {
		String formatCnpj = cnpj.replaceAll("[-\\/\\.]", "");

		if (formatCnpj.equals("00000000000000") || formatCnpj.equals("11111111111111")
				|| formatCnpj.equals("22222222222222") || formatCnpj.equals("33333333333333")
				|| formatCnpj.equals("44444444444444") || formatCnpj.equals("55555555555555")
				|| formatCnpj.equals("66666666666666") || formatCnpj.equals("77777777777777")
				|| formatCnpj.equals("88888888888888") || formatCnpj.equals("99999999999999")
				|| (formatCnpj.length() != 14))
			return (false);

		char penultimo, ultimo4;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {

				num = (int) (formatCnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				penultimo = '0';
			else
				penultimo = (char) ((11 - r) + 48);

			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (formatCnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				ultimo4 = '0';
			else
				ultimo4 = (char) ((11 - r) + 48);

			if ((penultimo == formatCnpj.charAt(12)) && (ultimo4 == formatCnpj.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}
