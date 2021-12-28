package com.sanches.alertamed.clock.api.dto;

import com.sanches.alertamed.clock.api.model.Medicamento;

public class RelatorioMedicamentosPaciente {

	private Medicamento medicamento;
	private Boolean cafeManha;
	private String almoco;
	private String janta;
	private String horaDormir;
	private String necessario;
	private String modoUso;

	public RelatorioMedicamentosPaciente(Medicamento medicamento, Boolean cafeManha) {
		super();
//		System.out.println("med: " + medicamento);
		this.medicamento = medicamento;
		System.out.println("teste: " + cafeManha);
//		this.cafeManha = (cafeManha) ? "antes" : "depois";
//		this.almoco = (almoco) ? "antes" : "depois";
//		this.janta = (janta) ? "antes" : "depois";
//		this.horaDormir = (horaDormir) ? "antes" : "depois";
//		this.necessario = (necessario) ? "antes" : "depois";
//		this.modoUso = (modoUso) ? "antes" : "depois";
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Boolean getCafeManha() {
		return cafeManha;
	}

	public void setCafeManha(Boolean cafeManha) {
		this.cafeManha = cafeManha;
	}

	public String getAlmoco() {
		return almoco;
	}

	public void setAlmoco(String almoco) {
		this.almoco = almoco;
	}

	public String getJanta() {
		return janta;
	}

	public void setJanta(String janta) {
		this.janta = janta;
	}

	public String getHoraDormir() {
		return horaDormir;
	}

	public void setHoraDormir(String horaDormir) {
		this.horaDormir = horaDormir;
	}

	public String getNecessario() {
		return necessario;
	}

	public void setNecessario(String necessario) {
		this.necessario = necessario;
	}

	public String getModoUso() {
		return modoUso;
	}

	public void setModoUso(String modoUso) {
		this.modoUso = modoUso;
	}

}
