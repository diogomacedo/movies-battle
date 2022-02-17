package br.com.diogomacedo.moviesbattle.components;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class LoginUtils {

	public String obterUsuarioLogado() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!ObjectUtils.isEmpty(authentication) && !ObjectUtils.isEmpty(authentication.getPrincipal())) {

			Object principal = authentication.getPrincipal();

			String usuarioLogado = (String) principal;

			return usuarioLogado;

		}

		return null;

	}

}
