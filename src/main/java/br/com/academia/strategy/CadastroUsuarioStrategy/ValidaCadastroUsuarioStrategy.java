package br.com.academia.strategy.CadastroUsuarioStrategy;

import br.com.academia.dto.CadastroUsuarioDto;

public interface ValidaCadastroUsuarioStrategy {

    void validarCampos(CadastroUsuarioDto cadastroUsuario);
}
