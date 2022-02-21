package br.com.diogomacedo.moviesbattle.dtos;

import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	private Long id;
	private String nomeCompleto;
	private String nomeUsuario;

	public UsuarioEntity toEntity() {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNomeCompleto(this.nomeCompleto);
		usuario.setNomeUsuario(this.nomeUsuario);
		return usuario;
	}

}
