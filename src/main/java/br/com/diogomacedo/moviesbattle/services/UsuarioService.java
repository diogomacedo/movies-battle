package br.com.diogomacedo.moviesbattle.services;

import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;

public interface UsuarioService {

	UsuarioEntity buscar(String nomeUsuario);

	UsuarioEntity buscar(String nomeUsuario, String senha);

}
