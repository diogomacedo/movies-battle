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
import br.com.diogomacedo.moviesbattle.components.LoginUtils;
import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;
import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;
import br.com.diogomacedo.moviesbattle.repositories.PartidaRepository;
import br.com.diogomacedo.moviesbattle.services.impl.PartidaServiceImpl;

public class PartidaServiceTest {

	@InjectMocks
	private PartidaService partidaService = new PartidaServiceImpl();

	@Mock
	private LoginUtils loginUtils;

	@Mock
	private PartidaRepository partidaRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveIniciarPartida() throws Exception {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comUsuario(usuarioEntity).obterPartida();

		Mockito.when(this.loginUtils.obterUsuarioEntity()).thenReturn(usuarioEntity);
		Mockito.when(this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity)).thenReturn(null);
		Mockito.when(this.partidaRepository.save(Mockito.any(PartidaEntity.class))).thenReturn(partidaEntity);

		PartidaDTO partidaIniciada = this.partidaService.iniciar();

		Assertions.assertEquals(partidaEntity.toDTO().getUsuario().getId(), partidaIniciada.getUsuario().getId());

		Mockito.verify(this.partidaRepository, Mockito.times(1)).save(Mockito.any(PartidaEntity.class));

	}

	@Test
	public void naoDeveIniciarPartidaSeHouverPartidasNaoConcluidas() {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comUsuario(usuarioEntity).obterPartida();

		List<PartidaEntity> partidasNaoConcluidas = Arrays.asList(partidaEntity);

		Mockito.when(this.loginUtils.obterUsuarioEntity()).thenReturn(usuarioEntity);
		Mockito.when(this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity)).thenReturn(partidasNaoConcluidas);

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.partidaService.iniciar());

		Assertions.assertEquals(exception.getErro().getMensagem(),
				"O usuário '" + usuarioEntity.getNomeUsuario() + "' já iniciou uma partida.");

	}

	@Test
	public void deveObterAPartidaAtual() throws Exception {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comUsuario(usuarioEntity).obterPartida();

		List<PartidaEntity> partidas = Arrays.asList(partidaEntity);

		Mockito.when(this.loginUtils.obterUsuarioEntity()).thenReturn(usuarioEntity);
		Mockito.when(this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity)).thenReturn(partidas);

		PartidaEntity partidaObtida = this.partidaService.obterPartidaAtual();

		Assertions.assertEquals(partidaEntity, partidaObtida);

	}

	@Test
	public void deveLancarExcecaoSeNaoHouverPartidasIniciadas() {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		Mockito.when(this.loginUtils.obterUsuarioEntity()).thenReturn(usuarioEntity);
		Mockito.when(this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity)).thenReturn(null);

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.partidaService.obterPartidaAtual());

		Assertions.assertEquals(exception.getErro().getMensagem(),
				"O usuário '" + usuarioEntity.getNomeUsuario() + "' ainda não iniciou uma partida.");

	}

	@Test
	public void deveEncerrarPartidaSemRodadas() throws Exception {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comUsuario(usuarioEntity).comRodadas(new ArrayList<RodadaEntity>()).obterPartida();

		Mockito.when(this.partidaRepository.save(Mockito.any(PartidaEntity.class))).thenReturn(partidaEntity);

		PartidaDTO partidaEncerrada = this.partidaService.encerrar(partidaEntity);

		Assertions.assertNotNull(partidaEncerrada.getFim());

		Mockito.verify(this.partidaRepository, Mockito.times(1)).save(Mockito.any(PartidaEntity.class));

	}

	@Test
	public void deveEncerrarPartidaComRodadas() throws Exception {

		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();
		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();
		FilmeEntity filmeARedeSocial = FilmeBuilder.umFilme().aRedeSocial().obterFilme();
		FilmeEntity filmeBladeOCacadorDeVampiros = FilmeBuilder.umFilme().bladeOCacadorDeVampiros().obterFilme();
		FilmeEntity filmeConanOBarbaro = FilmeBuilder.umFilme().conanOBarbaro().obterFilme();
		FilmeEntity filmeOCirculo = FilmeBuilder.umFilme().oCirculo().obterFilme();
		FilmeEntity filmeOExterminadorDoFuturo = FilmeBuilder.umFilme().oExterminadorDoFuturo2OJulgamentoFinal()
				.obterFilme();
		FilmeEntity filmeOSenhorDosAneis = FilmeBuilder.umFilme().oSenhorDosAneisASociedadeDoAnel().obterFilme();
		FilmeEntity filmeSpider = FilmeBuilder.umFilme().spiderDesafieASuaMente().obterFilme();
		FilmeEntity filmeUmLindoDiaNaVizinhanca = FilmeBuilder.umFilme().umLindoDiaNaVizinhanca().obterFilme();

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comUsuario(usuarioEntity).obterPartida();

		RodadaEntity rodada01 = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now()).comFim(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
				.comFilmeEscolhido(filmeABruchaDeBlair).obterRodada();

		RodadaEntity rodada02 = RodadaBuilder.umaRodada().comId(2l).comInicio(Instant.now()).comFim(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeARedeSocial).comFilmeDois(filmeBladeOCacadorDeVampiros)
				.comFilmeEscolhido(filmeARedeSocial).obterRodada();

		RodadaEntity rodada03 = RodadaBuilder.umaRodada().comId(3l).comInicio(Instant.now()).comFim(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeConanOBarbaro).comFilmeDois(filmeOCirculo)
				.comFilmeEscolhido(filmeOCirculo).obterRodada();

		RodadaEntity rodada04 = RodadaBuilder.umaRodada().comId(4l).comInicio(Instant.now()).comFim(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeOExterminadorDoFuturo).comFilmeDois(filmeOSenhorDosAneis)
				.comFilmeEscolhido(filmeOExterminadorDoFuturo).obterRodada();

		RodadaEntity rodada05 = RodadaBuilder.umaRodada().comId(5l).comInicio(Instant.now()).comFim(Instant.now())
				.comPartida(partidaEntity).comFilmeUm(filmeSpider).comFilmeDois(filmeUmLindoDiaNaVizinhanca)
				.comFilmeEscolhido(filmeSpider).obterRodada();

		List<RodadaEntity> rodadas = Arrays.asList(rodada01, rodada02, rodada03, rodada04, rodada05);

		partidaEntity.setRodadas(rodadas);

		Mockito.when(this.partidaRepository.save(Mockito.any(PartidaEntity.class))).thenReturn(partidaEntity);

		PartidaDTO partidaEncerrada = this.partidaService.encerrar(partidaEntity);

		Assertions.assertNotNull(partidaEncerrada.getFim());

		Mockito.verify(this.partidaRepository, Mockito.times(1)).save(Mockito.any(PartidaEntity.class));

	}

	@Test
	public void deveEncerrarPartidaQuandoAPartidaNaoEInformada() throws Exception {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comFim(Instant.now()).comUsuario(usuarioEntity).obterPartida();

		List<PartidaEntity> partidas = Arrays.asList(partidaEntity);

		Mockito.when(this.loginUtils.obterUsuarioEntity()).thenReturn(usuarioEntity);
		Mockito.when(this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity)).thenReturn(partidas);

		PartidaDTO partidaEncerrada = this.partidaService.encerrar();

		Assertions.assertNotNull(partidaEncerrada.getFim());

		Mockito.verify(this.partidaRepository, Mockito.times(1)).save(Mockito.any(PartidaEntity.class));

	}

	@Test
	public void deveLancarExcecaoAoEncerrarPartidaInformandoQueAPartidaENull() {

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.partidaService.encerrar(null));

		Assertions.assertEquals(exception.getErro().getMensagem(), "A partida que está sendo encerrada é inválida.");

	}

	@Test
	public void deveRetornarListaDePartidasOrdenadaPorClassificacao() {

//		FilmeEntity filmeABruchaDeBlair = FilmeBuilder.umFilme().aBruxaDeBlair().obterFilme();
//		FilmeEntity filmeAFamiliaAddams = FilmeBuilder.umFilme().aFamiliaAddams().obterFilme();
//		FilmeEntity filmeARedeSocial = FilmeBuilder.umFilme().aRedeSocial().obterFilme();
//		FilmeEntity filmeBladeOCacadorDeVampiros = FilmeBuilder.umFilme().bladeOCacadorDeVampiros().obterFilme();
//		FilmeEntity filmeConanOBarbaro = FilmeBuilder.umFilme().conanOBarbaro().obterFilme();
//		FilmeEntity filmeOCirculo = FilmeBuilder.umFilme().oCirculo().obterFilme();
//		FilmeEntity filmeOExterminadorDoFuturo = FilmeBuilder.umFilme().oExterminadorDoFuturo2OJulgamentoFinal()
//				.obterFilme();
//		FilmeEntity filmeOSenhorDosAneis = FilmeBuilder.umFilme().oSenhorDosAneisASociedadeDoAnel().obterFilme();
//		FilmeEntity filmeSpider = FilmeBuilder.umFilme().spiderDesafieASuaMente().obterFilme();
//		FilmeEntity filmeUmLindoDiaNaVizinhanca = FilmeBuilder.umFilme().umLindoDiaNaVizinhanca().obterFilme();

		UsuarioEntity usuarioAnaPaula = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		PartidaEntity partidaAnaPaula = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comFim(Instant.now()).comPontuacao(500f).comUsuario(usuarioAnaPaula).obterPartida();

//		RodadaEntity rodadaAna01 = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaAnaPaula).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
//				.comFilmeEscolhido(filmeABruchaDeBlair).obterRodada();
//
//		RodadaEntity rodadaAna02 = RodadaBuilder.umaRodada().comId(2l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaAnaPaula).comFilmeUm(filmeARedeSocial).comFilmeDois(filmeBladeOCacadorDeVampiros)
//				.comFilmeEscolhido(filmeARedeSocial).obterRodada();
//
//		RodadaEntity rodadaAna03 = RodadaBuilder.umaRodada().comId(3l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaAnaPaula).comFilmeUm(filmeConanOBarbaro).comFilmeDois(filmeOCirculo)
//				.comFilmeEscolhido(filmeOCirculo).obterRodada();
//
//		RodadaEntity rodadaAna04 = RodadaBuilder.umaRodada().comId(4l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaAnaPaula).comFilmeUm(filmeOExterminadorDoFuturo).comFilmeDois(filmeOSenhorDosAneis)
//				.comFilmeEscolhido(filmeOExterminadorDoFuturo).obterRodada();
//
//		RodadaEntity rodadaAna05 = RodadaBuilder.umaRodada().comId(5l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaAnaPaula).comFilmeUm(filmeSpider).comFilmeDois(filmeUmLindoDiaNaVizinhanca)
//				.comFilmeEscolhido(filmeSpider).obterRodada();
//
//		List<RodadaEntity> rodadasAnaPaula = Arrays.asList(rodadaAna01, rodadaAna02, rodadaAna03, rodadaAna04,
//				rodadaAna05);
//
//		partidaAnaPaula.setRodadas(rodadasAnaPaula);

		UsuarioEntity usuarioDiogoMacedo = UsuarioBuilder.umUsuario().diogoMacedo().obterUsuario();

		PartidaEntity partidaDiogoMacedo = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comFim(Instant.now()).comPontuacao(900f).comUsuario(usuarioDiogoMacedo).obterPartida();

//		RodadaEntity rodadaDiogo01 = RodadaBuilder.umaRodada().comId(1l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaDiogoMacedo).comFilmeUm(filmeABruchaDeBlair).comFilmeDois(filmeAFamiliaAddams)
//				.comFilmeEscolhido(filmeAFamiliaAddams).obterRodada();
//
//		RodadaEntity rodadaDiogo02 = RodadaBuilder.umaRodada().comId(2l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaDiogoMacedo).comFilmeUm(filmeARedeSocial).comFilmeDois(filmeBladeOCacadorDeVampiros)
//				.comFilmeEscolhido(filmeBladeOCacadorDeVampiros).obterRodada();
//
//		RodadaEntity rodadaDiogo03 = RodadaBuilder.umaRodada().comId(3l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaDiogoMacedo).comFilmeUm(filmeConanOBarbaro).comFilmeDois(filmeOCirculo)
//				.comFilmeEscolhido(filmeConanOBarbaro).obterRodada();
//
//		RodadaEntity rodadaDiogo04 = RodadaBuilder.umaRodada().comId(4l).comInicio(Instant.now()).comFim(Instant.now())
//				.comPartida(partidaDiogoMacedo).comFilmeUm(filmeOExterminadorDoFuturo)
//				.comFilmeDois(filmeOSenhorDosAneis).comFilmeEscolhido(filmeOExterminadorDoFuturo).obterRodada();
//
//		List<RodadaEntity> rodadasDiogoMacedo = Arrays.asList(rodadaDiogo01, rodadaDiogo02, rodadaDiogo03,
//				rodadaDiogo04);
//
//		partidaDiogoMacedo.setRodadas(rodadasDiogoMacedo);

		UsuarioEntity usuarioRosemaryRosa = UsuarioBuilder.umUsuario().rosemaryRosa().obterUsuario();

		PartidaEntity partidaRosemaryRosa = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comFim(Instant.now()).comPontuacao(700f).comUsuario(usuarioRosemaryRosa).obterPartida();

		UsuarioEntity usuarioAndersonGois = UsuarioBuilder.umUsuario().andersonGois().obterUsuario();

		PartidaEntity partidaAndersonGois = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now())
				.comFim(Instant.now()).comPontuacao(700f).comUsuario(usuarioAndersonGois).obterPartida();

		List<PartidaEntity> partidas = Arrays.asList(partidaAndersonGois, partidaAnaPaula, partidaDiogoMacedo,
				partidaRosemaryRosa);

		List<PartidaEntity> partidasNaOrdemEsperada = Arrays.asList(partidaDiogoMacedo, partidaRosemaryRosa,
				partidaAndersonGois, partidaAnaPaula);

		Mockito.when(this.partidaRepository.findAll()).thenReturn(partidas);

		List<PartidaEntity> partidasOrdenadas = this.partidaService.listarPartidasOrdenadasPorClassificacao();

		Assertions.assertEquals(partidasNaOrdemEsperada, partidasOrdenadas);

	}

}
