package br.com.diogomacedo.moviesbattle.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.ObjectUtils;

import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;
import br.com.diogomacedo.moviesbattle.dtos.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_partidas")
@Getter
@Setter
public class PartidaEntity implements Serializable {

	private static final long serialVersionUID = 8515055459874110204L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_partida")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuario;

	@Column(name = "inicio")
	private Instant inicio;

	@Column(name = "fim")
	private Instant fim;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
	private List<RodadaEntity> rodadas;

	@Column(name = "porcentagem_acertos")
	private Float porcentagemDeAcertos;

	@Column
	private Float pontuacao;

	@Override
	public String toString() {
		return "PartidaEntity [id=" + id + ", usuario=" + usuario + ", inicio=" + inicio + ", fim=" + fim + ", rodadas="
				+ rodadas + ", porcentagemDeAcertos=" + porcentagemDeAcertos + ", pontuacao=" + pontuacao + "]";
	}

	public PartidaDTO toDTO() {
		PartidaDTO partida = new PartidaDTO();
		partida.setId(this.id);
		if (!ObjectUtils.isEmpty(this.usuario)) {
			UsuarioDTO usuarioDTO = this.usuario.toDTO();
			partida.setUsuario(usuarioDTO);
		}
		partida.setInicio(this.inicio);
		partida.setFim(this.fim);
		partida.setPorcentagemDeAcertos(this.porcentagemDeAcertos);
		partida.setPontuacao(this.pontuacao);
		return partida;
	}

	public PartidaDTO toDTOSimples() {
		PartidaDTO partida = new PartidaDTO();
		partida.setId(this.id);
		if (!ObjectUtils.isEmpty(this.usuario)) {
			UsuarioDTO usuarioDTO = this.usuario.toDTO();
			partida.setUsuario(usuarioDTO);
		}
		partida.setInicio(this.inicio);
		partida.setFim(this.fim);
		partida.setPorcentagemDeAcertos(this.porcentagemDeAcertos);
		partida.setPontuacao(this.pontuacao);
		return partida;
	}

}
