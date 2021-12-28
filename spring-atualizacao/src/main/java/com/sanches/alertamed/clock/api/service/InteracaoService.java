package com.sanches.alertamed.clock.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sanches.alertamed.clock.api.model.Interacao;
import com.sanches.alertamed.clock.api.repository.InteracaoRepository;

@Service
public class InteracaoService {
	
	@Autowired
	private InteracaoRepository iRepository;

	public Interacao buscarInteracaoExistente(Long codigo) {
		Optional<Interacao> interacaoSalva = this.iRepository.findById(codigo);
		
		if (!interacaoSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		} else {
			return interacaoSalva.get();
		}
	}
	
	public Interacao atualizar(Long codigo, Interacao interacao) {
		Interacao interacaoSalva = this.buscarInteracaoExistente(codigo);
		
		BeanUtils.copyProperties(interacao, interacaoSalva, "codigo");
		
		return this.iRepository.save(interacaoSalva);
	}
}
