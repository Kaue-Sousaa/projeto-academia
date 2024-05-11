package br.com.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	Categoria findByCategoria(String nome);
}
