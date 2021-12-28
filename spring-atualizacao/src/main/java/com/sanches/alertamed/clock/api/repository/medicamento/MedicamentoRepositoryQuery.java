package com.sanches.alertamed.clock.api.repository.medicamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sanches.alertamed.clock.api.repository.filter.MedicamentoFilter;
import com.sanches.alertamed.clock.api.repository.projection.ResumoMedicamento;

public interface MedicamentoRepositoryQuery {

	public Page<ResumoMedicamento> resumir(MedicamentoFilter medicamentoFilter, Pageable pageable);

}
