package com.sanches.alertamed.clock.api.model;

public enum TipoUsuario {

	COMUM("Comum"),
	PACIENTE("Paciente");

	private final String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}