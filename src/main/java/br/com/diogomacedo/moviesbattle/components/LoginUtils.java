package br.com.diogomacedo.moviesbattle.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import br.com.diogomacedo.moviesbattle.dtos.UsuarioDTO;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.services.UsuarioService;

@Component
public class LoginUtils {

	@Autowired
	private UsuarioService usuarioService;

	private String obterUsuarioLogado() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!ObjectUtils.isEmpty(authentication) && !ObjectUtils.isEmpty(authentication.getPrincipal())) {

			Object principal = authentication.getPrincipal();

			String usuarioLogado = (String) principal;

			return usuarioLogado;

		}

		return null;

	}

	public UsuarioEntity obterUsuarioEntity() {
		String usuarioLogado = this.obterUsuarioLogado();
		UsuarioEntity usuarioEntity = this.usuarioService.buscar(usuarioLogado);
		return usuarioEntity;
	}

	public UsuarioDTO obterUsuarioDTO() {
		String usuarioLogado = this.obterUsuarioLogado();
		UsuarioEntity usuarioEntity = this.usuarioService.buscar(usuarioLogado);
		return usuarioEntity.toDTO();
	}

}
