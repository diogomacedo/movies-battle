package br.com.diogomacedo.moviesbattle.builders;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;

public class FilmeBuilder {

	private FilmeEntity filmeEntity;

	private FilmeBuilder() {
	}

	public static FilmeBuilder umFilme() {
		FilmeBuilder filmeBuilder = new FilmeBuilder();
		filmeBuilder.filmeEntity = new FilmeEntity();
		return filmeBuilder;
	}

	public FilmeBuilder aRedeSocial() {
		filmeEntity.setId("tt1285016");
		filmeEntity.setTitulo("A Rede Social");
		filmeEntity.setNota(7.7f);
		filmeEntity.setTotalDeVotos(670561);
		return this;
	}

	public FilmeEntity obterFilme() {
		return filmeEntity;
	}

}
