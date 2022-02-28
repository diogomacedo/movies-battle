package br.com.diogomacedo.moviesbattle.builders;

import java.time.Instant;

import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;

public class UsuarioBuilder {

	private UsuarioEntity usuarioEntity;

	private UsuarioBuilder() {
	}

	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
		usuarioBuilder.usuarioEntity = new UsuarioEntity();
		return usuarioBuilder;
	}

	public UsuarioBuilder anaPaula() {
		this.usuarioEntity = new UsuarioEntity();
		this.usuarioEntity.setId(400l);
		this.usuarioEntity.setNomeCompleto("ANA PAULA");
		this.usuarioEntity.setNomeUsuario("PAULANA");
		this.usuarioEntity.setSenha("P4ul4n4");
		this.usuarioEntity.setDataCadastro(Instant.now());
		return this;
	}

	public UsuarioEntity obterUsuario() {
		return this.usuarioEntity;
	}

}
