package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.InvalidCpfException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ValidatedCpfImpl implements ValidaCadastroUsuarioStrategy {

    @SneakyThrows
    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if(!cadastroUsuario.senha().matches("^[0-9]+$")){
            throw new InvalidCpfException("O campo CPF deve estar no formato correto e conter apenas números!");
        }
    }
}
