package br.com.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.dto.SubCategoriaDto;
import br.com.academia.model.SubCategoria;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer>{
	
	SubCategoria findBySubCategoria(String subCategoria);
	
	List<SubCategoriaDto> findByIdCategoria(Integer idCategoria);
}
