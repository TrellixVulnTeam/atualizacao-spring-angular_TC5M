package com.sanches.alertamed.clock.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanches.alertamed.clock.api.model.Medicamento;
import com.sanches.alertamed.clock.api.repository.medicamento.MedicamentoRepositoryQuery;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>, MedicamentoRepositoryQuery {

}
