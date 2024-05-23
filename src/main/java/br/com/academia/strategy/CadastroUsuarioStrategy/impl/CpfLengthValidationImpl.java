package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.InvalidCpfLengthException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;

@Component
public class CpfLengthValidationImpl  implements ValidaCadastroUsuarioStrategy {
    
    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if (cadastroUsuario.cpf().length() != 11) {
            throw new InvalidCpfLengthException("CPF tem que ter 11 digitos!");   
        }
    }
}
