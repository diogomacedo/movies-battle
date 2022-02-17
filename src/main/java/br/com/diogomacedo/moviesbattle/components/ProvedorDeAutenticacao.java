package br.com.diogomacedo.moviesbattle.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.diogomacedo.moviesbattle.CustomGrantedAuthority;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.services.UsuarioService;

@Component
public class ProvedorDeAutenticacao implements AuthenticationProvider {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		if (authentication.getCredentials() != null &&
			StringUtils.hasLength(authentication.getName()) &&
			StringUtils.hasLength(authentication.getCredentials().toString())) {

			String usuario = authentication.getName();
			String senha = authentication.getCredentials().toString();

			UsuarioEntity usuarioEntity = this.usuarioService.buscar(usuario, senha);

			if (usuarioEntity != null) {
				List<CustomGrantedAuthority> permissoes = new ArrayList<CustomGrantedAuthority>();
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario, senha, permissoes);
				return token;
			}

		}

		throw new UsernameNotFoundException("Usuário e/ou Senha inválidos.");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
