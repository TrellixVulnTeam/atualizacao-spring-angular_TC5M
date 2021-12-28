package com.sanches.alertamed.clock.api.repository.medicamento;

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

import com.sanches.alertamed.clock.api.model.Medicamento;
import com.sanches.alertamed.clock.api.model.Medicamento_;
import com.sanches.alertamed.clock.api.repository.filter.MedicamentoFilter;
import com.sanches.alertamed.clock.api.repository.projection.ResumoMedicamento;

public class MedicamentoRepositoryImpl implements MedicamentoRepositoryQuery {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Page<ResumoMedicamento> resumir(MedicamentoFilter medicamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<ResumoMedicamento> criteria = builder.createQuery(ResumoMedicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);

		criteria.select(
				builder.construct(ResumoMedicamento.class, root.get(Medicamento_.CODIGO), root.get(Medicamento_.NOME)));

		Predicate[] predicates = criarRestricoes(medicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoMedicamento> query = this.manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(medicamentoFilter));

	}

	private Long total(MedicamentoFilter medicamentoFilter) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);

		Predicate[] predicates = criarRestricoes(medicamentoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return this.manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(MedicamentoFilter medicamentoFilter, CriteriaBuilder builder,
			Root<Medicamento> root) {

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
