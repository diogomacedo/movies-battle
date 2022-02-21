package br.com.diogomacedo.moviesbattle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogomacedo.moviesbattle.dtos.RankingDTO;
import br.com.diogomacedo.moviesbattle.services.RankingService;

@RestController()
@RequestMapping("ranking")
public class RankingController {

	@Autowired
	private RankingService rankingService;

	@ResponseBody
	@GetMapping
	public List<RankingDTO> listar() {
		List<RankingDTO> ranking = this.rankingService.listar();
		return ranking;
	}

}
