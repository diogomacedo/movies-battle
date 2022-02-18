package br.com.diogomacedo.moviesbattle;

import java.time.Instant;

public class DetalhesDaExcecaoPOJO {

	private String titulo;
	private String mensagem;
	private Instant data;

	public DetalhesDaExcecaoPOJO(String titulo, String detalhes, String mensagem, Instant data) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.data = data;
	}

	public DetalhesDaExcecaoPOJO() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DetalhesDaExcecaoPOJO [titulo=" + titulo + ", mensagem=" + mensagem + ", data=" + data + "]";
	}

}
