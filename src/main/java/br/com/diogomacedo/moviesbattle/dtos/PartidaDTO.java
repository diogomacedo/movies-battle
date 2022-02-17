package br.com.diogomacedo.moviesbattle.dtos;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
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
	private List<RodadaDTO> rodadas;

	@Override
	public String toString() {
		return "PartidaDTO [id=" + id + ", usuario=" + usuario + ", inicio=" + inicio + ", fim=" + fim + ", rodadas="
				+ rodadas + "]";
	}

	public PartidaEntity toEntity() {
		PartidaEntity partida = new PartidaEntity();
		partida.setId(this.id);
		if (!ObjectUtils.isEmpty(this.usuario)) {
			UsuarioEntity usuarioEntity = this.usuario.toEntity();
			partida.setUsuario(usuarioEntity);
		}
		partida.setInicio(this.inicio);
		partida.setFim(this.fim);
		if (!CollectionUtils.isEmpty(this.rodadas)) {
			List<RodadaEntity> rodadas = this.rodadas.stream().map(rodada -> rodada.toEntity()).collect(Collectors.toList());
			partida.setRodadas(rodadas);
		}
		return partida;
	}

}
