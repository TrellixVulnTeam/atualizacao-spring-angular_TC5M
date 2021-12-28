package com.sanches.alertamed.clock.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "interacao")
public class Interacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String mecanismoEfeito;
	
	private String recomendacoes;

	@ManyToOne
	@JoinColumn(name = "codigo_acao")
	private Acao acao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_medicamento_a")
	private Medicamento medicamentoa;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_medicamento_b")
	private Medicamento medicamentob;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getMecanismoEfeito() {
		return mecanismoEfeito;
	}

	public void setMecanismoEfeito(String mecanismoEfeito) {
		this.mecanismoEfeito = mecanismoEfeito;
	}

	public String getRecomendacoes() {
		return recomendacoes;
	}

	public void setRecomendacoes(String recomendacoes) {
		this.recomendacoes = recomendacoes;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Medicamento getMedicamentoa() {
		return medicamentoa;
	}

	public void setMedicamentoa(Medicamento medicamentoa) {
		this.medicamentoa = medicamentoa;
	}

	public Medicamento getMedicamentob() {
		return medicamentob;
	}

	public void setMedicamentob(Medicamento medicamentob) {
		this.medicamentob = medicamentob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
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
		Interacao other = (Interacao) obj;
		if (acao == null) {
			if (other.acao != null)
				return false;
		} else if (!acao.equals(other.acao))
			return false;
		return true;
	}

}
