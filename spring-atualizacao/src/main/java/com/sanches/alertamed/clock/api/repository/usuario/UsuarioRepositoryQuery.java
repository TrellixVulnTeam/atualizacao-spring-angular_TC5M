package com.sanches.alertamed.clock.api.repository.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sanches.alertamed.clock.api.repository.projection.ResumoUsuario;

public interface UsuarioRepositoryQuery {

	public Page<ResumoUsuario> resumir(Pageable pageable);
}
