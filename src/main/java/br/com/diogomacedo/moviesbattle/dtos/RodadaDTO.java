package br.com.diogomacedo.moviesbattle.dtos;

import java.time.Instant;

import org.springframework.util.ObjectUtils;

import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RodadaDTO {

	private Long id;
	private PartidaDTO partida;
	private FilmeDTO filmeUm;
	private FilmeDTO filmeDois;
	private FilmeDTO filmeEscolhido;
	private Instant inicio;
	private Instant fim;

	public RodadaEntity toEntity() {
		RodadaEntity rodada = new RodadaEntity();
		rodada.setId(this.id);
		if (!ObjectUtils.isEmpty(this.partida)) {
			rodada.setPartida(this.partida.toEntity());
		}
		if (!ObjectUtils.isEmpty(this.filmeUm)) {
			rodada.setFilmeUm(this.filmeUm.toEntity());
		}
		if (!ObjectUtils.isEmpty(this.filmeDois)) {
			rodada.setFilmeDois(this.filmeDois.toEntity());
		}
		rodada.setInicio(this.inicio);
		rodada.setFim(this.fim);
		return rodada;
	}

}
