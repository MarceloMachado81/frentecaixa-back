package com.marcelo.sidbebi.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import com.marcelo.sidbebi.domain.Agendamento;
import com.marcelo.sidbebi.domain.ItensAgendamento;

public class ItensAgendamentoDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Agendamento agendamento;
	private String codBarra;
	
	@NotNull(message = "O campo ITEM DO PRODUTO é requerido")
	private String item;
	
	private double valorUnit;	
	private String tamanho;
	
	public ItensAgendamentoDTO() {
		super();
	}

	public ItensAgendamentoDTO(ItensAgendamento obj) {
		super();
		this.id = obj.getId();
		this.item = obj.getItem();
		this.valorUnit = obj.getValorUnit();
		this.agendamento = obj.getAgendamento();
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

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}	
	
}
