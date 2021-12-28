package com.sanches.alertamed.clock.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sanches.alertamed.clock.api.exceptionhandler.AlertamedClockExceptionHandler.Erro;
import com.sanches.alertamed.clock.api.model.Paciente;
import com.sanches.alertamed.clock.api.repository.PacienteRepository;
import com.sanches.alertamed.clock.api.repository.filter.PacienteFilter;
import com.sanches.alertamed.clock.api.service.PacienteService;
import com.sanches.alertamed.clock.api.service.exeception.PacienteInexistenteException;

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PACIENTE') and #oauth2.hasScope('read')")
	public Page<Paciente> listar(PacienteFilter filter, Pageable pageable) {
		return this.pacienteRepository.filtrar(filter, pageable);
	}

	@GetMapping("/relatorios")
	public ResponseEntity<byte[]> relatorio(@RequestParam String cpf) throws Exception {
		byte[] relatorio = this.pacienteService.relatorioHorarios(cpf);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Paciente> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Paciente> pacienteSalvo = this.pacienteRepository.findById(codigo);

		return pacienteSalvo.isPresent() ? ResponseEntity.ok(pacienteSalvo.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/paciente-existente")
	public ResponseEntity<Paciente> buscarPeloCPF(@RequestParam String cpf) throws Exception {
		Optional<Paciente> pacienteSalvo = this.pacienteRepository.findByCpf(cpf);

		if (!pacienteSalvo.isPresent()) {
			throw new PacienteInexistenteException();
		}
		return pacienteSalvo.isPresent() ? ResponseEntity.ok(pacienteSalvo.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/validar-paciente")
	public Boolean validarHorarioPaciente(@RequestParam String cpf) throws Exception {
		return this.pacienteService.validar(cpf);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PACIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Paciente> salvar(@Valid @RequestBody Paciente paciente, HttpServletResponse response)
			throws Exception {
		Paciente pacienteSalvo = this.pacienteService.salvar(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Paciente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Paciente paciente) {
		Paciente pacienteSalvo = this.pacienteService.atualizar(codigo, paciente);
		return ResponseEntity.ok(pacienteSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PACIENTE') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		this.pacienteService.excluir(codigo);
	}

	@ExceptionHandler({ PacienteInexistenteException.class })
	public ResponseEntity<Object> handlePacienteInexistenteExceptionException(PacienteInexistenteException ex) {
		String mensagemUsuario = this.messageSource.getMessage("paciente.inexistente", null,
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}
