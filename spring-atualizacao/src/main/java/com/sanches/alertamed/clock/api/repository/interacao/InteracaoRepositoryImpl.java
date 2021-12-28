package com.sanches.alertamed.clock.api.repository.interacao;

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

import com.sanches.alertamed.clock.api.model.Acao_;
import com.sanches.alertamed.clock.api.model.Interacao;
import com.sanches.alertamed.clock.api.model.Interacao_;
import com.sanches.alertamed.clock.api.model.Medicamento_;
import com.sanches.alertamed.clock.api.repository.filter.InteracaoFilter;
import com.sanches.alertamed.clock.api.repository.projection.ResumoInteracao;

public class InteracaoRepositoryImpl implements InteracaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ResumoInteracao> resumir(InteracaoFilter interacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<ResumoInteracao> criteria = builder.createQuery(ResumoInteracao.class);
		Root<Interacao> root = criteria.from(Interacao.class);

		criteria.select(builder.construct(ResumoInteracao.class,

				root.get(Interacao_.CODIGO), root.get(Interacao_.MEDICAMENTOA).get(Medicamento_.NOME),
				root.get(Interacao_.MEDICAMENTOB).get(Medicamento_.NOME),
				root.get(Interacao_.ACAO).get(Acao_.DESCRICAO)));

		Predicate[] predicates = criarRestricoes(interacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoInteracao> query = this.manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(interacaoFilter));
	}

	private Long total(InteracaoFilter interacaoFilter) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Interacao> root = criteria.from(Interacao.class);

		Predicate[] predicates = criarRestricoes(interacaoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return this.manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(InteracaoFilter interacaoFilter, CriteriaBuilder builder,
			Root<Interacao> root) {

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
