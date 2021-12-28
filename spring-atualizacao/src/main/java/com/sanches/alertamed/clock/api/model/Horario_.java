package com.sanches.alertamed.clock.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Horario.class)
public abstract class Horario_ {

	public static volatile SingularAttribute<Horario, String> importante;
	public static volatile SingularAttribute<Horario, Long> codigo;
	public static volatile SingularAttribute<Horario, String> almoco;
	public static volatile SingularAttribute<Horario, String> modoUso;
	public static volatile SingularAttribute<Horario, Paciente> paciente;
	public static volatile SingularAttribute<Horario, String> janta;
	public static volatile SingularAttribute<Horario, Medicamento> medicamento;
	public static volatile SingularAttribute<Horario, String> horaDormir;
	public static volatile SingularAttribute<Horario, String> cafeManha;

	public static final String IMPORTANTE = "importante";
	public static final String CODIGO = "codigo";
	public static final String ALMOCO = "almoco";
	public static final String MODO_USO = "modoUso";
	public static final String PACIENTE = "paciente";
	public static final String JANTA = "janta";
	public static final String MEDICAMENTO = "medicamento";
	public static final String HORA_DORMIR = "horaDormir";
	public static final String CAFE_MANHA = "cafeManha";

}

