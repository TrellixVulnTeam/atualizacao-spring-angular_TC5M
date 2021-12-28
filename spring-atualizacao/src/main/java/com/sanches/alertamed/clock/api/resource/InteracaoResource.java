package com.sanches.alertamed.clock.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sanches.alertamed.clock.api.model.Interacao;
import com.sanches.alertamed.clock.api.repository.InteracaoRepository;
import com.sanches.alertamed.clock.api.repository.filter.InteracaoFilter;
import com.sanches.alertamed.clock.api.repository.projection.ResumoInteracao;
import com.sanches.alertamed.clock.api.service.InteracaoService;

@RestController
@RequestMapping("/interacoes")
public class InteracaoResource {

	@Autowired
	private InteracaoRepository interacaoRepository;

	@Autowired
	private InteracaoService interacaoService;

	@GetMapping
	public Interacao pesquisar(@RequestParam Long meda, Long medb) {
//		System.out.println("medA" + meda);
//		System.out.println("medB" + medb);
//		return this.interacaoRepository.findByMedicamentoaCodigoAndMedicamentobCodigo(meda, medb);
		Interacao i = this.interacaoRepository.findByMedicamentoaCodigoAndMedicamentobCodigo(meda, medb);
		if(i != null) {
			System.out.println(i.getAcao().getDescricao());
		}
		return i;
//		if (i != null) {
//			return i;
//		} else {
//			return this.interacaoRepository.findByMedicamentoaCodigoAndMedicamentobCodigo(medb, meda);
//		}
	}

	@GetMapping(params = "resumo")
	public Page<ResumoInteracao> resumir(InteracaoFilter filter, Pageable pageable) {
		return this.interacaoRepository.resumir(filter, pageable);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Interacao> buscarPeloId(@PathVariable Long codigo) {
		Optional<Interacao> interacaoSalva = this.interacaoRepository.findById(codigo);
		return interacaoSalva.isPresent() ? ResponseEntity.ok(interacaoSalva.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Interacao> salvar(@Valid @RequestBody Interacao interacao, HttpServletResponse response) {
		Interacao interacaoSalva = this.interacaoRepository.save(interacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(interacaoSalva);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Interacao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Interacao interacao) {
		Interacao interacaoSalva = this.interacaoService.atualizar(codigo, interacao);
		return ResponseEntity.ok(interacaoSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		this.interacaoRepository.deleteById(codigo);
	}

}
