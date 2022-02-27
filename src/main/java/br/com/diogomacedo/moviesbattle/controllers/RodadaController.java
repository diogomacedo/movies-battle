package br.com.diogomacedo.moviesbattle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogomacedo.moviesbattle.dtos.RodadaDTO;
import br.com.diogomacedo.moviesbattle.services.RodadaService;

@RestController()
@RequestMapping("rodadas")
public class RodadaController {

	@Autowired
	private RodadaService rodadaService;

	@ResponseBody
	@GetMapping("responder")
	public RodadaDTO proxima() throws Exception {
		RodadaDTO rodada = this.rodadaService.obter();
		return rodada;
	}

	@ResponseBody
	@PostMapping("responder")
	public RodadaDTO responder(@RequestParam(value = "filmeEscolhido") String filmeEscolhido) throws Exception {
		RodadaDTO rodadaDTO = this.rodadaService.responder(filmeEscolhido);
		return rodadaDTO;
	}

}
