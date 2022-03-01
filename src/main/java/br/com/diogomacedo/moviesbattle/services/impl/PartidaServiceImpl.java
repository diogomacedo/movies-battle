package br.com.diogomacedo.moviesbattle.services.impl;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.diogomacedo.moviesbattle.components.LoginUtils;
import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;
import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;
import br.com.diogomacedo.moviesbattle.repositories.PartidaRepository;
import br.com.diogomacedo.moviesbattle.services.PartidaService;

@Transactional
@Service
public class PartidaServiceImpl implements PartidaService {

	@Autowired
	private LoginUtils loginUtils;

	@Autowired
	private PartidaRepository partidaRepository;

	@Override
	public PartidaDTO iniciar() {

		UsuarioEntity usuarioEntity = this.loginUtils.obterUsuarioEntity();

		List<PartidaEntity> partidasNaoFinalizadas = this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity);

		if (!CollectionUtils.isEmpty(partidasNaoFinalizadas)) {
			throw new RegraDeNegocioException("Erro ao iniciar uma partida",
					"O usuário '" + usuarioEntity.getNomeUsuario() + "' já iniciou uma partida.");
		}

		Instant horaAtual = Instant.now();

		PartidaEntity partidaEntity = new PartidaEntity();
		partidaEntity.setInicio(horaAtual);
		partidaEntity.setUsuario(usuarioEntity);

		this.partidaRepository.save(partidaEntity);

		return partidaEntity.toDTO();

	}

	@Override
	public PartidaDTO encerrar() throws Exception {

		PartidaEntity partidaAtual = this.obterPartidaAtual();

		return this.encerrar(partidaAtual);

	}

	@Override
	public PartidaDTO encerrar(PartidaEntity partida) throws Exception {

		if (ObjectUtils.isEmpty(partida)) {
			throw new RegraDeNegocioException("Erro ao finalizar uma partida",
					"A partida que está sendo encerrada é inválida.");
		}

		List<RodadaEntity> rodadasDaPartida = partida.getRodadas();

		int qtdeTotalDeRodadas = 0;
		int qtdeRodadasRespondidasCorretamente = 0;

		if (!CollectionUtils.isEmpty(rodadasDaPartida)) {

			List<RodadaEntity> rodadaRespondidasCorretamente = rodadasDaPartida.stream().filter(rodada -> {
				FilmeEntity filmeComMaiorPontuacao = null;
				if (rodada.getFilmeUm().getPontuacao() > rodada.getFilmeDois().getPontuacao()) {
					filmeComMaiorPontuacao = rodada.getFilmeUm();
				} else {
					filmeComMaiorPontuacao = rodada.getFilmeDois();
				}
				if (rodada.getFilmeEscolhido() == filmeComMaiorPontuacao) {
					return true;
				}
				return false;
			}).collect(Collectors.toList());

			qtdeTotalDeRodadas = rodadasDaPartida.size();
			qtdeRodadasRespondidasCorretamente = rodadaRespondidasCorretamente.size();

		}

		float porcentagemAcertos = 0.0f;
		float pontuacao = 0.0f;

		if (qtdeTotalDeRodadas > 0) {
			porcentagemAcertos = ((float) qtdeRodadasRespondidasCorretamente / qtdeTotalDeRodadas) * 100;
			pontuacao = qtdeRodadasRespondidasCorretamente * porcentagemAcertos;
		}

		Instant dataHoraAtual = Instant.now();

		partida.setFim(dataHoraAtual);
		partida.setPorcentagemDeAcertos(porcentagemAcertos);
		partida.setPontuacao(pontuacao);

		this.partidaRepository.save(partida);

		return partida.toDTO();

	}

	@Override
	public PartidaEntity obterPartidaAtual() throws Exception {

		UsuarioEntity usuarioEntity = this.loginUtils.obterUsuarioEntity();

		List<PartidaEntity> partidasNaoFinalizadas = this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity);

		if (CollectionUtils.isEmpty(partidasNaoFinalizadas)) {
			throw new RegraDeNegocioException("Erro ao obter a partida atual",
					"O usuário '" + usuarioEntity.getNomeUsuario() + "' ainda não iniciou uma partida.");
		}

		return partidasNaoFinalizadas.get(0);

	}

	@Override
	public List<PartidaEntity> listarPartidasOrdenadasPorClassificacao() {
		List<PartidaEntity> partidas = this.partidaRepository.findAll();
		Collections.sort(partidas, new Comparator<PartidaEntity>() {
			@Override
			public int compare(PartidaEntity p1, PartidaEntity p2) {
				if (p1.getPontuacao() > p2.getPontuacao()) {
					return -1;
				} else if (p1.getPontuacao() < p2.getPontuacao()) {
					return 1;
				}
				return 0;
			}
		});
		return partidas;
	}

}
