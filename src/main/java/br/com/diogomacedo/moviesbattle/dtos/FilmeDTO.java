package br.com.diogomacedo.moviesbattle.dtos;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDTO {

	private String id;
	private String titulo;
	private Integer ano;
	private Float nota;
	private Integer totalDeVotos;
	private Float pontuacao;

	public FilmeEntity toEntity() {
		FilmeEntity filme = new FilmeEntity();
		filme.setId(this.id);
		filme.setTitulo(this.titulo);
		filme.setAno(this.ano);
		filme.setNota(this.nota);
		filme.setTotalDeVotos(this.totalDeVotos);
		filme.setPontuacao(this.pontuacao);
		return filme;
	}

}
