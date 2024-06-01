package br.com.academia.strategy.atualizaCadastroStrategy;

import br.com.academia.dto.CadastroUsuarioDto;

public interface ValidaAtualizacaoCadastroStrategy {
	
	void validar(CadastroUsuarioDto request);
}
