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
	private Float pontuacao;
	private Integer totalDeVotos;

	@Override
	public String toString() {
		return "FilmeDTO [id=" + id + ", titulo=" + titulo + ", ano=" + ano + ", pontuacao=" + pontuacao
				+ ", totalDeVotos=" + totalDeVotos + "]";
	}

	public FilmeEntity toEntity() {
		FilmeEntity filme = new FilmeEntity();
		filme.setId(this.id);
		filme.setTitulo(this.titulo);
		filme.setAno(this.ano);
		filme.setPontuacao(this.pontuacao);
		filme.setTotalDeVotos(this.totalDeVotos);
		return filme;
	}

}
