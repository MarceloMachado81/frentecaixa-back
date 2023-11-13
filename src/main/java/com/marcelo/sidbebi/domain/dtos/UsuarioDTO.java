package com.marcelo.sidbebi.domain.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.marcelo.sidbebi.domain.Usuario;
import com.marcelo.sidbebi.domain.enums.Perfil;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected Integer id;
	
	@NotNull(message = "O campo NOME é requerido")
	protected String nome;
	
	@NotNull(message = "O campo EMAIL é requerido")
	protected String email;
	
	@NotNull(message = "O campo SENHA é requerido")
	protected String senha;
	
	protected Set<Integer> perfis = new HashSet<>();
	
	public UsuarioDTO() {
		super();
		addPerfil(Perfil.USUARIO);
	}

	public UsuarioDTO(Usuario obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		addPerfil(Perfil.USUARIO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
