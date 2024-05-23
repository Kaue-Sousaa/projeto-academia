package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.ExistingObjectException;
import br.com.academia.repository.UsuarioRepository;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;

@Component
public class ExistingEmailValidationImpl implements ValidaCadastroUsuarioStrategy {
    private final UsuarioRepository userRepository;

    public ExistingEmailValidationImpl(UsuarioRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if (!isValidEmail(cadastroUsuario.email())) {
            throw new ExistingObjectException("Email já cadastrado!");   
        }
    }

    private boolean isValidEmail(String email){
        return userRepository.findAll().stream()
            .noneMatch(user -> user.getEmail().equals(email));
    }
}
