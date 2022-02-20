package br.com.diogomacedo.moviesbattle.services;

import org.springframework.data.domain.Page;

import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;
import br.com.diogomacedo.moviesbattle.entities.PartidaEntity;

public interface PartidaService {

	PartidaDTO iniciar() throws Exception;

	PartidaDTO encerrar() throws Exception;

	PartidaDTO encerrar(PartidaEntity partida) throws Exception;

	PartidaEntity obterPartidaAtual() throws Exception;

	Page<PartidaDTO> listar(int page, int size);

}
