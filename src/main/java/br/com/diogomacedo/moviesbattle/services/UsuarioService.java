package br.com.diogomacedo.moviesbattle.services;

import org.springframework.data.domain.Page;

import br.com.diogomacedo.moviesbattle.dtos.UsuarioDTO;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;

public interface UsuarioService {

	Page<UsuarioDTO> listar(int page, int size);

	UsuarioDTO salvar(UsuarioDTO usuario);

	UsuarioDTO buscar(Long idUsuario);

	UsuarioEntity buscar(String nomeUsuario);

	UsuarioEntity buscar(String nomeUsuario, String senha);

}
