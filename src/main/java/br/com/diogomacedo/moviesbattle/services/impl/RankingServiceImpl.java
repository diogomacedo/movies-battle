package br.com.diogomacedo.moviesbattle.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.diogomacedo.moviesbattle.dtos.RankingDTO;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.services.PartidaService;
import br.com.diogomacedo.moviesbattle.services.RankingService;

@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	private PartidaService partidaService;

	@Override
	public List<RankingDTO> listar() {
		List<RankingDTO> listaRanking = new ArrayList<RankingDTO>();
		List<PartidaEntity> partidasOrdenadas = this.partidaService.listarPartidasOrdenadasPorClassificacao();
		if (!CollectionUtils.isEmpty(partidasOrdenadas)) {
			for (int posicao = 0; posicao < partidasOrdenadas.size(); posicao++) {
				PartidaEntity partida = partidasOrdenadas.get(posicao);
				RankingDTO ranking = new RankingDTO();
				ranking.setNomeUsuario(partida.getUsuario().getNomeUsuario());
				ranking.setNomeCompletoUsuario(partida.getUsuario().getNomeCompleto());
				ranking.setPontuacao(partida.getPorcentagemDeAcertos());
				ranking.setColocacao(posicao + 1);
				listaRanking.add(ranking);
			}
		}
		return listaRanking;
	}

}
