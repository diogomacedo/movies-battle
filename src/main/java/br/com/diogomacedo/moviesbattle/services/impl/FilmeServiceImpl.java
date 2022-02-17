package br.com.diogomacedo.moviesbattle.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.diogomacedo.moviesbattle.dtos.FilmeDTO;
import br.com.diogomacedo.moviesbattle.repositories.FilmeRepository;
import br.com.diogomacedo.moviesbattle.services.FilmeService;

@Service
public class FilmeServiceImpl implements FilmeService {

	@Autowired
	private FilmeRepository repository;

	@Override
	public Page<FilmeDTO> listar(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return this.repository.findAll(pageRequest).map(u -> u.toDTO());
	}

}
