package br.com.academia.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController{

	private final UsuarioService cadastroService;
	
	@GetMapping
	public ResponseEntity<List<CadastroUsuarioDto>> buscarTodosUsuario() {
		return ResponseEntity.ok(cadastroService.buscarTodosCadastro());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CadastroUsuarioDto> buscarUsuario(@PathVariable Integer id) {
		return ResponseEntity.ok(cadastroService.buscarCadastroPorId(id));
	}
	
	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CadastroUsuarioDto> buscarUsuarioPorEmail(@RequestParam String email) {
		return ResponseEntity.ok(cadastroService.buscarUsarioPorEmail(email));
	}
	
	@PostMapping(value = "cadastro",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cadastrarUsuario(@RequestBody CadastroUsuarioDto cadastro) {
		var entity = cadastroService.salvarCadastro(cadastro);
		if(!entity.contains("Usuário cadastrado com sucesso!")) {
			return ResponseEntity.badRequest().body("Erro ao cadastrar usuário");
		}
		return ResponseEntity.ok(entity);
	}
	
	@PutMapping(value = "atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CadastroUsuarioDto> atualizaCadastroCliente(@RequestBody CadastroUsuarioDto cadastro) {
		return ResponseEntity.ok(cadastroService.atualizarCadastro(cadastro));
	}
	
	@DeleteMapping(value = "excluir/{id}")
	public void deletarUsuario(@PathVariable Integer id) {
		cadastroService.deletarUsuario(id);
	}
}