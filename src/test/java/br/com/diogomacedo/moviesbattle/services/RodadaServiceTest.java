package br.com.diogomacedo.moviesbattle.services;

import java.time.Instant;
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

import br.com.diogomacedo.moviesbattle.builders.FilmeBuilder;
import br.com.diogomacedo.moviesbattle.builders.PartidaBuilder;
import br.com.diogomacedo.moviesbattle.builders.RodadaBuilder;
import br.com.diogomacedo.moviesbattle.builders.UsuarioBuilder;
import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;
import br.com.diogomacedo.moviesbattle.dtos.RodadaDTO;
import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;
import br.com.diogomacedo.moviesbattle.repositories.RodadaRepository;
import br.com.diogomacedo.moviesbattle.services.impl.RodadaServiceImpl;

public class RodadaServiceTest {

	@InjectMocks
	private RodadaService rodadaService = new RodadaServiceImpl();

	@Mock
	private PartidaService partidaService;

	@Mock
	private FilmeService filmeService;

	@Mock
	private RodadaRepository rodadaRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveObterUmaRodadaNova() throws Exception {

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now()).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		ArrayList<RodadaEntity> rodadas = new ArrayList<RodadaEntity>();

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();

		Mockito.when(this.filmeService.obterFilmeAleatorio(null)).thenReturn(filmeABruchaDeBlair);

		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();

		List<String> idsDeFilmesQueNaoDevemSerRetornados = Arrays.asList(filmeABruchaDeBlair.getId());

		Mockito.when(this.filmeService.obterFilmeAleatorio(idsDeFilmesQueNaoDevemSerRetornados))
				.thenReturn(filmeAFamiliaAddams);

		RodadaEntity rodadaEntity = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
				.obterRodada();

		Mockito.when(this.rodadaRepository.save(Mockito.any(RodadaEntity.class))).thenReturn(rodadaEntity);

		RodadaDTO rodadaDTOObtida = this.rodadaService.obter();

		Assertions.assertAll(() -> {
			Assertions.assertEquals(filmeABruchaDeBlair.getId(), rodadaDTOObtida.getFilmeUm().getId());
			Assertions.assertEquals(filmeAFamiliaAddams.getId(), rodadaDTOObtida.getFilmeDois().getId());
		});

