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
import br.com.diogomacedo.moviesbattle.dtos.RodadaDTO;
import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;
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
	public void deveObterUmaRodada() throws Exception {

		PartidaEntity partidaEntity = PartidaBuilder.umaPartida().comId(1l).comInicio(Instant.now()).obterPartida();

		Mockito.when(this.partidaService.obterPartidaAtual()).thenReturn(partidaEntity);

		Mockito.when(this.rodadaRepository.findByPartida(Mockito.any(PartidaEntity.class))).thenReturn(new ArrayList());

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

		RodadaDTO rodadaDTO = this.rodadaService.obter();

		Assertions.assertAll("Campos", () -> {
			Assertions.assertEquals(filmeABruchaDeBlair.getId(), rodadaDTO.getFilmeUm().getId());
			Assertions.assertEquals(filmeAFamiliaAddams.getId(), rodadaDTO.getFilmeDois().getId());
		});

		Mockito.verify(this.rodadaRepository, Mockito.times(1)).save(Mockito.any(RodadaEntity.class));

	}

}
