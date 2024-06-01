package br.com.academia.dto;

import br.com.academia.model.TreinoAluno;

public record TreinoAlunoDto(
		Integer id,
		
		Integer idUsuario,

		String nomeAluno,

		Integer categoria,

		Integer subCategoria,

		String horario

) {
	public TreinoAlunoDto(TreinoAluno treinoAluno) {
		this(treinoAluno.getId(), treinoAluno.getIdUsuario(), treinoAluno.getNomeAluno(), treinoAluno.getCategoria(),
				treinoAluno.getSubCategoria(), treinoAluno.getHorario());
	}
}
