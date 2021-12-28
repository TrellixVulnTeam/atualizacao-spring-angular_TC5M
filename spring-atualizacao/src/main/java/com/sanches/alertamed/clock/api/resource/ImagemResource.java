package com.sanches.alertamed.clock.api.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanches.alertamed.clock.api.model.Imagem;
import com.sanches.alertamed.clock.api.repository.ImagemRepository;

@RestController
@RequestMapping("/imagem")
public class ImagemResource {
	
	@Autowired
	private ImagemRepository imagemRepository;
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Imagem> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Imagem> imagemSalva = this.imagemRepository.findById(codigo);
		
		System.out.println(imagemSalva.get().getPic());

		return imagemSalva.isPresent() ? ResponseEntity.ok(imagemSalva.get())
				: ResponseEntity.notFound().build();
	}
}
