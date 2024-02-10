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
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.dto.CadastroClienteDto;
import br.com.academia.service.CadastroClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
public class CadastroClienteController{

	private final CadastroClienteService cadastroService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CadastroClienteDto> buscarCliente(@PathVariable Integer id) {
		return ResponseEntity.ok(cadastroService.buscarCadastroPorId(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CadastroClienteDto>> buscarTodosCliente() {
		return ResponseEntity.ok(cadastroService.buscarTodosCadastro());
	}
	
	@PostMapping(value = "cadastro",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cadastrarCliente(@RequestBody CadastroClienteDto cadastro) {
		var entity = cadastroService.salvarCadastro(cadastro);
		if(!entity.contains("Cliente cadastrado com sucesso!")) {
			return ResponseEntity.badRequest().body("Erro ao cadastrar cliente");
		}
		return ResponseEntity.ok(entity);
	}
	
	@PutMapping(value = "atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CadastroClienteDto> atualizaCadastroCliente(@RequestBody CadastroClienteDto cadastro) {
		return ResponseEntity.ok(cadastroService.atualizarCadastro(cadastro));
	}
	
	@DeleteMapping(value = "deletar/{id}")
	public void deletarCliente(@PathVariable Integer id) {
		cadastroService.deletarCliente(id);
	}
}