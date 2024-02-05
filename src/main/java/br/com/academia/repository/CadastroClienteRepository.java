package br.com.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.model.CadastroCliente;

public interface CadastroClienteRepository extends JpaRepository<CadastroCliente, Integer>{

}
