package com.sanches.alertamed.clock.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Imagem.class)
public abstract class Imagem_ {

	public static volatile SingularAttribute<Imagem, String> name;
	public static volatile SingularAttribute<Imagem, Long> id;
	public static volatile SingularAttribute<Imagem, byte[]> pic;
	public static volatile SingularAttribute<Imagem, String> type;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PIC = "pic";
	public static final String TYPE = "type";

}

