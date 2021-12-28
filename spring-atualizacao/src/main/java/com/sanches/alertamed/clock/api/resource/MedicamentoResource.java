package com.sanches.alertamed.clock.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sanches.alertamed.clock.api.model.Medicamento;
import com.sanches.alertamed.clock.api.repository.MedicamentoRepository;
import com.sanches.alertamed.clock.api.repository.filter.MedicamentoFilter;
import com.sanches.alertamed.clock.api.repository.projection.ResumoMedicamento;


@RestController
@RequestMapping("/medicamentos")
public class MedicamentoResource {
	
	@Autowired
	private MedicamentoRepository mRepository;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICAMENTO') and #oauth2.hasScope('read')")
	public List<Medicamento> listar() {
		return this.mRepository.findAll();
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Medicamento> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Medicamento> medicamentoSalvo = this.mRepository.findById(codigo);

		return medicamentoSalvo.isPresent() ? ResponseEntity.ok(medicamentoSalvo.get())
				: ResponseEntity.notFound().build();
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoMedicamento> resumir(MedicamentoFilter filter, Pageable pageable) {
		return this.mRepository.resumir(filter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Medicamento> salvar(@Valid @RequestBody Medicamento medicamento,
			HttpServletResponse response) {
		Medicamento medicamentoSalvo = this.mRepository.save(medicamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICAMENTO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		this.mRepository.deleteById(codigo);
	}

}
