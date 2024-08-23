package br.com.academia.service;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.enums.UserRoleEn;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.Usuario;
import br.com.academia.repository.UsuarioRepository;
import br.com.academia.strategy.CadastroUsuarioStrategy.ValidaCadastroUsuarioStrategy;
import br.com.academia.strategy.CadastroUsuarioStrategy.impl.PasswordhValidationImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final List<ValidaCadastroUsuarioStrategy> listValidateField;
	private final PasswordhValidationImpl passwordValidation;
	private final UsuarioRepository usuarioRepository;

	public CadastroUsuarioDto buscarCadastroPorId(Integer id) {
		return criarUsuarioDto(getUsuario(id));
	}

	public List<CadastroUsuarioDto> buscarTodosCadastro() {
		return usuarioRepository.findAll()
				.stream()
				.map(CadastroUsuarioDto::new)
				.toList();
	}

	public List<CadastroUsuarioDto> buscarTodosUsuario() {
		return buscarTodosCadastro().stream()
				.filter(user -> user.role().equals(UserRoleEn.USER.getRole()))
				.toList();
	}

	public CadastroUsuarioDto buscarUsarioPorEmail(String email) {
		return criarUsuarioDto(usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado")));
	}

	public String salvarCadastro(CadastroUsuarioDto cadastroDto) {
		listValidateField.forEach(field -> field.validarCampos(cadastroDto));
		return salvarUsuario(cadastroDto);
	}

	public CadastroUsuarioDto atualizarCadastro(CadastroUsuarioDto cliente) {
		try {
			var entity = getUsuario(cliente.id());

			atualizarDadosDoCadastro(cliente, entity);

			return criarUsuarioDto(usuarioRepository.save(entity));
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	public void deletarUsuario(Integer id) {
		usuarioRepository.delete(getUsuario(id));
	}

	private String salvarUsuario(CadastroUsuarioDto cadastroDto) {
		var cadastroUsuario = new Usuario(cadastroDto);
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

	private Usuario getUsuario(Integer id) {
        return usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
	}
}