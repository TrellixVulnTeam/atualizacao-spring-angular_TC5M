package com.sanches.alertamed.clock.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanches.alertamed.clock.api.model.Paciente;
import com.sanches.alertamed.clock.api.repository.paciente.PacienteRepositoryQuery;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, PacienteRepositoryQuery {
	
	public Optional<Paciente> findByCpf(String cpf);
	public Optional<Paciente> findByEmail(String email);
	
}
