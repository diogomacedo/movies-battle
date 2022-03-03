package br.com.diogomacedo.moviesbattle.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.ObjectUtils;

import br.com.diogomacedo.moviesbattle.dtos.RodadaDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_rodadas")
@Getter
@Setter
public class RodadaEntity implements Serializable {

	private static final long serialVersionUID = -4174619982500090950L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_rodada")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_partida")
	private PartidaEntity partida;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_filme_1")
	private FilmeEntity filmeUm;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_filme_2")
	private FilmeEntity filmeDois;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_filme_escolhido")
	private FilmeEntity filmeEscolhido;

	@Column(name = "inicio")
	private Instant inicio;

	@Column(name = "fim")
	private Instant fim;

	public RodadaDTO toDTO() {
		RodadaDTO rodada = new RodadaDTO();
		rodada.setId(this.id);
		if (!ObjectUtils.isEmpty(this.partida)) {
			rodada.setPartida(this.partida.toDTOSimples());
		}
		if (!ObjectUtils.isEmpty(this.filmeUm)) {
			rodada.setFilmeUm(this.filmeUm.toDTO());
		}
		if (!ObjectUtils.isEmpty(this.filmeDois)) {
			rodada.setFilmeDois(this.filmeDois.toDTO());
		}
		if (!ObjectUtils.isEmpty(this.filmeEscolhido)) {
			rodada.setFilmeEscolhido(this.filmeEscolhido.toDTO());
		}
		rodada.setInicio(this.inicio);
		rodada.setFim(this.fim);
		return rodada;
	}

}
