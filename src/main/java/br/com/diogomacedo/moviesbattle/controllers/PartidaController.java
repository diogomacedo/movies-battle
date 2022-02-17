package br.com.diogomacedo.moviesbattle.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogomacedo.moviesbattle.dtos.PartidaDTO;

@RestController()
@RequestMapping("partidas")
public class PartidaController {

	@ResponseBody
	@PostMapping("/iniciar")
	public Page<PartidaDTO> iniciar() {
		return null;
	}

}
