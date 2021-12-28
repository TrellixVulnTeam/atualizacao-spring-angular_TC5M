package com.sanches.alertamed.clock.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanches.alertamed.clock.api.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

}
