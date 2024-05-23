package br.com.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query(value = """ 
			SELECT * FROM academia.cadastro_usuario
			WHERE cpf = :cpf
			""", nativeQuery = true)
	Usuario findByCpf(String cpf);

	Usuario findByEmail(String email);
}
