package com.sanches.alertamed.clock.api.repository.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sanches.alertamed.clock.api.model.Paciente;
import com.sanches.alertamed.clock.api.repository.filter.PacienteFilter;

public interface PacienteRepositoryQuery {
	
	public Page<Paciente> filtrar(PacienteFilter filter, Pageable pageable);
	
}
