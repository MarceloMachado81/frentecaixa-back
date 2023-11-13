package com.marcelo.sidbebi.domain.dtos;

import javax.validation.constraints.NotNull;

import com.marcelo.sidbebi.domain.ItensProduto;

public class ItensProdutoDTO {
	
	private Integer id;
	
	private Integer produto;
	
	private Integer fornecedor;
	
	@NotNull(message = "O campo CÓDIGO DE BARRAS é requerido")
	private String codBarra;
	
	private String nomeProduto;
	private String nomeFornecedor;
	private String tamanho;
	private double valor;
	
	public ItensProdutoDTO() {
		super();
	}

	public ItensProdutoDTO(ItensProduto obj) {
		super();
		this.id = obj.getId();
		this.produto = obj.getProduto().getId();
		this.fornecedor = obj.getFornecedor().getId();
		this.codBarra = obj.getCodBarra();
		this.nomeProduto = obj.getProduto().getNome();
		this.nomeFornecedor = obj.getFornecedor().getNome();
		this.tamanho = obj.getProduto().getTamanho();
		this.valor = obj.getProduto().getValorUnit();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public Integer getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Integer fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
