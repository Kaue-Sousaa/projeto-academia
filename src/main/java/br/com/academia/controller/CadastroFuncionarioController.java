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

import br.com.academia.dto.CadastroFuncionarioDto;
import br.com.academia.service.CadastroFuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("funcionario")
@RequiredArgsConstructor
public class CadastroFuncionarioController{
	
	private final CadastroFuncionarioService service; 
	
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CadastroFuncionarioDto> buscarFuncionario(@PathVariable Integer id){
		return ResponseEntity.ok(service.buscarFuncionarioPorId(id));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CadastroFuncionarioDto>> buscarTodosFuncionario(){
		return ResponseEntity.ok(service.buscarTodosFuncionario());
	}
	
	@PostMapping(value = "cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cadastrarFuncionario(@RequestBody CadastroFuncionarioDto cadastroDto){
		var entity = service.salvarCadastro(cadastroDto);
		if(entity != null && entity.contains("Funcionário cadastrado com sucesso!")) {
			return ResponseEntity.ok(entity);
		}
		return ResponseEntity.badRequest().body("Não foi possivel cadastrar funcionário");
	}
	
	@PutMapping(value = "atualizar/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CadastroFuncionarioDto> atualizarFuncionario(@RequestBody CadastroFuncionarioDto cadastroDto){
		return ResponseEntity.ok(service.atualizarCadastro(cadastroDto));
	}
	
	@DeleteMapping(value = "deletar/{id}")
	public void deletarFuncionario(@PathVariable Integer id){
		 service.deletarCliente(id);
	}
}