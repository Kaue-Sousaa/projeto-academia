package br.com.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.dto.CategoriaDto;
import br.com.academia.exception.ExistingObject;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.Categoria;
import br.com.academia.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	public List<CategoriaDto> bucasTodasCategoria(){
		return categoriaRepository.findAll().stream().map(CategoriaDto::new).toList();
	}
	
	public CategoriaDto buscarCategoriaPorId(Integer id) {
		return new CategoriaDto(categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada")));
	}
	
	public String salvarCategoria(CategoriaDto categoriaDto) {
		var categoria = categoriaRepository.findByCategoria(categoriaDto.categoria());
		if(categoria != null) {
			throw new ExistingObject("Categoria já cadastrada!");
		}else {
			categoriaRepository.save(new Categoria(categoriaDto));
			return "Cadastro bem sucedido!";
		}
	}
	
	public CategoriaDto atualizarCategoria(CategoriaDto categoriaDto) {
		var categoria = categoriaRepository.findById(categoriaDto.id())
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
		
		categoria.setCategoria(categoriaDto.categoria());
		categoriaRepository.save(categoria);
		return new CategoriaDto(categoria);
	}
	
	public void excluirCategoria(Integer id) {
		var categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
		categoriaRepository.delete(categoria);
	}
}