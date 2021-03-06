package br.com.diogomacedo.moviesbattle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	UsuarioEntity findByNomeUsuarioIgnoreCase(String nomeUsuario);

	UsuarioEntity findByNomeUsuarioIgnoreCaseAndSenha(String nomeUsuario, String senha);

}
