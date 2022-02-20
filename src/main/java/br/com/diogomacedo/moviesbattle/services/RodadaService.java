package br.com.diogomacedo.moviesbattle.services;

import org.springframework.data.domain.Page;

import br.com.diogomacedo.moviesbattle.dtos.RodadaDTO;

public interface RodadaService {

	RodadaDTO obter() throws Exception;

	RodadaDTO responder(String filmeEscolhido) throws Exception;

	Page<RodadaDTO> listar(int page, int size);

}
