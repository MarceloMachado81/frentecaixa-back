package com.marcelo.sidbebi.domain;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.sidbebi.domain.enums.Pagamento;
import com.marcelo.sidbebi.domain.enums.Status;


@Entity
public class Agendamento {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonIgnore
	@OneToMany (mappedBy = "agendamento")	
	private List<ItensAgendamento> itens;
	
	private String[] itensAgendamento;
	private Integer qtdItens;	
	private double valorTotal;
	private String end;
	
	private String dataHora;
	
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	private String telefone;
	private String pagamento;
	private String nomeCliente;
	
	public Agendamento() {
		super();
	}

	public Agendamento(Integer id, double valorTotal, String end, Status status, Cliente cliente, String nomeCliente,
			List<ItensAgendamento> itens, String[] itensAgendamento, Integer qtdItens, String telefone, Pagamento pagamento) {
		super();
		
		this.id = id;
		this.valorTotal = valorTotal;
		this.end = end;
		this.status = status;
		this.cliente = cliente;
		this.itens = itens;
		this.itensAgendamento = itensAgendamento;
		this.qtdItens = qtdItens;
		this.telefone = telefone;
		this.pagamento = pagamento.toString();
		this.nomeCliente = nomeCliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ItensAgendamento> getItens() {
		return itens;
	}

	public void setItens(List<ItensAgendamento> itens) {
		this.itens = itens;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String[] getItensAgendamento() {
		return itensAgendamento;
	}

	public void setItensAgendamento(String[] itensAgendamento) {
		this.itensAgendamento = itensAgendamento;
	}

	public Integer getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
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
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}