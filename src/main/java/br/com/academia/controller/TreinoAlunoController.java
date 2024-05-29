package br.com.academia.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.dto.TreinoAlunoDto;
import br.com.academia.service.TreinoAlunoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("treino")
@RequiredArgsConstructor
public class TreinoAlunoController {

	private final TreinoAlunoService treinoService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TreinoAlunoDto>> buscarTodosTreino() {
		return ResponseEntity.ok(treinoService.buscarTodosTreinoAluno());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TreinoAlunoDto> buscarTreinoAlunoPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(treinoService.buscarTreinoPorIdAluno(id));
	}

	@GetMapping(value = "/alunos/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TreinoAlunoDto>> buscarTreinoAlunoPorData(@PathVariable String date) {
		var listTreinoDia = treinoService.buscarTreinoAlunoPorData(date);
		if(listTreinoDia.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(listTreinoDia);
	}

	@PostMapping(value = "cadastro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void salvarTreino(@RequestBody TreinoAlunoDto treinoAluno) {
		treinoService.salvarTreino(treinoAluno);
	}

	@PutMapping(value = "atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TreinoAlunoDto> atualizarTreino(@RequestBody TreinoAlunoDto treinoAluno) {
		return ResponseEntity.ok(treinoService.atualizarTreino(treinoAluno));
	}

	@DeleteMapping(value = "/{id}")
	public void deletarTreino(@PathVariable Integer id) {
		treinoService.deletarTreino(id);
	}
}