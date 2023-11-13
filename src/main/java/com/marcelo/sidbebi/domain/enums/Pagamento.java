package com.marcelo.sidbebi.domain.enums;

public enum Pagamento {
	
	DINHEIRO(0, "DINHEIRO"), CARTAO_CREDITO(1, "CARTAO_CREDITO"), CARTAO_DEBITO(2, "CARTAO_DEBITO"), PIX(3, "PIX");
	
	private Integer codigoInteger;
	private String descricao;
	
	private Pagamento(Integer codigoInteger, String descricao) {
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
	public static Pagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Pagamento xStatus : Pagamento.values()) {
			if(cod.equals(xStatus.getCodigoInteger())) {
				return xStatus;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}

}
