package br.com.academia.dto;

import br.com.academia.model.SubCategoria;

public record SubCategoriaDto(Integer id, String subCategoria, Integer idCategoria) {
	
	public SubCategoriaDto(SubCategoria entity) {
		this(entity.getId(), entity.getSubCategoria(), entity.getIdCategoria());
	}
}
