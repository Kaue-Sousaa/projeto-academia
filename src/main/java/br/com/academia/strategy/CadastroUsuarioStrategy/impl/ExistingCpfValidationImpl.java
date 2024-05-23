package br.com.academia.strategy.CadastroUsuarioStrategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.ExistingObjectException;
import br.com.academia.repository.UsuarioRepository;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;

@Component
public class ExistingCpfValidationImpl implements ValidaCadastroUsuarioStrategy {
    private final UsuarioRepository userRepository;

    public ExistingCpfValidationImpl(UsuarioRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void validarCampos(CadastroUsuarioDto cadastroUsuario) {
        if (!isValidCpf(cadastroUsuario.cpf())) {
            throw new ExistingObjectException("CPF já cadastrado!");   
        }
    }

    private boolean isValidCpf(String cpf){
        return userRepository.findAll().stream()
            .noneMatch(user -> user.getCpf().equals(cpf));
    }
}
