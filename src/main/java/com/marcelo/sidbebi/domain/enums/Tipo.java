package com.marcelo.sidbebi.domain.enums;

public enum Tipo {
	
	ALCOOLICA(0, "ALCOOLICA"), NAOALCOOLICA(1, "NAO_ALCOOLICA"), COMIDA(2, "COMIDA"), DIVERSOS(3, "DIVERSOS");
	
	private Integer codigo;
	private String descricao;
	
	private Tipo(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	//método que verifica se foi digitado um dos tipos enumerados acima (0, 1, 2, 3)
	public static Tipo toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Tipo x : Tipo.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Inválido");
	}

}
