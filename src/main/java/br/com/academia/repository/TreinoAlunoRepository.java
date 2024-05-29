package br.com.academia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academia.model.TreinoAluno;

public interface TreinoAlunoRepository extends JpaRepository<TreinoAluno, Integer> {
	
	@Query(value = """
			SELECT * FROM academia.treino_aluno
			WHERE horario LIKE CONCAT(:date, '%')
			""", nativeQuery = true)
	Optional<List<TreinoAluno>> buscarTreinoAlunoPorData(String date);
}
