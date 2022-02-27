package br.com.diogomacedo.moviesbattle.services;

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

		Assertions.assertEquals(filmeObtido, filmeEntity);

	}

	@Test
	public void naoDeveObterFilmeSeOIDNaoForInformado() {

		FilmeEntity filmeEntity = FilmeBuilder.umFilme().aRedeSocial().obterFilme();

		Mockito.when(this.filmeRepository.findByIdIgnoreCase(Mockito.anyString())).thenReturn(filmeEntity);

		RegraDeNegocioException exception = Assertions.assertThrows(RegraDeNegocioException.class, () -> {
			this.filmeService.obter("");
		});

		Assertions.assertEquals(exception.getErro().getMensagem(), "O ID de filme '' que foi informado é inválido.");

	}

}
