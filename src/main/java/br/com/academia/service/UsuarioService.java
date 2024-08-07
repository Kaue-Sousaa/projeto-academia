package br.com.academia.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.Usuario;
import br.com.academia.repository.UsuarioRepository;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import br.com.academia.strategy.CadastroUsuarioStrategy.impl.PasswordhValidationImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final List<ValidaCadastroUsuarioStrategy> listValidateField;
	private final PasswordhValidationImpl passwordValidation;
	private final UsuarioRepository usuarioRepository;

	public CadastroUsuarioDto buscarCadastroPorId(Integer id) {
		var entity = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
		return criarUsuarioDto(entity);
	}

	public List<CadastroUsuarioDto> buscarTodosCadastro() {
		var entity = usuarioRepository.findAll();
		return entity.stream().map(CadastroUsuarioDto::new).toList();
	}

	public CadastroUsuarioDto buscarUsarioPorEmail(String email) {
		return criarUsuarioDto(usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário nâo encontrado")));
	}

	public String salvarCadastro(CadastroUsuarioDto cadastroDto) {
		listValidateField.forEach(field -> field.validarCampos(cadastroDto));
		return salvarUsuario(cadastroDto);
	}

	public CadastroUsuarioDto atualizarCadastro(CadastroUsuarioDto cliente) {
		try {
			var entity = usuarioRepository.findById(cliente.id())
					.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

			atualizarDadosDoCadastro(cliente, entity);

			return criarUsuarioDto(usuarioRepository.save(entity));
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	public void deletarUsuario(Integer id) {
		var entity = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
		usuarioRepository.delete(entity);
	}

	private String salvarUsuario(CadastroUsuarioDto cadastroDto) {
		Usuario cadastroUsuario = new Usuario(cadastroDto);
		cadastroUsuario.setSenha(passwordEncoder(cadastroDto.senha()));
		usuarioRepository.save(cadastroUsuario);
		return "Usuário cadastrado com sucesso!";
	}

	private String passwordEncoder(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}

	private void atualizarDadosDoCadastro(CadastroUsuarioDto cliente, Usuario entity) {
		atualizarSenhaSeNecessario(cliente, entity);

		entity.setNome(cliente.nome());
		entity.setCpf(cliente.cpf());
		entity.setDataNascimento(cliente.dataNascimento());
		entity.setTelefone(cliente.telefone());
		entity.setEmail(cliente.email());
		entity.setGenero(cliente.genero());
	}

	private void atualizarSenhaSeNecessario(CadastroUsuarioDto cliente, Usuario entity) {
		if (cliente.senha() != null && cliente.confirmarSenha() != null) {
			passwordValidation.validarCampos(cliente);
			entity.setSenha(passwordEncoder(cliente.senha()));
		}
	}

	private CadastroUsuarioDto criarUsuarioDto(Usuario entity) {
		return new CadastroUsuarioDto(entity);
	}
}