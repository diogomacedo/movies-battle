package br.com.diogomacedo.moviesbattle.services;

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
import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;
import br.com.diogomacedo.moviesbattle.repositories.FilmeRepository;
import br.com.diogomacedo.moviesbattle.services.impl.FilmeServiceImpl;

public class FilmeSeviceTest {

	@InjectMocks
	private FilmeService filmeService = new FilmeServiceImpl();

	@Mock
	private FilmeRepository filmeRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveObterFilme() {

		FilmeEntity filmeEntity = FilmeBuilder.umFilme().aRedeSocial().obterFilme();

		Mockito.when(this.filmeRepository.findByIdIgnoreCase(Mockito.anyString())).thenReturn(filmeEntity);

		FilmeEntity filmeObtido = this.filmeService.obter("umIDQualquer");

		Assertions.assertEquals(filmeEntity, filmeObtido);

	}

	@Test
	public void naoDeveObterFilmeSeOIDNaoForInformado() {

		FilmeEntity filmeEntity = FilmeBuilder.umFilme().aRedeSocial().obterFilme();

		Mockito.when(this.filmeRepository.findByIdIgnoreCase(Mockito.anyString())).thenReturn(filmeEntity);

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class,
				() -> this.filmeService.obter(""));

		Assertions.assertEquals(exception.getErro().getMensagem(), "O ID de filme '' que foi informado é inválido.");

	}

	@Test
	public void deveObterFilmeAleatorio() {

		FilmeEntity filmeEntity = FilmeBuilder.umFilme().aRedeSocial().obterFilme();

		List<FilmeEntity> filmes = Arrays.asList(filmeEntity);

		Mockito.when(this.filmeRepository.findAll()).thenReturn(filmes);

		FilmeEntity filmeObtido = this.filmeService.obterFilmeAleatorio(null);

		Assertions.assertEquals(filmeEntity, filmeObtido);

	}

	@Test
	public void deveObterFilmeAleatorioIgnorandoLista() {

		FilmeEntity filmeARedeSocial = FilmeBuilder.umFilme().aRedeSocial().obterFilme();
		FilmeEntity filmeOCirculo = FilmeBuilder.umFilme().oCirculo().obterFilme();

		List<String> filmesQueDevemSerIgnorados = Arrays.asList(filmeARedeSocial.getId());

		List<FilmeEntity> filmesRetornados = Arrays.asList(filmeOCirculo);

		Mockito.when(this.filmeRepository.findByIdNotIn(filmesQueDevemSerIgnorados)).thenReturn(filmesRetornados);

		FilmeEntity filmeObtido = this.filmeService.obterFilmeAleatorio(filmesQueDevemSerIgnorados);

		Assertions.assertEquals(filmeOCirculo, filmeObtido);

	}

}
