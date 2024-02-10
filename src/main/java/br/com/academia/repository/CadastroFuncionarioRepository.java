package br.com.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academia.model.CadastroFuncionario;

public interface CadastroFuncionarioRepository extends JpaRepository<CadastroFuncionario, Integer>{
	
	@Query(value = """ 
			SELECT cpf FROM academia.cadastro_funcionario
			WHERE cpf = :cpf
			""", nativeQuery = true)
	String findByCpf(String cpf);
}
