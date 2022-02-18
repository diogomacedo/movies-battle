package br.com.diogomacedo.moviesbattle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, String> {

	FilmeEntity findByIdIgnoreCase(String id);

	List<FilmeEntity> findByIdNotIn(List<String> ids);

}
