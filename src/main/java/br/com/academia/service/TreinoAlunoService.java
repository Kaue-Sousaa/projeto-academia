package br.com.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.dto.TreinoAlunoDto;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.TreinoAluno;
import br.com.academia.repository.TreinoAlunoRepository;
import br.com.academia.strategy.TreinoAlunoStrategy.ValidarTreinoAlunoStrategy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreinoAlunoService{
	
	private final ValidarTreinoAlunoStrategy camposValidateStrategy;
	private final TreinoAlunoRepository treinoRepository;
	
	public List<TreinoAlunoDto> buscarTodosTreinoAluno(){
		return treinoRepository.findAll().stream().map(TreinoAlunoDto::new).toList();
	}
	
	public TreinoAlunoDto buscarTreinoPorIdAluno(Integer id) {
		return new TreinoAlunoDto(treinoRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Treino não encontrado")));
	}
	
	public void salvarTreino(TreinoAlunoDto treinoAluno) {
		validaCamposTreinoAluno(treinoAluno);
		treinoRepository.save(new TreinoAluno(treinoAluno));
	}
	
	public TreinoAlunoDto atualizarTreino(TreinoAlunoDto treinoAluno) {
		var entity = treinoRepository.findById(treinoAluno.id()).orElseThrow(
			() -> new ResourceNotFoundException("Treino referente ao aluno: " + treinoAluno.aluno() + " nâo encontrado!"));
		
		validaCamposTreinoAluno(treinoAluno);	
		entity.setAluno(treinoAluno.aluno());
		entity.setCategoria(treinoAluno.categoria());
		entity.setSubCategoria(treinoAluno.subCategoria());
		entity.setHorario(treinoAluno.horario());
		
		return new TreinoAlunoDto(treinoRepository.save(entity));
	}
	
	public void deletarTreino(Integer id) {
		var treino = treinoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Treino não encontrado"));
		treinoRepository.delete(treino);
	}

	private void validaCamposTreinoAluno(TreinoAlunoDto treinoAlunoDto){
		camposValidateStrategy.validar(treinoAlunoDto);
	}
}