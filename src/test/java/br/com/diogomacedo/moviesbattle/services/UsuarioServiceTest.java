package br.com.diogomacedo.moviesbattle.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.diogomacedo.moviesbattle.builders.UsuarioBuilder;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.repositories.UsuarioRepository;
import br.com.diogomacedo.moviesbattle.services.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService = new UsuarioServiceImpl();

	@Mock
	private UsuarioRepository usuarioRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveBuscarUsuarioPorNomeUsuario() {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		Mockito.when(this.usuarioRepository.findByNomeUsuarioIgnoreCase(usuarioEntity.getNomeUsuario()))
				.thenReturn(usuarioEntity);

		UsuarioEntity usuarioObtido = this.usuarioService.buscar(usuarioEntity.getNomeUsuario());

		Assertions.assertEquals(usuarioEntity, usuarioObtido);

	}

	@Test
	public void deveBuscarUsuarioPorNomeUsuarioESenha() {

		UsuarioEntity usuarioEntity = UsuarioBuilder.umUsuario().anaPaula().obterUsuario();

		Mockito.when(
				this.usuarioRepository.findByNomeUsuarioIgnoreCaseAndSenha(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(usuarioEntity);

		UsuarioEntity usuarioObtido = this.usuarioService.buscar(usuarioEntity.getNomeUsuario(),
				usuarioEntity.getSenha());

		Assertions.assertEquals(usuarioEntity, usuarioObtido);

	}

}
