package br.com.diogomacedo.moviesbattle.services.impl;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.com.diogomacedo.moviesbattle.dtos.RodadaDTO;
import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;
import br.com.diogomacedo.moviesbattle.repositories.RodadaRepository;
import br.com.diogomacedo.moviesbattle.services.FilmeService;
import br.com.diogomacedo.moviesbattle.services.PartidaService;
import br.com.diogomacedo.moviesbattle.services.RodadaService;

@Transactional
@Service
public class RodadaServiceImpl implements RodadaService {

	@Autowired
	private PartidaService partidaService;

	@Autowired
	private FilmeService filmeService;

	@Autowired
	private RodadaRepository repository;

	@Override
	public RodadaDTO obter() throws Exception {

		PartidaEntity partidaAtual = this.partidaService.obterPartidaAtual();

		List<RodadaEntity> rodadas = this.repository.findByPartidaAndFilmeEscolhidoIsNull(partidaAtual);

		if (!CollectionUtils.isEmpty(rodadas)) {

			if (rodadas.size() > 1) {
				throw new RegraDeNegocioException("Erro ao obter a rodada atual",
						"O usuário '" + partidaAtual.getUsuario().getNomeUsuario()
								+ "' possui duas rodadas em andamento para a partida atual .");
			}

			return rodadas.get(0).toDTO();

		}

		RodadaEntity rodada = new RodadaEntity();

		Instant dataHoraAtual = Instant.now();

		rodada.setPartida(partidaAtual);
		rodada.setInicio(dataHoraAtual);

		FilmeEntity filmeUm = this.filmeService.obterFilmeAleatorio(null);
		FilmeEntity filmeDois = this.filmeService.obterFilmeAleatorio(Arrays.asList(filmeUm.getId()));

		rodada.setFilmeUm(filmeUm);
		rodada.setFilmeDois(filmeDois);

		this.repository.save(rodada);

		return rodada.toDTO();

	}

	@Override
	public RodadaDTO responder(String filmeEscolhido) throws Exception {

		if (!StringUtils.hasLength(filmeEscolhido)) {
			throw new RegraDeNegocioException("Erro ao salvar a resposta da rodada atual",
					"O ID de filme informado ('" + filmeEscolhido + "') é inválido.");
		}

		PartidaEntity partidaAtual = this.partidaService.obterPartidaAtual();

		List<RodadaEntity> rodadas = this.repository.findByPartidaAndFilmeEscolhidoIsNull(partidaAtual);

		if (CollectionUtils.isEmpty(rodadas)) {
			throw new RegraDeNegocioException("Erro ao responder a rodada atual",
					"O usuário '" + partidaAtual.getUsuario().getNomeUsuario()
							+ "' não possui rodada pendente de resposta para a partida atual .");
		}

		RodadaEntity rodadaEntity = rodadas.get(0);

		if (!filmeEscolhido.equalsIgnoreCase(rodadaEntity.getFilmeUm().getId())
				&& !filmeEscolhido.equalsIgnoreCase(rodadaEntity.getFilmeDois().getId())) {
			throw new RegraDeNegocioException("Erro ao salvar a resposta da rodada atual",
					"O ID de filme informado ('" + filmeEscolhido + "') não é uma opção válida.");
		}

		FilmeEntity filmeEntity = this.filmeService.obter(filmeEscolhido);

		rodadaEntity.setFilmeEscolhido(filmeEntity);

		return rodadaEntity.toDTO();

	}

}
