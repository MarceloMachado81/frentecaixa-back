package com.marcelo.sidbebi.domain.enums;

public enum Status {
	
	SOLICITADO(0, "SOLICITADO"), ANDAMENTO(1, "ANDAMENTO"), ENTREGUE(2, "ENTREGUE"), CANCELADO(3, "CANCELADO");
	
	private Integer codigoInteger;
	private String descricao;
	
	private Status(Integer codigoInteger, String descricao) {
		this.codigoInteger = codigoInteger;
		this.descricao = descricao;
	}

	public Integer getCodigoInteger() {
		return codigoInteger;
	}

	public String getDescricao() {
		return descricao;
	}
	
	//método que verifica se foi digitado um dos tipos enumerados acima (0, 1, 2, 3)
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Status xStatus : Status.values()) {
			if(cod.equals(xStatus.getCodigoInteger())) {
				return xStatus;
			}
		}
		throw new IllegalArgumentException("Status Inválido");
	}

}
