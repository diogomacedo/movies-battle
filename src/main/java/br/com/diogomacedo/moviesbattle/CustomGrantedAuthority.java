package br.com.diogomacedo.moviesbattle;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -7966930904616711005L;

	@Override
	public String getAuthority() {
		return null;
	}

}
