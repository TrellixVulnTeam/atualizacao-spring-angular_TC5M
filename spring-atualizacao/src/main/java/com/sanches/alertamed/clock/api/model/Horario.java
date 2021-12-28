package com.sanches.alertamed.clock.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "horario")
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "cafe_manha")
	private String cafeManha;

	@ManyToOne
	@JoinColumn(name = "codigo_medicamento")
	private Medicamento medicamento;
	
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;

	private String almoco;

	private String janta;

	@Column(name = "hora_dormir")
	private String horaDormir;

	private String importante;

	@Column(name = "descricao")
	private String modoUso;

	public Long getCodigo() {
		return codigo;
	}

	public String getCafeManha() {
		return cafeManha;
	}

	public void setCafeManha(String cafeManha) {
		this.cafeManha = cafeManha;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
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

	public String getImportante() {
		return importante;
	}

	public void setImportante(String importante) {
		this.importante = importante;
	}

	public String getModoUso() {
		return modoUso;
	}

	public void setModoUso(String modoUso) {
		this.modoUso = modoUso;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Horario other = (Horario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
