package br.com.diogomacedo.moviesbattle.builders;

import java.time.Instant;
import java.util.List;

import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;

public class PartidaBuilder {

	private PartidaEntity partidaEntity;

	private PartidaBuilder() {
	}

	public static PartidaBuilder umaPartida() {
		PartidaBuilder partidaBuilder = new PartidaBuilder();
		partidaBuilder.partidaEntity = new PartidaEntity();
		return partidaBuilder;
	}

	public PartidaBuilder comID(Long id) {
		this.partidaEntity.setId(id);
		return this;
	}

	public PartidaBuilder comInicio(Instant inicio) {
		this.partidaEntity.setInicio(inicio);
		return this;
	}

	public PartidaBuilder comFim(Instant fim) {
		this.partidaEntity.setFim(fim);
		return this;
	}

	public PartidaBuilder comUsuario(UsuarioEntity usuario) {
		this.partidaEntity.setUsuario(usuario);
		return this;
	}

	public PartidaBuilder comPontuacao(Float pontuacao) {
		this.partidaEntity.setPontuacao(pontuacao);
		return this;
	}

	public PartidaBuilder comPorcentagemDeAcertos(Float porcentagemDeAcertos) {
		this.partidaEntity.setPorcentagemDeAcertos(porcentagemDeAcertos);
		return this;
	}

	public PartidaBuilder comRodadas(List<RodadaEntity> rodadas) {
		this.partidaEntity.setRodadas(rodadas);
		return this;
	}

	public PartidaEntity obterPartida() {
		return this.partidaEntity;
	}

}
