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

import br.com.academia.dto.CategoriaDto;
import br.com.academia.service.CategoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {
	private final CategoriaService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoriaDto>> buscarTodasCategoria(){
		return ResponseEntity.ok(service.bucasTodasCategoria());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscarCategoriaPorId(id));
	}
	
	@PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> salvarCategoria(@RequestBody CategoriaDto categoriaDto){
		var categoria = service.salvarCategoria(categoriaDto);
		if(categoria.contains("Cadastro bem sucedido!")) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.badRequest().body("Erro ao cadastrar categoria! Entre em contato com o suporte");
	}
	
	@PutMapping(value = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriaDto> atualizarCategoria(@RequestBody CategoriaDto categoriaDto){
		return ResponseEntity.ok(service.atualizarCategoria(categoriaDto));
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletarCategoria(@PathVariable Integer id) {
		service.excluirCategoria(id);
	}
} 