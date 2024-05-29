package br.com.academia.dto;

import br.com.academia.model.TreinoAluno;

public record TreinoAlunoDto(
		Integer id,

		String nomeAluno,

		Integer categoria,

		Integer subCategoria,

		String horario

) {
	public TreinoAlunoDto(TreinoAluno treinoAluno) {
		this(treinoAluno.getId(), treinoAluno.getNomeAluno(), treinoAluno.getCategoria(), treinoAluno.getSubCategoria(),
				treinoAluno.getHorario());
	}
}
