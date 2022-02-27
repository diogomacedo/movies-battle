package br.com.diogomacedo.moviesbattle.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.diogomacedo.moviesbattle.entities.FilmeEntity;
import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;
import br.com.diogomacedo.moviesbattle.repositories.FilmeRepository;
import br.com.diogomacedo.moviesbattle.services.FilmeService;

@Service
public class FilmeServiceImpl implements FilmeService {

	@Autowired
	private FilmeRepository repository;

	@Override
	public FilmeEntity obterFilmeAleatorio(List<String> excecoes) {
		List<FilmeEntity> filmes = null;
		if (excecoes != null) {
			filmes = this.repository.findByIdNotIn(excecoes);
		} else {
			filmes = this.repository.findAll();
		}
		int quantidadeDeFilmes = filmes.size() - 1;
		double numeroAleatorio = Math.random();
		double numeroEscolhido = numeroAleatorio * quantidadeDeFilmes;
		int numero = (int) Math.round(numeroEscolhido);
		return filmes.get(numero);
	}

	@Override
	public FilmeEntity obter(String id) throws RegraDeNegocioException {

		if (!StringUtils.hasLength(id)) {
			throw new RegraDeNegocioException("ID de filme inválido",
					"O ID de filme '" + id + "' que foi informado é inválido.");
		}

		FilmeEntity filmeEntity = this.repository.findByIdIgnoreCase(id);

		return filmeEntity;

	}

}
