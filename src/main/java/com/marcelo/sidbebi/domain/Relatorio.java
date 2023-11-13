package com.marcelo.sidbebi.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcelo.sidbebi.domain.enums.Tipo;

public class Relatorio implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicial;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFinal;
	
    private Produto produto;
    private Tipo tipo;
    private String tamanho;
    private Integer qtdTotal;
    private double valorTotal;
	
    public Relatorio() {
		super();
	}

	public Relatorio(LocalDate dataInicial, LocalDate dataFinal, Produto produto, Tipo tipo, String tamanho, Integer qtdTotal, double valorTotal) {
		super();
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.produto = produto;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.qtdTotal = qtdTotal;
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Integer getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(Integer qtdTotal) {
		this.qtdTotal = qtdTotal;
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

}
