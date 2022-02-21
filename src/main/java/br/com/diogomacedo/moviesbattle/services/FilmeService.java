package br.com.diogomacedo.moviesbattle.services;

import java.util.List;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;

public interface FilmeService {

	FilmeEntity obterFilmeAleatorio(List<String> excecoes);

	FilmeEntity obter(String id);

}
