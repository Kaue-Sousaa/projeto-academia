package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.RequiredNameInvalidException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class UsernameLengthValidationImpl implements ValidaCadastroUsuarioStrategy {

    @SneakyThrows
    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if(validateLengthNomeAndSobreNome(cadastroUsuario.nome(), cadastroUsuario.sobreNome())){
            throw new RequiredNameInvalidException("O nome e sobre nome deve conter mais que dois caracteres.");
        }
    }

    private boolean validateLengthNomeAndSobreNome(String nome, String sobreNome){
        return nome.length() <= 2 || sobreNome.length() <= 2;
    }
}
