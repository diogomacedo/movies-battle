package br.com.diogomacedo.moviesbattle.dtos;

import java.time.Instant;
import java.util.Objects;

import org.springframework.util.ObjectUtils;

import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartidaDTO {

	private Long id;
	private UsuarioDTO usuario;
	private Instant inicio;
	private Instant fim;
	private Float porcentagemDeAcertos;
	private Float pontuacao;

	public PartidaEntity toEntity() {
		PartidaEntity partida = new PartidaEntity();
		partida.setId(this.id);
		if (!ObjectUtils.isEmpty(this.usuario)) {
			UsuarioEntity usuarioEntity = this.usuario.toEntity();
			partida.setUsuario(usuarioEntity);
		}
		partida.setInicio(this.inicio);
		partida.setFim(this.fim);
		partida.setPorcentagemDeAcertos(this.porcentagemDeAcertos);
		partida.setPontuacao(this.pontuacao);
		return partida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fim, id, inicio, pontuacao, porcentagemDeAcertos, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartidaDTO other = (PartidaDTO) obj;
		return Objects.equals(fim, other.fim) && Objects.equals(id, other.id) && Objects.equals(inicio, other.inicio)
				&& Objects.equals(pontuacao, other.pontuacao)
				&& Objects.equals(porcentagemDeAcertos, other.porcentagemDeAcertos)
				&& Objects.equals(usuario, other.usuario);
	}

}
