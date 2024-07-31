package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.InvalidEmailException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ValidatedEmailImpl implements ValidaCadastroUsuarioStrategy {
    @SneakyThrows
    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if(!cadastroUsuario.email().matches("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Za-z]{2,}$")){
            throw new InvalidEmailException("E-mail inválido.");
        }
    }
}
