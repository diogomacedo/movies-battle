package br.com.diogomacedo.moviesbattle.dtos;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(id, nomeCompleto, nomeUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeCompleto, other.nomeCompleto)
				&& Objects.equals(nomeUsuario, other.nomeUsuario);
	}

}
