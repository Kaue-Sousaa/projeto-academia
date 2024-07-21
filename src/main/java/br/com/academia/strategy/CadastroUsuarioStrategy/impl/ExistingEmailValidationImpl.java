package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.ExistingObjectException;
import br.com.academia.repository.UsuarioRepository;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ExistingEmailValidationImpl implements ValidaCadastroUsuarioStrategy {
    private final UsuarioRepository userRepository;

    public ExistingEmailValidationImpl(UsuarioRepository userRepository){
        this.userRepository = userRepository;
    }
    @SneakyThrows
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
