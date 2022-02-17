package br.com.diogomacedo.moviesbattle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.diogomacedo.moviesbattle.components.ProvedorDeAutenticacao;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	private ProvedorDeAutenticacao provedorDeAutenticacao;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.provedorDeAutenticacao);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/**").authenticated()
			.antMatchers(HttpMethod.POST, "/**").authenticated()
			.antMatchers(HttpMethod.PUT, "/**").authenticated()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().cors()
			.and().csrf()
			.disable()
			;

	}

}
