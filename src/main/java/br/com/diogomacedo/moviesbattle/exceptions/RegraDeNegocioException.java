package br.com.diogomacedo.moviesbattle.exceptions;

import java.time.Instant;

import br.com.diogomacedo.moviesbattle.DetalhesDaExcecaoPOJO;

public class RegraDeNegocioException extends RuntimeException {

	private static final long serialVersionUID = 83161697892089729L;

	private DetalhesDaExcecaoPOJO erro;

	public RegraDeNegocioException(String titulo, String mensagem) {
		this.erro = new DetalhesDaExcecaoPOJO();
		this.erro.setTitulo(titulo);
		this.erro.setMensagem(mensagem);
		this.erro.setData(Instant.now());
	}

	public DetalhesDaExcecaoPOJO getErro() {
		return erro;
	}

}
