package br.com.diogomacedo.moviesbattle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;

@Repository
public interface PartidaRepository extends JpaRepository<PartidaEntity, Long> {

}
