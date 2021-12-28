package com.sanches.alertamed.clock.api.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sanches.alertamed.clock.api.mail.Mailer;
import com.sanches.alertamed.clock.api.model.Paciente;
import com.sanches.alertamed.clock.api.model.Permissao;
import com.sanches.alertamed.clock.api.model.TipoUsuario;
import com.sanches.alertamed.clock.api.model.Usuario;
import com.sanches.alertamed.clock.api.repository.PacienteRepository;
import com.sanches.alertamed.clock.api.repository.UsuarioRepository;
import com.sanches.alertamed.clock.api.service.exeception.CpfExistenteException;
import com.sanches.alertamed.clock.api.service.exeception.EmailExistenteException;

@Service
public class UsuarioService {

	private static final Integer PERMISSAO = 11; // ROLE_PESQUISAR_PACIENTE

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private Mailer mailer;

	/**
	 * Cria um usuario comum com permissao para leitura de pacientes
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario salvar(Usuario usuario) {
		validarUsuario(usuario.getEmail(), usuario.getEmail());

		Permissao permissao = new Permissao();
		permissao.setCodigo(Integer.toUnsignedLong(PERMISSAO));

		usuario.setPermissoes(Arrays.asList(permissao));
		usuario.setTipo(TipoUsuario.COMUM);

		usuario.setSenha(encodar(usuario.getSenha()));
		return this.usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalvo = this.buscarUsuarioExistente(codigo);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo", "senha");

		return this.usuarioRepository.save(usuarioSalvo);
	}

	/**
	 * Envia um e-mail para o paciente cadastro, contendo o nome e a senha no corpo
	 * da mensagem. O CPF e a senha do paciente serão utilizados para fazer login no
	 * sistema
	 * 
	 * @param paciente
	 * @param senha
	 */
	public void envioDeAcesso(Paciente paciente, String senha) {
		if (logger.isDebugEnabled()) {
			logger.debug("Preparando envio de e-mails ");
		}
		this.mailer.enviarAcesso(paciente, senha);

		logger.info("Envido de e-mail de aviso concluído.");
	}

	/**
	 * Cria um usuario para ser utilizado pelo paciente para leitura dos horarios
	 * cadastrados
	 * 
	 * @param paciente
	 * @return
	 */
	public Usuario criarUsuario(Paciente paciente) {
		Permissao permissao = new Permissao();
		permissao.setCodigo(Integer.toUnsignedLong(PERMISSAO));

		Usuario usuario = new Usuario();

		usuario.setNome(paciente.getNome());
		usuario.setEmail(paciente.getCpf());
		usuario.setSenha(gerarSenha());
		usuario.setTipo(TipoUsuario.PACIENTE);
		usuario.setPermissoes(Arrays.asList(permissao));

		return usuario;
	}

	/**
	 * Valida se o email ou cpf existe na base de dados
	 * 
	 * @param email
	 * @param cpf
	 */
	public void validarUsuario(String email, String cpf) {
		Optional<Usuario> usuarioByEmail = this.usuarioRepository.findByEmail(email);
		Optional<Paciente> pacienteByEmail = this.pacienteRepository.findByEmail(email);

		if (usuarioByEmail.isPresent() || pacienteByEmail.isPresent()) {
			throw new EmailExistenteException();
		}

		Optional<Usuario> usuarioByCpf = this.usuarioRepository.findByEmail(cpf);
		Optional<Paciente> pacienteByCpf = this.pacienteRepository.findByCpf(cpf);

		if (usuarioByCpf.isPresent() || pacienteByCpf.isPresent()) {
			throw new CpfExistenteException();
		}
	}

	/**
	 * Gera uma senha aleatoria utilizando valores da tabela ascii
	 * 
	 * @return
	 */
	private String gerarSenha() {
		char senha[] = new char[12];
		int[] proibidos = new int[] { 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 92, 93, 94, 95,
				96 };

		for (int i = 0; i < senha.length; i++) {
			senha[i] = aleatoriar(35, 122, proibidos);
		}
		return String.valueOf(senha);
	}

	/**
	 * Gera um valor aleatorio entre os intervalos
	 * 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	private char aleatoriar(int minimo, int maximo, int[] proibidos) {
		Random random = new Random();
		int valor;
		boolean aux;

		while (true) {
			valor = (random.nextInt((maximo - minimo) + 1) + minimo);
			aux = true;

			for (int num : proibidos) {
				if (valor == num) {
					aux = false;
					break;
				}
			}
			if (aux) {
				return (char) valor;
			}
		}
	}

	/**
	 * Encripta o valor passado
	 * 
	 * @param valor
	 * @return
	 */
	public String encodar(String valor) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(valor);
	}

	private Usuario buscarUsuarioExistente(Long codigo) {
		Optional<Usuario> usuarioSalvo = this.usuarioRepository.findById(codigo);

		if (!usuarioSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		} else {
			return usuarioSalvo.get();
		}
	}

	public void excluir(Long codigo) {
		Usuario usuario = this.buscarUsuarioExistente(codigo);
		Optional<Paciente> pacienteSalvo = this.pacienteRepository.findByCpf(usuario.getEmail());

		this.usuarioRepository.deleteById(codigo);

		if (pacienteSalvo.isPresent()) {
			this.pacienteRepository.deleteById(pacienteSalvo.get().getCodigo());
		}
	}
}
