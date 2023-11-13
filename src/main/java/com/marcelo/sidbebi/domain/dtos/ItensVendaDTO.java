package com.marcelo.sidbebi.domain.dtos;

import javax.validation.constraints.NotNull;

import com.marcelo.sidbebi.domain.ItensVenda;
import com.marcelo.sidbebi.domain.Venda;

public class ItensVendaDTO {
	
	private Integer id;
	
	@NotNull(message = "O campo ITEM DO PRODUTO é requerido")
	private String item;
	
	@NotNull(message = "O campo CÓDIGO DE BARRAS é requerido")
	private String codBarra;
	
	private double valorUnit;
	private Venda venda;
	private String tamanho;
	
	public ItensVendaDTO() {
		super();
	}

	public ItensVendaDTO(ItensVenda obj) {
		super();
		this.id = obj.getId();
		this.item = obj.getItem();
		this.codBarra = obj.getCodBarra();
		this.valorUnit = obj.getValorUnit();
		this.venda = obj.getVenda();
		this.tamanho = obj.getTamanho();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(double valorUnit) {
		this.valorUnit = valorUnit;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}	

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}	
	
}
