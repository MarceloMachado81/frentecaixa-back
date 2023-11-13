package com.marcelo.sidbebi.domain.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.marcelo.sidbebi.domain.Fornecedor;
import com.marcelo.sidbebi.domain.ItensProduto;

public class FornecedorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotNull(message = "O campo NOME é requerido")
	private String nome;
	
	@NotNull(message = "O campo CPF/CNPJ é requerido")
	private String cpfCnpj;
	private String end;
	
	@NotNull(message = "O campo TELEFONE é requerido")
	private String telefone;
	
	@NotNull(message = "O campo E-MAIL é requerido")
	private String email;
	
	private List<ItensProduto> itens;
	
	public FornecedorDTO() {
		super();
	}

	public FornecedorDTO(Fornecedor obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpfCnpj = obj.getCpfCnpj();
		this.end = obj.getEnd();
		this.telefone = obj.getTelefone();
		this.email = obj.getEmail();
		this.itens = obj.getItens();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<ItensProduto> getItens() {
		return itens;
	}

	public void setItens(List<ItensProduto> itens) {
		this.itens = itens;
	}	
	
}
