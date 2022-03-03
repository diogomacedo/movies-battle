package br.com.diogomacedo.moviesbattle.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import br.com.diogomacedo.moviesbattle.dtos.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
public class UsuarioEntity implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = 1099413388761522762L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "nome_completo")
	private String nomeCompleto;

	@Column(name = "nome_usuario", unique = true)
	private String nomeUsuario;

	@Column
	private String senha;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<PartidaEntity> partidas;

	@Column(name = "data_cadastro")
	private Instant dataCadastro;

	@Transient
	private boolean entidadeNova = true;

	@Override
	public boolean isNew() {
		return this.entidadeNova;
	}

	@PrePersist
	@PostLoad
	void markNotNew() {
		this.entidadeNova = false;
	}

	public UsuarioDTO toDTO() {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(this.id);
		usuario.setNomeCompleto(this.nomeCompleto);
		usuario.setNomeUsuario(this.nomeUsuario);
		return usuario;
	}

}
