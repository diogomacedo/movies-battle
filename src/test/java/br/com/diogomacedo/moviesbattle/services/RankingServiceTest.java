package br.com.diogomacedo.moviesbattle.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.diogomacedo.moviesbattle.builders.PartidaBuilder;
import br.com.diogomacedo.moviesbattle.builders.UsuarioBuilder;
import br.com.diogomacedo.moviesbattle.dtos.RankingDTO;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.services.impl.RankingServiceImpl;

public class RankingServiceTest {

	@InjectMocks
	private RankingService rankingService = new RankingServiceImpl();

	@Mock
	private PartidaService partidaService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveListarRankingQuandoHaPeloMenosUmaPartidaConcluida() {

		UsuarioEntity usuarioAnaPaula = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaAnaPaula = PartidaBuilder.umaPartida().comId(1l).comUsuario(usuarioAnaPaula)
				.comPontuacao(1100f).comPorcentagemDeAcertos(50f).obterPartida();

		UsuarioEntity usuarioDiogo = UsuarioBuilder.umUsuario().diogoMacedo().obterUsuario();

		PartidaEntity partidaDiogo = PartidaBuilder.umaPartida().comId(2l).comUsuario(usuarioDiogo).comPontuacao(750f)
				.comPorcentagemDeAcertos(40f).obterPartida();

		UsuarioEntity usuarioAnderson = UsuarioBuilder.umUsuario().andersonGois().obterUsuario();

		PartidaEntity partidaAnderson = PartidaBuilder.umaPartida().comId(3l).comUsuario(usuarioAnderson)
				.comPontuacao(980f).comPorcentagemDeAcertos(55.7f).obterPartida();

		UsuarioEntity usuarioRosemary = UsuarioBuilder.umUsuario().rosemaryRosa().obterUsuario();

		PartidaEntity partidaRosemary = PartidaBuilder.umaPartida().comId(3l).comUsuario(usuarioRosemary)
				.comPontuacao(670f).comPorcentagemDeAcertos(59.1f).obterPartida();

		List<PartidaEntity> partidasOrdenadas = Arrays.asList(partidaAnaPaula, partidaAnderson, partidaDiogo,
				partidaRosemary);

		Mockito.when(this.partidaService.listarPartidasOrdenadasPorClassificacao()).thenReturn(partidasOrdenadas);

		List<RankingDTO> ranking = this.rankingService.listar();

		Assertions.assertNotNull(ranking);

	}

	@Test
	public void deveListarRankingQuandoNaoHaPartidas() {

		List<PartidaEntity> partidasOrdenadas = new ArrayList<PartidaEntity>();

		Mockito.when(this.partidaService.listarPartidasOrdenadasPorClassificacao()).thenReturn(partidasOrdenadas);

		List<RankingDTO> ranking = this.rankingService.listar();

		List<RankingDTO> rankingDTO = new ArrayList<RankingDTO>();

		Assertions.assertEquals(rankingDTO, ranking);

	}

}
