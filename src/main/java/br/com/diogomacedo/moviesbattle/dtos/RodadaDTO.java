package br.com.diogomacedo.moviesbattle.dtos;

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

	@Override
	public String toString() {
		return "RodadaDTO [id=" + id + ", partida=" + partida + ", filmeUm=" + filmeUm + ", filmeDois=" + filmeDois
				+ ", filmeEscolhido=" + filmeEscolhido + "]";
	}

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
		return rodada;
	}

}
