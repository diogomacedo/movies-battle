package br.com.diogomacedo.moviesbattle.dtos;

import java.util.Objects;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDTO {

	private String id;
	private String titulo;
	private Integer ano;

	public FilmeEntity toEntity() {
		FilmeEntity filme = new FilmeEntity();
		filme.setId(this.id);
		filme.setTitulo(this.titulo);
		filme.setAno(this.ano);
		return filme;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmeDTO other = (FilmeDTO) obj;
		return Objects.equals(ano, other.ano) && Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}

}
