package br.com.academia.strategy.TreinoAlunoStrategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.TreinoAlunoDto;
import br.com.academia.strategy.TreinoAlunoStrategy.ValidarTreinoAlunoStrategy;

@Component
public class ExistingIdUsuario implements ValidarTreinoAlunoStrategy{

	@Override
	public void validar(TreinoAlunoDto treinoAluno) {
   // TODO document why this method is empty
 }

}