		Mockito.verify(this.rodadaRepository, Mockito.times(1)).save(Mockito.any(RodadaEntity.class));

	}

	@Test
	public void deveObterUmaRodadaPendente() throws Exception {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comUsuario(usuarioEntity).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();

		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();

		RodadaEntity rodadaEntity = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
				.obterRodada();

		List<RodadaEntity> rodadas = Arrays.asList(rodadaEntity);

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		RodadaDTO rodadaDTOObtida = this.rodadaService.obter();

		RodadaDTO rodadaDTO = rodadaEntity.toDTO();

		Assertions.assertAll(() -> {
			Assertions.assertEquals(rodadaDTO.getId(), rodadaDTOObtida.getId());
			Assertions.assertEquals(rodadaDTO.getFilmeUm(), rodadaDTOObtida.getFilmeUm());
			Assertions.assertEquals(rodadaDTO.getFilmeDois(), rodadaDTOObtida.getFilmeDois());
			Assertions.assertNull(rodadaDTOObtida.getFim());
		});

	}

	@Test
	public void deveLancarExcecaoAoObterDuasRodadasPendentes() throws Exception {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comUsuario(usuarioEntity)
				.comInicio(Instant.now()).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();

		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();

		FilmeEntity filmeARedeSocial = FilmeBuilder.umFilme().aRedeSocial().obterFilme();

		RodadaEntity rodadaEntity01 = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
				.obterRodada();

		RodadaEntity rodadaEntity02 = RodadaBuilder.umaRodada().comId(2l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeARedeSocial).obterRodada();

		List<RodadaEntity> rodadas = Arrays.asList(rodadaEntity01, rodadaEntity02);

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.rodadaService.obter());

		Assertions.assertEquals(exception.getErro().getMensagem(),
				"O usuário '" + partidaEntity.getUsuario().getNomeUsuario()
						+ "' possui duas rodadas em andamento para a partida atual .");

	}

	@Test
	public void deveResponderRodadaPendenteCorretamente() throws Exception {

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now()).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();

		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();

		RodadaEntity rodadaEntity = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
				.obterRodada();

		List<RodadaEntity> rodadas = Arrays.asList(rodadaEntity);

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		Mockito.when(this.filmeService.obter(Mockito.anyString())).thenReturn(filmeAFamiliaAddams);

		RodadaDTO rodadaRespondida = this.rodadaService.responder(filmeAFamiliaAddams.getId());

		Assertions.assertAll(() -> {
			Assertions.assertNotNull(rodadaRespondida.getFilmeEscolhido());
			Assertions.assertNotNull(rodadaRespondida.getFim());
		});

	}

	@Test
	public void deveResponderRodadaPendenteErradamenteSemFinalizarPartida() throws Exception {

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now()).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();

		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();

		RodadaEntity rodadaEntity = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeAFamiliaAddams).comFilmeDois(filmeABruchaDeBlair)
				.obterRodada();

		List<RodadaEntity> rodadas = Arrays.asList(rodadaEntity);

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		Mockito.when(this.filmeService.obter(Mockito.anyString())).thenReturn(filmeABruchaDeBlair);

		RodadaDTO rodadaRespondida = this.rodadaService.responder(filmeABruchaDeBlair.getId());

		Assertions.assertAll(() -> {
			Assertions.assertNotNull(rodadaRespondida.getFilmeEscolhido());
			Assertions.assertNotNull(rodadaRespondida.getFim());
		});

	}

	@Test
	public void deveResponderRodadaPendenteErradamenteFinalizandoAPartida() throws Exception {

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now()).obterPartida();
		PartidaDTO partidaDTO = partidaEntity.toDTO();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();
		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();

		FilmeEntity filmeARedeSocial = FilmeBuilder.umFilme().aRedeSocial().obterFilme();
		FilmeEntity filmeBladeOCacadorDeVampiros = FilmeBuilder.umFilme().bladeOCacadorDeVampiros().obterFilme();

		FilmeEntity filmeConanOBarbaro = FilmeBuilder.umFilme().conanOBarbaro().obterFilme();
		FilmeEntity filmeOCirculo = FilmeBuilder.umFilme().oCirculo().obterFilme();

		RodadaEntity rodadaEntity01 = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now()).comFim(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeAFamiliaAddams).comFilmeDois(filmeABruchaDeBlair)
				.comFilmeEscolhido(filmeAFamiliaAddams).obterRodada();

		RodadaEntity rodadaEntity02 = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now()).comFim(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeARedeSocial).comFilmeDois(filmeBladeOCacadorDeVampiros)
				.comFilmeEscolhido(filmeBladeOCacadorDeVampiros).obterRodada();

		RodadaEntity rodadaEntity03 = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeConanOBarbaro).comFilmeDois(filmeOCirculo).obterRodada();

		List<RodadaEntity> rodadas = Arrays.asList(rodadaEntity01, rodadaEntity02, rodadaEntity03);

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		Mockito.when(this.filmeService.obter(Mockito.anyString())).thenReturn(filmeOCirculo);

		Mockito.when(this.partidaService.encerrar(partidaEntity)).thenReturn(partidaDTO);

		RodadaDTO rodadaRespondida = this.rodadaService.responder(filmeOCirculo.getId());

		Assertions.assertAll(() -> {
			Assertions.assertNotNull(rodadaRespondida.getFilmeEscolhido());
			Assertions.assertNotNull(rodadaRespondida.getFim());
		});

	}

	@Test
	public void deveLancarExcecaoAoResponderRodadaSemInformarOFilme() {

		String filmeEscolhido = null;

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.rodadaService.responder(filmeEscolhido));

		Assertions.assertEquals(exception.getErro().getMensagem(),
				"O ID de filme informado ('" + filmeEscolhido + "') é inválido.");

	}

	@Test
	public void deveLancarExcecaoAoResponderRodadaJaRespondida() throws Exception {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comUsuario(usuarioEntity)
				.comInicio(Instant.now()).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		List<RodadaEntity> rodadas = new ArrayList<RodadaEntity>();

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		FilmeEntity filmeEscolhidoABruxaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.rodadaService.responder(filmeEscolhidoABruxaDeBlair.getId()));

		Assertions.assertEquals(exception.getErro().getMensagem(),
				"O usuário '" + partidaEntity.getUsuario().getNomeUsuario()
						+ "' não possui rodada pendente de resposta para a partida atual .");

	}

	@Test
	public void deveLancarExcecaoAoResponderRodadaComIDDeFilmeInvalido() throws Exception {

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now()).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();

		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();

		RodadaEntity rodadaEntity = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
				.obterRodada();

		List<RodadaEntity> rodadas = Arrays.asList(rodadaEntity);

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(rodadas);

		FilmeEntity filmeEscolhido = FilmeBuilder.umFilme().aRedeSocial().obterFilme();

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.rodadaService.responder(filmeEscolhido.getId()));

		Assertions.assertEquals(exception.getErro().getMensagem(),
				"O ID de filme informado ('" + filmeEscolhido.getId() + "') não é uma opção válida.");

	}

}
