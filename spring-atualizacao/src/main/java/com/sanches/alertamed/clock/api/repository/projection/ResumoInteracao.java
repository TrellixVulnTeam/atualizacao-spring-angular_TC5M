package com.sanches.alertamed.clock.api.repository.projection;

public class ResumoInteracao {

	private Long codigo;
	private String medA;
	private String medB;
	private String acao;

	public ResumoInteracao(Long codigo, String medA, String medB, String acao) {
		this.codigo = codigo;
		this.medA = medA;
		this.medB = medB;
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getMedA() {
		return medA;
	}

	public void setMedA(String medA) {
		this.medA = medA;
	}

	public String getMedB() {
		return medB;
	}

	public void setMedB(String medB) {
		this.medB = medB;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

}
