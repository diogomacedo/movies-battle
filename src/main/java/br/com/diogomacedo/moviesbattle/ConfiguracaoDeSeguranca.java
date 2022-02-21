package br.com.diogomacedo.moviesbattle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
			.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/api-docs/**").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().headers().frameOptions().disable()
			.and().cors()
			.and().csrf()
			.disable();

	}

}
