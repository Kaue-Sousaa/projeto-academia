package br.com.academia.dto;

import br.com.academia.model.Categoria;

public record CategoriaDto(Integer id, String categoria) {
	public CategoriaDto(Categoria categoria) {
		this(categoria.getId(), categoria.getCategoria());
	}
}
