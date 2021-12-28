package com.sanches.alertamed.clock.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Interacao.class)
public abstract class Interacao_ {

	public static volatile SingularAttribute<Interacao, Long> codigo;
	public static volatile SingularAttribute<Interacao, String> recomendacoes;
	public static volatile SingularAttribute<Interacao, Medicamento> medicamentoa;
	public static volatile SingularAttribute<Interacao, String> mecanismoEfeito;
	public static volatile SingularAttribute<Interacao, Acao> acao;
	public static volatile SingularAttribute<Interacao, Medicamento> medicamentob;

	public static final String CODIGO = "codigo";
	public static final String RECOMENDACOES = "recomendacoes";
	public static final String MEDICAMENTOA = "medicamentoa";
	public static final String MECANISMO_EFEITO = "mecanismoEfeito";
	public static final String ACAO = "acao";
	public static final String MEDICAMENTOB = "medicamentob";

}

