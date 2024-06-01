package br.com.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import br.com.academia.dto.TreinoAlunoDto;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.TreinoAluno;
import br.com.academia.repository.TreinoAlunoRepository;
import br.com.academia.strategy.TreinoAlunoStrategy.ValidarTreinoAlunoStrategy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreinoAlunoService {

	private final List<ValidarTreinoAlunoStrategy> camposValidateStrategy;
	private final TreinoAlunoRepository treinoRepository;
	private final UsuarioService userService;

	public List<TreinoAlunoDto> buscarTodosTreinoAluno() {
		return treinoRepository.findAll().stream().map(TreinoAlunoDto::new).toList();
	}

	public TreinoAlunoDto buscarTreinoPorIdAluno(Integer id) {
		return new TreinoAlunoDto(treinoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Treino não encontrado")));
	}
	
	public List<TreinoAlunoDto> buscarListaTreinoAlunoPorEmail(String email) {
		List<TreinoAlunoDto> listTreinoAluno = new ArrayList<>();
		var user = userService.buscarUsarioPorEmail(email);
		if(user != null) {
			listTreinoAluno = treinoRepository.buscarTreinoPorIdUsuario(user.id())
					.stream().map(TreinoAlunoDto::new).toList();
		}
		return listTreinoAluno;
	}

	public List<TreinoAlunoDto> buscarTreinoAlunoPorData(String date) {
		List<TreinoAluno> treinoData = new ArrayList<>();
		if (!"null".equals(date)) {
			var data = date.replace("-", "/");
			treinoData = consultaTreinoAlunoPorData(data)
					.stream().filter(dia -> dia.getHorario().contains(data)).toList();
		}
		return treinoData.stream().map(TreinoAlunoDto::new).toList();
	}
	
	public void salvarTreino(TreinoAlunoDto treinoAluno) {
		validaCamposTreinoAluno(treinoAluno);
		treinoRepository.save(new TreinoAluno(treinoAluno));
	}

	public TreinoAlunoDto atualizarTreino(TreinoAlunoDto treinoAluno) {
		var entity = treinoRepository.findById(treinoAluno.id()).orElseThrow(
				() -> new ResourceNotFoundException(
						"Treino referente ao aluno: " + treinoAluno.nomeAluno() + " nâo encontrado!"));

		validaCamposTreinoAluno(treinoAluno);
		entity.setNomeAluno(treinoAluno.nomeAluno());
		entity.setCategoria(treinoAluno.categoria());
		entity.setSubCategoria(treinoAluno.subCategoria());
		entity.setHorario(treinoAluno.horario());

		return new TreinoAlunoDto(treinoRepository.save(entity));
	}

	public void deletarTreino(Integer id) {
		var treino = treinoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Treino não encontrado"));
		treinoRepository.delete(treino);
	}

	private void validaCamposTreinoAluno(TreinoAlunoDto treinoAlunoDto) {
		camposValidateStrategy.forEach(field -> field.validar(treinoAlunoDto));
	}
	
	private List<TreinoAluno> consultaTreinoAlunoPorData(String data){
		return treinoRepository.buscarTreinoAlunoPorData(data)
				.orElseThrow(() -> new ResourceClosedException("Nenhum treino encontrado no dia: " + data));
	}
}