package br.com.diogomacedo.moviesbattle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;

@Repository
public interface PartidaRepository extends JpaRepository<PartidaEntity, Long> {

	List<PartidaEntity> findByUsuarioAndFimIsNull(UsuarioEntity usuario);

}
