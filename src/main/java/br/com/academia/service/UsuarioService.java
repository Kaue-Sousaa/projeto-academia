package br.com.academia.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.Usuario;
import br.com.academia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService{
	
	private final UsuarioRepository usuarioRepository;
	
	public CadastroUsuarioDto buscarCadastroPorId(Integer id)  {
		var entity = usuarioRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Cliente não encontrado"));
		return new CadastroUsuarioDto(entity);
	}
	
	public List<CadastroUsuarioDto> buscarTodosCadastro() {
		var entity = usuarioRepository.findAll();
		return entity.stream().map(CadastroUsuarioDto::new).toList();
	}
	
	public String salvarCadastro(CadastroUsuarioDto cadastroDto) {
		var entity = usuarioRepository.findByCpf(cadastroDto.cpf());
		if(entity == null) {
			if(!isValidCampoConfirmSenha(cadastroDto.senha(), cadastroDto.confirmSenha())) {
				return "As senhas não são iguais. Tente novamente.";
			}
			return salvarUsuario(cadastroDto);
		}
		return "Erro ao cadastrar Usuário.";
	}
	
	public CadastroUsuarioDto atualizarCadastro(CadastroUsuarioDto cliente){
		try {
			var entity = usuarioRepository.findById(cliente.id()).orElseThrow(()
					-> new ResourceNotFoundException("Usuário não encontrado"));
			
			entity.setNome(cliente.nome());
			entity.setCpf(cliente.cpf());
			entity.setDtNascimento(cliente.dtNascimento());
			entity.setTelefone(cliente.telefone());
			entity.setEmail(cliente.email());
			entity.setGenero(cliente.genero());
			
			usuarioRepository.save(entity);
			return new CadastroUsuarioDto(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public void deletarUsuario(Integer id) {
		var entity = usuarioRepository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Usuário não encontrado"));
		usuarioRepository.delete(entity);
	}
	
	private String salvarUsuario(CadastroUsuarioDto cadastroDto) {
		Usuario cadastroUsuario = new Usuario(cadastroDto);
		cadastroUsuario.setSenha(passwordEncoder(cadastroDto.senha()));
		usuarioRepository.save(cadastroUsuario);
		return "Usuário cadastrado com sucesso!";
	}

	private boolean isValidCampoConfirmSenha(String senha, String confirmSenha) {
		return senha.equals(confirmSenha);
	}
	
	private String passwordEncoder(String senha) {
		return new BCryptPasswordEncoder().encode(senha); 
	}
}