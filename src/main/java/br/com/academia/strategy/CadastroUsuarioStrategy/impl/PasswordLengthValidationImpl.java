package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.InvalidPasswordException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;

@Component
public class PasswordLengthValidationImpl implements ValidaCadastroUsuarioStrategy{

    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if(cadastroUsuario.senha().length() < 8 || cadastroUsuario.senha().length() > 12){
            throw new InvalidPasswordException("A senha deve ter no mínimo 8 caracteres e no máximo 12 caracteres.");
        }        
    }
}
