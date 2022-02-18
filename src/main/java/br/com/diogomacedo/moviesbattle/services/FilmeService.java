package br.com.diogomacedo.moviesbattle.services;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.diogomacedo.moviesbattle.dtos.FilmeDTO;
import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;

public interface FilmeService {

	Page<FilmeDTO> listar(int page, int size);

	FilmeEntity obterFilmeAleatorio(List<String> excecoes);

	FilmeEntity obter(String id);

}
