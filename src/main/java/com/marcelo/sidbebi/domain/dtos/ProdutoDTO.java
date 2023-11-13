package com.marcelo.sidbebi.domain.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.marcelo.sidbebi.domain.ItensProduto;
import com.marcelo.sidbebi.domain.Produto;
import com.marcelo.sidbebi.domain.enums.NivelEstoque;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message = "O TIPO do produto é requerido")
	private Integer tipo;
	
	@NotNull(message = "O NOME do produto é requerido")
	private String nome;	
	
	@NotNull(message = "O TAMANHO do produto é requerido")
	private String tamanho;
	
	@NotNull(message = "A QUANTIDADE do produto é reuquerida")
	private Integer qtd;
	
	@NotNull(message = "O VALOR UNITÁRIO do produto é requerido")
	private double valorUnit;
	
	private double valorTotal;
	
	private List<ItensProduto> itens;
	
	private NivelEstoque nivel;
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.tipo = obj.getTipo().getCodigo();
		this.nome = obj.getNome();
		this.tamanho = obj.getTamanho();
		this.qtd = obj.getQtd();
		this.valorUnit = obj.getValorUnit();
		this.valorTotal = obj.getValorTotal();
		this.itens = obj.getItens();
		this.nivel = obj.getNivel();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public double getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(double valorUnit) {
		this.valorUnit = valorUnit;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public NivelEstoque getNivel() {
		return nivel;
	}

	public void setNivel(NivelEstoque nivel) {
		this.nivel = nivel;
	}

	public List<ItensProduto> getItens() {
		return itens;
	}

	public void setItens(List<ItensProduto> itens) {
		this.itens = itens;
	}	
	
}