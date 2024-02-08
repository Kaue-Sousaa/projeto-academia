package br.com.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academia.model.CadastroCliente;

public interface CadastroClienteRepository extends JpaRepository<CadastroCliente, Integer>{
	
	@Query(value = """ 
			SELECT cpf FROM academia.cadastro_cliente
			WHERE cpf = :cpf
			""", nativeQuery = true)
	String findByCpf(String cpf);
}
