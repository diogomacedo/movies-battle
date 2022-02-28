package br.com.diogomacedo.moviesbattle.services;

import java.time.Instant;
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
import br.com.diogomacedo.moviesbattle.components.LoginUtils;
import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
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

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comID(1l).comInicio(Instant.now())
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

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comID(1l).comInicio(Instant.now())
				.comUsuario(usuarioEntity).obterPartida();

		List<PartidaEntity> partidasNaoConcluidas = Arrays.asList(partidaEntity);

		Mockito.when(this.loginUtils.obterUsuarioEntity()).thenReturn(usuarioEntity);
		Mockito.when(this.partidaRepository.findByUsuarioAndFimIsNull(usuarioEntity)).thenReturn(partidasNaoConcluidas);

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.partidaService.iniciar());

		Assertions.assertEquals(exception.getErro().getMensagem(),
				"O usuário '" + usuarioEntity.getNomeUsuario() + "' já iniciou uma partida.");

	}

}
