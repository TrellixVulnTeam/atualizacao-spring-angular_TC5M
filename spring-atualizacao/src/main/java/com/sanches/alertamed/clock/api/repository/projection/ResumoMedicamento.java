package com.sanches.alertamed.clock.api.repository.projection;

public class ResumoMedicamento {

	private Long codigo;
	private String nome;

	public ResumoMedicamento(Long codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
