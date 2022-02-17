package br.com.diogomacedo.moviesbattle.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.diogomacedo.moviesbattle.dtos.FilmeDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_filmes")
@Getter
@Setter
public class FilmeEntity implements Serializable {

	private static final long serialVersionUID = 1417670882468102534L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_filme")
	private String id;

	@Column
	private String titulo;

	@Column
	private Integer ano;

	@Column
	private Float pontuacao;

	@Column(name = "total_de_votos")
	private Integer totalDeVotos;

	@Override
	public String toString() {
		return "FilmeEntity [id=" + id + ", titulo=" + titulo + ", ano=" + ano + ", pontuacao=" + pontuacao
				+ ", totalDeVotos=" + totalDeVotos + "]";
	}

	public FilmeDTO toDTO() {
		FilmeDTO filme = new FilmeDTO();
		filme.setId(this.id);
		filme.setTitulo(this.titulo);
		filme.setAno(this.ano);
		filme.setPontuacao(this.pontuacao);
		filme.setTotalDeVotos(this.totalDeVotos);
		return filme;
	}

}
