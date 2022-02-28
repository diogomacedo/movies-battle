package br.com.diogomacedo.moviesbattle.builders;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;

public class FilmeBuilder {

	private FilmeEntity filmeEntity;

	private FilmeBuilder() {
	}

	public static FilmeBuilder umFilme() {
		FilmeBuilder filmeBuilder = new FilmeBuilder();
		return filmeBuilder;
	}

	public FilmeBuilder aRedeSocial() {
		this.filmeEntity = new FilmeEntity();
		filmeEntity.setId("tt1285016");
		filmeEntity.setTitulo("A Rede Social");
		filmeEntity.setNota(7.7f);
		filmeEntity.setTotalDeVotos(670561);
		return this;
	}

	public FilmeBuilder oCirculo() {
		this.filmeEntity = new FilmeEntity();
		filmeEntity.setId("tt4287320");
		filmeEntity.setTitulo("O Círculo");
		filmeEntity.setAno(2017);
		filmeEntity.setNota(5.4f);
		filmeEntity.setTotalDeVotos(90425);
		return this;
	}

	public FilmeEntity obterFilme() {
		return this.filmeEntity;
	}

}
