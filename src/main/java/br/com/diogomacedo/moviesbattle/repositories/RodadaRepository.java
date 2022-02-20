package br.com.diogomacedo.moviesbattle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;
import br.com.diogomacedo.moviesbattle.entities.RodadaEntity;

@Repository
public interface RodadaRepository extends JpaRepository<RodadaEntity, Long> {

	List<RodadaEntity> findByPartida(PartidaEntity partida);

	List<RodadaEntity> findByPartidaAndFilmeEscolhidoIsNullAndFimIsNull(PartidaEntity partida);

}
