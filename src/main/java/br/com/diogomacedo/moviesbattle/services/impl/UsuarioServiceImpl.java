package br.com.diogomacedo.moviesbattle.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.repositories.UsuarioRepository;
import br.com.diogomacedo.moviesbattle.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UsuarioEntity buscar(String nomeUsuario) {
		UsuarioEntity usuario = this.repository.findByNomeUsuarioIgnoreCase(nomeUsuario);
		return usuario;
	}

	@Override
	public UsuarioEntity buscar(String nomeUsuario, String senha) {
		UsuarioEntity usuario = this.repository.findByNomeUsuarioIgnoreCaseAndSenha(nomeUsuario, senha);
		return usuario;
	}

}
