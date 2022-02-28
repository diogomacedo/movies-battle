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
		this.filmeEntity.setId("tt1285016");
		this.filmeEntity.setTitulo("A Rede Social");
		this.filmeEntity.setNota(7.7f);
		this.filmeEntity.setTotalDeVotos(670561);
		return this;
	}

	public FilmeBuilder oCirculo() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt4287320");
		this.filmeEntity.setTitulo("O Círculo");
		this.filmeEntity.setAno(2017);
		this.filmeEntity.setNota(5.4f);
		this.filmeEntity.setTotalDeVotos(90425);
		return this;
	}

	public FilmeBuilder bladeOCacadorDeVampiros() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt0120611");
		this.filmeEntity.setTitulo("Blade, O Caçador de Vampiros");
		this.filmeEntity.setAno(1998);
		this.filmeEntity.setNota(7.1f);
		this.filmeEntity.setTotalDeVotos(261993);
		return this;
	}

	public FilmeBuilder oSenhorDosAneisASociedadeDoAnel() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt0120737");
		this.filmeEntity.setTitulo("O Senhor dos Anéis: A Sociedade do Anel");
		this.filmeEntity.setAno(2001);
		this.filmeEntity.setNota(8.8f);
		this.filmeEntity.setTotalDeVotos(1767565);
		return this;
	}

	public FilmeBuilder aFamiliaAddams() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt0101272");
		this.filmeEntity.setTitulo("A Família Addams");
		this.filmeEntity.setAno(1991);
		this.filmeEntity.setNota(6.9f);
		this.filmeEntity.setTotalDeVotos(149234);
		return this;
	}

	public FilmeBuilder aBruxaDeBlair() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt0185937");
		this.filmeEntity.setTitulo("A Bruxa de Blair");
		this.filmeEntity.setAno(1999);
		this.filmeEntity.setNota(6.5f);
		this.filmeEntity.setTotalDeVotos(249044);
		return this;
	}

	public FilmeBuilder umLindoDiaNaVizinhanca() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt3224458");
		this.filmeEntity.setTitulo("Um Lindo Dia Na Vizinhança");
		this.filmeEntity.setAno(2019);
		this.filmeEntity.setNota(7.3f);
		this.filmeEntity.setTotalDeVotos(74067);
		return this;
	}

	public FilmeBuilder oExterminadorDoFuturo2OJulgamentoFinal() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt0103064");
		this.filmeEntity.setTitulo("O Exterminador Do Futuro 2: O Julgamento Final");
		this.filmeEntity.setAno(1991);
		this.filmeEntity.setNota(8.5f);
		this.filmeEntity.setTotalDeVotos(1051761);
		return this;
	}

	public FilmeBuilder spiderDesafieASuaMente() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt0278731");
		this.filmeEntity.setTitulo("Spider: Desafie A Sua Mente");
		this.filmeEntity.setAno(2002);
		this.filmeEntity.setNota(6.8f);
		this.filmeEntity.setTotalDeVotos(38279);
		return this;
	}

	public FilmeBuilder conanOBarbaro() {
		this.filmeEntity = new FilmeEntity();
		this.filmeEntity.setId("tt0082198");
		this.filmeEntity.setTitulo("Conan, O Bárbaro");
		this.filmeEntity.setAno(1982);
		this.filmeEntity.setNota(6.9f);
		this.filmeEntity.setTotalDeVotos(144905);
		return this;
	}

	public FilmeEntity obterFilme() {
		return this.filmeEntity;
	}

}
