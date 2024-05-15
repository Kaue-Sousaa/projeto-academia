package br.com.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.dto.SubCategoriaDto;
import br.com.academia.exception.ExistingObject;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.SubCategoria;
import br.com.academia.repository.SubCategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubCategoriaService {
	
	private final SubCategoriaRepository repository;
	
	public List<SubCategoriaDto> buscarTodasSubCategoria(){
		return repository.findAll().stream().map(SubCategoriaDto::new).toList();
	}
	
	public SubCategoriaDto buscarSubCategoriaPorId(Integer id) {
		var subCategoria = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SubCategoria não encontrada"));
		return new SubCategoriaDto(subCategoria);
	}
	
	public String salvarSubCategoria(SubCategoriaDto subCategoria) {
		var entity = repository.findBySubCategoria(subCategoria.subCategoria());
		if(entity != null) {
			throw new ExistingObject("SubCategoria já cadastrada!");
		}
		repository.save(new SubCategoria(subCategoria));
		return "SubCategoria cadastrada com sucesso!";
	}
	
	public String atualizarSubCategoria(SubCategoriaDto subCategoriaDto) {
		var entity = repository.findById(subCategoriaDto.id())
				.orElseThrow(() -> new ResourceNotFoundException("SubCategoria não encontrada"));
		entity.setSubCategoria(subCategoriaDto.subCategoria());
		repository.save(entity);
		return "SubCategoria atualizada com sucesso!";
	}
	
	public void deletarSubCategoria(Integer id) {
		var subCategoria = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SubCategoria não encontrada"));
		repository.delete(subCategoria);
	}
}