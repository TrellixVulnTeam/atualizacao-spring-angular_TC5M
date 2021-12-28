package com.sanches.alertamed.clock.api.repository.projection;

public class ResumoHorario {

	private String importante;
	private String almoco;
	private String modoUso;
	private String janta;
	private String medicamento;
	private String horaDormir;
	private String cafeManha;
	
	

	public ResumoHorario(String importante, String almoco, String modoUso, String janta, String medicamento,
			String horaDormir, String cafeManha) {
		super();
		this.importante = importante;
		this.almoco = almoco;
		this.modoUso = modoUso;
		this.janta = janta;
		this.medicamento = medicamento;
		this.horaDormir = horaDormir;
		this.cafeManha = cafeManha;
	}

	public String getImportante() {
		return importante;
	}

	public void setImportante(String importante) {
		this.importante = importante;
	}

	public String getAlmoco() {
		return almoco;
	}

	public void setAlmoco(String almoco) {
		this.almoco = almoco;
	}

	public String getModoUso() {
		return modoUso;
	}

	public void setModoUso(String modoUso) {
		this.modoUso = modoUso;
	}

	public String getJanta() {
		return janta;
	}

	public void setJanta(String janta) {
		this.janta = janta;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getHoraDormir() {
		return horaDormir;
	}

	public void setHoraDormir(String horaDormir) {
		this.horaDormir = horaDormir;
	}

	public String getCafeManha() {
		return cafeManha;
	}

	public void setCafeManha(String cafeManha) {
		this.cafeManha = cafeManha;
	}

}
