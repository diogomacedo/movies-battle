package br.com.diogomacedo.moviesbattle.services;

import java.util.List;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;

public interface FilmeService {

	FilmeEntity obterFilmeAleatorio(List<String> excecoes);

	FilmeEntity obter(String id) throws RegraDeNegocioException;

}
