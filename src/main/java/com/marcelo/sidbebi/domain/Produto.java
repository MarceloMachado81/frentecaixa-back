package com.marcelo.sidbebi.domain;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.sidbebi.domain.enums.NivelEstoque;
import com.marcelo.sidbebi.domain.enums.Tipo;

@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id		
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonIgnore
	@OneToMany (mappedBy = "produto")	
	private List<ItensProduto> itens;
	
	private Tipo tipo;
	private String nome;
	private String tamanho;
	private Integer qtd;
	private double valorUnit;
	private double valorTotal;
	private NivelEstoque nivel;
	
	public Produto() {
		super();
	}

	public Produto(Integer id, Tipo tipo, String nome, String tamanho, Integer qtd, double valorUnit, double valorTotal,
			NivelEstoque nivel, List<ItensProduto> itens) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.itens = itens;
		this.tamanho = tamanho;
		this.qtd = qtd;
		this.valorUnit = valorUnit;
		this.valorTotal = valorTotal;
		this.nivel = nivel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}