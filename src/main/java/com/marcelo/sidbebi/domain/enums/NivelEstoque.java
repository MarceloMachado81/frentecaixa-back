package com.marcelo.sidbebi.domain.enums;

public enum NivelEstoque {
	
	ALTO(0, "ALTO"), NORMAL(1, "NORMAL"), BAIXO(2, "BAIXO");
	
	private Integer codigoInteger;
	private String descricao;
	
	private NivelEstoque(Integer codigoInteger, String descricao) {
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
	public static NivelEstoque toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(NivelEstoque xStatus : NivelEstoque.values()) {
			if(cod.equals(xStatus.getCodigoInteger())) {
				return xStatus;
			}
		}
		throw new IllegalArgumentException("Nível Inválido");
	}

}
