package com.marcelo.sidbebi.domain;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.sidbebi.domain.enums.Pagamento;

@Entity
public class Venda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataHora;
	
	@JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
	private LocalDateTime dataHoraRegistro = LocalDateTime.now().minusHours(4);	
	
	@JsonIgnore
	@OneToMany (mappedBy = "venda")	
	private List<ItensVenda> itens;
	
	private String[] itensVenda;	
	private Integer qtdItens;
	private double valorTotal;
	private String pagamento;
	
	public Venda() {
		super();
	}

	public Venda(Integer id, List<ItensVenda> itens, String[] itensVenda, Cliente cliente, String nomeCliente, 
			Integer qtdItens, double valorTotal, Pagamento pagamento) {
		super();
		this.id = id;
		this.itens = itens;
		this.itensVenda = itensVenda;
		this.qtdItens = qtdItens;
		this.valorTotal = valorTotal;
		this.pagamento = pagamento.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<ItensVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItensVenda> itens) {
		this.itens = itens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
	public String[] getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(String[] itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Integer getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
	
	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

	public LocalDateTime getDataHoraRegistro() {
		return dataHoraRegistro;
	}

	public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
		this.dataHoraRegistro = dataHoraRegistro;
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}