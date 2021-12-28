package com.sanches.alertamed.clock.api.repository.projection;

import java.util.List;

import com.sanches.alertamed.clock.api.model.Permissao;
import com.sanches.alertamed.clock.api.model.TipoUsuario;

public class ResumoUsuario {

	private Long codigo;
	private String nome;
	private String email;
	private TipoUsuario tipo;
	private List<Permissao> permissoes;

	public ResumoUsuario(Long codigo, String nome, String email, TipoUsuario tipo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
	}

//	public ResumoUsuario(Long codigo, String nome, String email, TipoUsuario tipo, List<Permissao> permissoes) {
//		super();
//		this.codigo = codigo;
//		this.nome = nome;
//		this.email = email;
//		this.tipo = tipo;
//		this.permissoes = permissoes;
//	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

}
