package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.InvalidPasswordException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class PasswordRequirementsValidationImpl implements ValidaCadastroUsuarioStrategy {

    @SneakyThrows
    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if(!cadastroUsuario.senha().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).+$")){
            throw new InvalidPasswordException(
                    "A senha deve ter pelo menos: 1 caractere minúsculo, 1 caractere maiúsculo, 1 número e 1 caractere especial");
        }
    }
}
