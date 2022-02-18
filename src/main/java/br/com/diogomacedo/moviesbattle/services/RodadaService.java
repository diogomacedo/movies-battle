package br.com.diogomacedo.moviesbattle.services;

import br.com.diogomacedo.moviesbattle.dtos.RodadaDTO;

public interface RodadaService {

	RodadaDTO obter() throws Exception;

	RodadaDTO responder(String filmeEscolhido) throws Exception;

}
