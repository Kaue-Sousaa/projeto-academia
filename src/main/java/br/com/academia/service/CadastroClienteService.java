package br.com.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.dto.CadastroClienteDto;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.model.CadastroCliente;
import br.com.academia.repository.CadastroClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastroClienteService{
	
	private final CadastroClienteRepository clienteRepository;
	
	public CadastroClienteDto buscarCadastroPorId(Integer id)  {
		var entity = clienteRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Cliente não encontrado"));
		return new CadastroClienteDto(entity);
	}
	
	public List<CadastroClienteDto> buscarTodosCadastro() {
		var entity = clienteRepository.findAll();
		return entity.stream().map(CadastroClienteDto::new).toList();
	}
	
	public String salvarCadastro(CadastroClienteDto cadastroDto) {
		String cpf = clienteRepository.findByCpf(cadastroDto.cpf());
		if(cpf != null) {
			return "CPF já cadastrado";
		}
		clienteRepository.save(new CadastroCliente(cadastroDto));
		return "Cliente cadastrado com sucesso!";
	}
	
	public CadastroClienteDto atualizarCadastro(CadastroClienteDto cliente){
		try {
			var entity = clienteRepository.findById(cliente.id()).orElseThrow(()
					-> new ResourceNotFoundException("Cliente não encontrado"));
			
			entity.setNome(cliente.nome());
			entity.setCpf(cliente.cpf());
			entity.setDtNascimento(cliente.dtNascimento());
			entity.setTelefone(cliente.telefone());
			entity.setEmail(cliente.email());
			entity.setGenero(cliente.genero());
			
			clienteRepository.save(entity);
			return new CadastroClienteDto(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public void deletarCliente(Integer id) {
		var entity = clienteRepository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Cliente não encontrado"));
		clienteRepository.delete(entity);
	}
}
