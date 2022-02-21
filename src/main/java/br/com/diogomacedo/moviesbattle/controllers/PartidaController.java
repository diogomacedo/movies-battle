package br.com.diogomacedo.moviesbattle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;
import br.com.diogomacedo.moviesbattle.services.PartidaService;

@RestController()
@RequestMapping("partidas")
public class PartidaController {

	@Autowired
	private PartidaService service;

	@ResponseBody
	@PostMapping("/iniciar")
	public PartidaDTO iniciar() throws Exception {
		PartidaDTO partidaDTO = this.service.iniciar();
		return partidaDTO;
	}

	@ResponseBody
	@PostMapping("/encerrar")
	public PartidaDTO encerrar() throws Exception {
		PartidaDTO partidaDTO = this.service.encerrar();
		return partidaDTO;
	}

}
