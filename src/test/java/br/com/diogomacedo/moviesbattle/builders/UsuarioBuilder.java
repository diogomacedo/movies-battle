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

	public UsuarioBuilder diogoMacedo() {
		this.usuarioEntity = new UsuarioEntity();
		this.usuarioEntity.setId(300l);
		this.usuarioEntity.setNomeCompleto("DIOGO MACEDO");
		this.usuarioEntity.setNomeUsuario("DIOGOMACEDO01");
		this.usuarioEntity.setSenha("TitiTata99*");
		this.usuarioEntity.setDataCadastro(Instant.now());
		return this;
	}

	public UsuarioBuilder rosemaryRosa() {
		this.usuarioEntity = new UsuarioEntity();
		this.usuarioEntity.setId(100l);
		this.usuarioEntity.setNomeCompleto("ROSEMARY ROSA");
		this.usuarioEntity.setNomeUsuario("ROSEROSA01");
		this.usuarioEntity.setSenha("umaSenhaQualquer");
		this.usuarioEntity.setDataCadastro(Instant.now());
		return this;
	}

	public UsuarioBuilder andersonGois() {
		this.usuarioEntity = new UsuarioEntity();
		this.usuarioEntity.setId(200l);
		this.usuarioEntity.setNomeCompleto("ANDERSON GOIS");
		this.usuarioEntity.setNomeUsuario("andersongois19");
		this.usuarioEntity.setSenha("maxminduim1988");
		this.usuarioEntity.setDataCadastro(Instant.now());
		return this;
	}

	public UsuarioEntity obterUsuario() {
		return this.usuarioEntity;
	}

}
