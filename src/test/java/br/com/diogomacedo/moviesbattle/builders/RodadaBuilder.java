package br.com.diogomacedo.moviesbattle.builders;

import java.time.Instant;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;

public class RodadaBuilder {

	private RodadaEntity rodadaEntity;

	public static RodadaBuilder umaRodada() {
		RodadaBuilder rodadaBuilder = new RodadaBuilder();
		rodadaBuilder.rodadaEntity = new RodadaEntity();
		return rodadaBuilder;
	}

	public RodadaBuilder comId(Long id) {
		this.rodadaEntity.setId(id);
		return this;
	}

	public RodadaBuilder comInicio(Instant inicio) {
		this.rodadaEntity.setInicio(inicio);
		return this;
	}

	public RodadaBuilder comFim(Instant fim) {
		this.rodadaEntity.setFim(fim);
		return this;
	}

	public RodadaBuilder comPartida(PartidaEntity partida) {
		this.rodadaEntity.setPartida(partida);
		return this;
	}

	public RodadaBuilder comFilmeUm(FilmeEntity filme) {
		this.rodadaEntity.setFilmeUm(filme);
		return this;
	}

	public RodadaBuilder comFilmeDois(FilmeEntity filme) {
		this.rodadaEntity.setFilmeDois(filme);
		return this;
	}

	public RodadaBuilder comFilmeEscolhido(FilmeEntity filme) {
		this.rodadaEntity.setFilmeEscolhido(filme);
		return this;
	}

	public RodadaEntity obterRodada() {
		return this.rodadaEntity;
	}

}
