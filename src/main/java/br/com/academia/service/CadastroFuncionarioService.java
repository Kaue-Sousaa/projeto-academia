package br.com.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.dto.CadastroFuncionarioDto;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.CadastroFuncionario;
import br.com.academia.repository.CadastroFuncionarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastroFuncionarioService{
	
	private final CadastroFuncionarioRepository repository;
	
	public CadastroFuncionarioDto buscarFuncionarioPorId(Integer id) {
		var entity = repository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Funcionário não encontrado"));
		return new CadastroFuncionarioDto(entity);
	}
	
	public List<CadastroFuncionarioDto> buscarTodosFuncionario() {
		var entity = repository.findAll();
		return entity.stream().map(CadastroFuncionarioDto::new).toList();
	}
	
	public String salvarCadastro(CadastroFuncionarioDto cadastroDto) {
		String cpf = repository.findByCpf(cadastroDto.cpf());
		if(cpf != null) {
			return "CPF já cadastrado";
		}
		repository.save(new CadastroFuncionario(cadastroDto));
		return "Funcionário cadastrado com sucesso!";
	}
	
	public CadastroFuncionarioDto atualizarCadastro(CadastroFuncionarioDto funcionario){
		try {
			var entity = repository.findById(funcionario.id()).orElseThrow(()
					-> new ResourceNotFoundException("Funcionário não encontrado"));
			
			entity.setNome(funcionario.nome());
			entity.setCpf(funcionario.cpf());
			entity.setSexo(funcionario.sexo());
			entity.setDtNascimento(funcionario.dtNascimento());
			entity.setTelefone(funcionario.telefone());
			entity.setCelular(funcionario.celular());
			entity.setEmail(funcionario.email());
			entity.setBairro(funcionario.bairro());
			entity.setCep(funcionario.cep());
			entity.setEndereco(funcionario.endereco());
			entity.setCidade(funcionario.cidade());
			entity.setEstado(funcionario.estado());
			entity.setRole(funcionario.role());
			
			repository.save(entity);
			return new CadastroFuncionarioDto(entity);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void deletarCliente(Integer id) {
		var entity = repository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Funcionário não encontrado"));
		repository.delete(entity);
	}
}