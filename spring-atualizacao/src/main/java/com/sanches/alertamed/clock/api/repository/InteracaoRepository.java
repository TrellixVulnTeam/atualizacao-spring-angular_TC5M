package com.sanches.alertamed.clock.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanches.alertamed.clock.api.model.Interacao;
import com.sanches.alertamed.clock.api.repository.interacao.InteracaoRepositoryQuery;

public interface InteracaoRepository extends JpaRepository<Interacao, Long>, InteracaoRepositoryQuery {

	Interacao findByMedicamentoaCodigoAndMedicamentobCodigo (Long medA, Long medB);
}
