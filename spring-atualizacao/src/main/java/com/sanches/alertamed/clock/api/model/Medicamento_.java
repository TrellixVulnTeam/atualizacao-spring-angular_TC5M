package com.sanches.alertamed.clock.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medicamento.class)
public abstract class Medicamento_ {

	public static volatile SingularAttribute<Medicamento, Long> codigo;
	public static volatile SingularAttribute<Medicamento, String> nome;
	public static volatile SingularAttribute<Medicamento, String> posologia;

	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String POSOLOGIA = "posologia";

}

