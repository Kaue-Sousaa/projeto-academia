package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.RequiredNameInvalidException;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class UsernameRequirementsValidationImpl implements ValidaCadastroUsuarioStrategy {

    @SneakyThrows
    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if(!validateNomeAndSobreNome(cadastroUsuario.nome(), cadastroUsuario.sobreNome())){
            throw new RequiredNameInvalidException("O nome e sobre nome deve conter apenas letras.");
        }
    }

    private boolean validateNomeAndSobreNome(String nome, String sobreNome){
        return nome.matches("^[a-ã-zA-Z]+$") &&
                sobreNome.matches("^[a-ã-zA-Z]+$");
    }
}
