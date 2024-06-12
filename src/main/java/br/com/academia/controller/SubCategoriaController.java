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

import br.com.academia.dto.SubCategoriaDto;
import br.com.academia.service.SubCategoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("subCategoria")
public class SubCategoriaController {
	
	private final SubCategoriaService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubCategoriaDto>> buscarTodasSubCategorias(){
		return ResponseEntity.ok(service.buscarTodasSubCategoria());
	}

	@GetMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubCategoriaDto> buscarSubCategoriaPorId(@PathVariable Integer id){
		return ResponseEntity.ok(service.buscarSubCategoriaPorId(id));
	}
	
	@GetMapping(value = "categoria/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubCategoriaDto>> buscarSubCategoriaPorCategoria(@PathVariable Integer id){
		var listSubCategoria = service.buscarSubCategoriaPorCategoria(id);
		if(listSubCategoria.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listSubCategoria);
	}
	
	@PostMapping(value = "cadastro", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> salvarSubCategoria(@RequestBody SubCategoriaDto subCategoria){
		return ResponseEntity.ok(service.salvarSubCategoria(subCategoria));
	}
	
	@PutMapping(value = "atualiza", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> atualizarSubCategoria(@RequestBody SubCategoriaDto subCategoriaDto){
		return ResponseEntity.ok(service.atualizarSubCategoria(subCategoriaDto));
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletarSubCategoria(@PathVariable Integer id) {
		service.deletarSubCategoria(id);
	}
}
