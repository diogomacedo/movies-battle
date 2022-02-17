package br.com.diogomacedo.moviesbattle.services;

import org.springframework.data.domain.Page;

import br.com.diogomacedo.moviesbattle.dtos.FilmeDTO;

public interface FilmeService {

	Page<FilmeDTO> listar(int page, int size);

}
