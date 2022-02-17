package br.com.diogomacedo.moviesbattle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogomacedo.moviesbattle.dtos.FilmeDTO;
import br.com.diogomacedo.moviesbattle.services.FilmeService;

@RestController()
@RequestMapping("filmes")
public class FilmeController {

	@Autowired
	private FilmeService service;

	@ResponseBody
	@GetMapping
	public Page<FilmeDTO> listar() {
		Page<FilmeDTO> filmes = this.service.listar(0, 10);
		return filmes;
	}

}
