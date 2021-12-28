package com.sanches.alertamed.clock.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, Long> codigo;
	public static volatile ListAttribute<Paciente, Horario> horarios;
	public static volatile SingularAttribute<Paciente, String> cpf;
	public static volatile SingularAttribute<Paciente, String> nome;
	public static volatile SingularAttribute<Paciente, String> email;

	public static final String CODIGO = "codigo";
	public static final String HORARIOS = "horarios";
	public static final String CPF = "cpf";
	public static final String NOME = "nome";
	public static final String EMAIL = "email";

}

