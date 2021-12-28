package com.sanches.alertamed.clock.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanches.alertamed.clock.api.model.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

	public Imagem findByName (String name);
}
