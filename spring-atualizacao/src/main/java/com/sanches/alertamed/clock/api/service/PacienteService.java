package com.sanches.alertamed.clock.api.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sanches.alertamed.clock.api.model.Horario;
import com.sanches.alertamed.clock.api.model.Imagem;
import com.sanches.alertamed.clock.api.model.Paciente;
import com.sanches.alertamed.clock.api.model.Usuario;
import com.sanches.alertamed.clock.api.repository.ImagemRepository;
import com.sanches.alertamed.clock.api.repository.PacienteRepository;
import com.sanches.alertamed.clock.api.repository.UsuarioRepository;
import com.sanches.alertamed.clock.api.repository.projection.ResumoHorario;
import com.sanches.alertamed.clock.api.service.exeception.HorarioInexistenteException;
import com.sanches.alertamed.clock.api.service.exeception.PacienteInexistenteException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ImagemRepository imagemRepository;

	/**
	 * Gera um relatorio em pdf com os medicamentos e horarios de um determinado
	 * paciente
	 * 
	 * @param cpf
	 * @return
	 * @throws Exception
	 */
	public byte[] relatorioHorarios(String cpf) throws Exception {
		Optional<Imagem> imagem = this.imagemRepository.findById(Integer.toUnsignedLong(1));		
		Optional<Paciente> dados = this.pacienteRepository.findByCpf(cpf);
		
		
		List<ResumoHorario> resumo = new ArrayList<>();
		ResumoHorario novoHorario;
		
		for(Horario h: dados.get().getHorarios()) {
			novoHorario = new ResumoHorario(h.getImportante(), h.getAlmoco(), h.getModoUso(), h.getJanta(),
					h.getMedicamento().getNome(), h.getHoraDormir(), h.getCafeManha());
			
			resumo.add(novoHorario);
		}

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		parametros.put("NOME_PACIENTE", dados.get().getNome());
		parametros.put("NOME_USUARIO", "Administrador");
//		parametros.put("CPF_PACIENTE", dados.get().getCpf());
		
		parametros.put("IMAGE", new ByteArrayInputStream(imagem.get().getPic()));

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/arquivoteste.jasper");
//		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/horarios.jasper");

//		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
//				new JRBeanCollectionDataSource(dados.get().getHorarios()));
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(resumo));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	/**
	 * Cria um paciente e um usuario com dados do paciente
	 * 
	 * @param paciente
	 * @return
	 */
	public Paciente salvar(Paciente paciente) {
		this.usuarioService.validarUsuario(paciente.getEmail(), paciente.getCpf());

		paciente.getHorarios().forEach(h -> h.setPaciente(paciente));

		Usuario usuario = this.usuarioService.criarUsuario(paciente);

		this.usuarioService.envioDeAcesso(paciente, usuario.getSenha());
		usuario.setSenha(this.usuarioService.encodar(usuario.getSenha()));

		this.pacienteRepository.save(paciente);
		this.usuarioRepository.save(usuario);

		return paciente;
	}

	public Paciente atualizar(Long codigo, Paciente paciente) {
		Paciente pacienteSalvo = this.buscarPacienteExistente(codigo);

		pacienteSalvo.getHorarios().clear();
		pacienteSalvo.getHorarios().addAll(paciente.getHorarios());
		pacienteSalvo.getHorarios().forEach(h -> h.setPaciente(pacienteSalvo));

		BeanUtils.copyProperties(paciente, pacienteSalvo, "codigo", "horarios");
		return this.pacienteRepository.save(pacienteSalvo);
	}

	/**
	 * Valida se um paciente existe ou se possui horarios cadastrados
	 * 
	 * @param cpf
	 * @return
	 */
	public Boolean validar(String cpf) {
		Optional<Paciente> paciente = this.pacienteRepository.findByCpf(cpf);

		if (!paciente.isPresent()) {
			throw new PacienteInexistenteException();
		} else if (paciente.get().getHorarios().isEmpty()) {
			throw new HorarioInexistenteException();
		}
		return true;
	}

	public void excluir(Long codigo) {
		Paciente pacienteSalvo = this.buscarPacienteExistente(codigo);
		Optional<Usuario> usuarioSalvo = this.usuarioRepository.findByEmail(pacienteSalvo.getCpf());

		this.pacienteRepository.deleteById(codigo);

		if (usuarioSalvo.isPresent()) {
			this.usuarioRepository.deleteById(usuarioSalvo.get().getCodigo());
		}

	}

	private Paciente buscarPacienteExistente(Long codigo) {
		Optional<Paciente> pacienteSalvo = this.pacienteRepository.findById(codigo);

		if (!pacienteSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		} else {
			return pacienteSalvo.get();
		}
	}

}
