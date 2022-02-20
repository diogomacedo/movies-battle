package br.com.diogomacedo.moviesbattle.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private Float nota;

	@Column(name = "total_de_votos")
	private Integer totalDeVotos;

	@Transient
	private Float pontuacao;

	public Float getPontuacao() {
		return this.nota * this.totalDeVotos;
	}

	@Override
	public String toString() {
		return "FilmeEntity [id=" + id + ", titulo=" + titulo + ", ano=" + ano + ", nota=" + nota + ", totalDeVotos="
				+ totalDeVotos + ", pontuacao=" + pontuacao + "]";
	}

	public FilmeDTO toDTO() {
		FilmeDTO filme = new FilmeDTO();
		filme.setId(this.id);
		filme.setTitulo(this.titulo);
		filme.setAno(this.ano);
		filme.setNota(this.nota);
		filme.setTotalDeVotos(this.totalDeVotos);
		filme.setPontuacao(this.getPontuacao());
		return filme;
	}

}
