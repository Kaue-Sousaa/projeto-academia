package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.InvalidPasswordException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;

@Component
public class PasswordhValidationImpl implements ValidaCadastroUsuarioStrategy{

    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if(!isValidCampoConfirmSenha(cadastroUsuario.senha(), cadastroUsuario.confirmarSenha())){
            throw new InvalidPasswordException("As senhas não são iguais. Tente novamente.");
        }        
    }

    private boolean isValidCampoConfirmSenha(String senha, String confirmSenha) {
		return senha != null && senha.equals(confirmSenha);
	}
}
