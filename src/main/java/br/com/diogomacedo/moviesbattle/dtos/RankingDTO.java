package br.com.diogomacedo.moviesbattle.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingDTO {

	private String nomeUsuario;
	private String nomeCompletoUsuario;
	private Float pontuacao;
	private int colocacao;

}
