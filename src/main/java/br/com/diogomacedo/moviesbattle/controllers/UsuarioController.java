package br.com.diogomacedo.moviesbattle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogomacedo.moviesbattle.dtos.UsuarioDTO;
import br.com.diogomacedo.moviesbattle.entities.UsuarioEntity;
import br.com.diogomacedo.moviesbattle.services.UsuarioService;

@RestController()
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@ResponseBody
	@GetMapping
	public Page<UsuarioDTO> listar() {
		Page<UsuarioDTO> usuarios = this.service.listar(1, 10);
		return usuarios;
	}

	@ResponseBody
	@GetMapping("/{id}")
	public UsuarioDTO buscar(@PathVariable(value = "id") Long idUsuario) {
		UsuarioDTO usuario = this.service.buscar(idUsuario);
		return usuario;
	}

	@ResponseBody
	@GetMapping("/login")
	public Boolean login(@RequestBody UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = this.service.buscar(usuario.getNomeUsuario(), usuario.getSenha());
		if (usuarioEntity != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@PostMapping
	public UsuarioDTO salvar(@RequestBody UsuarioDTO usuario) {
		UsuarioDTO usuarioSalvo = this.service.salvar(usuario);
		return usuarioSalvo;
	}

}
