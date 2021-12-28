package com.sanches.alertamed.clock.api.repository.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.sanches.alertamed.clock.api.model.Usuario;
import com.sanches.alertamed.clock.api.model.Usuario_;
import com.sanches.alertamed.clock.api.repository.projection.ResumoUsuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Page<ResumoUsuario> resumir(Pageable pageable) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<ResumoUsuario> criteria = builder.createQuery(ResumoUsuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(builder.construct(ResumoUsuario.class,
				root.get(Usuario_.CODIGO),
				root.get(Usuario_.NOME),
				root.get(Usuario_.EMAIL),
				root.get(Usuario_.TIPO)));

//		TODO: no momento no existe restricoes
		Predicate[] predicates = criarRestricoes(builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoUsuario> query = this.manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total());

	}

	private Long total() {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		Predicate[] predicates = criarRestricoes(builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return this.manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(CriteriaBuilder builder, Root<Usuario> root) {

		List<Predicate> predicates = new ArrayList<>();

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

}
