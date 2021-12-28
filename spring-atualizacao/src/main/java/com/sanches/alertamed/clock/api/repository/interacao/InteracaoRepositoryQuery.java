package com.sanches.alertamed.clock.api.repository.interacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sanches.alertamed.clock.api.repository.filter.InteracaoFilter;
import com.sanches.alertamed.clock.api.repository.projection.ResumoInteracao;

public interface InteracaoRepositoryQuery {
	
//	public Page<Interacao> filtrar(InteracaoFilter interacaoFilter, Pageable pageable);

	public Page<ResumoInteracao> resumir(InteracaoFilter interacaoFilter, Pageable pageable);

}
