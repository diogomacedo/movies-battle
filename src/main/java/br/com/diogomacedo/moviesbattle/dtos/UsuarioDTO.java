package br.com.diogomacedo.moviesbattle.dtos;

import java.time.Instant;

import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	private Long id;
	private String nomeCompleto;
	private String nomeUsuario;
	private Instant dataCadastro;
	private String senha;

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nomeCompleto=" + nomeCompleto + ", nomeUsuario=" + nomeUsuario
				+ ", dataCadastro=" + dataCadastro + ", senha=" + senha + "]";
	}

	public UsuarioEntity toEntity() {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNomeCompleto(this.nomeCompleto);
		usuario.setNomeUsuario(this.nomeUsuario);
		usuario.setSenha(this.senha);
		usuario.setDataCadastro(this.dataCadastro);
		return usuario;
	}

}
