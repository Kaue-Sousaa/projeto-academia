package br.com.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.dto.CadastroClienteDto;
import br.com.academia.model.CadastroCliente;
import br.com.academia.repository.CadastroClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastroClienteService{
	
	private final CadastroClienteRepository clienteRepository;
	
	public CadastroClienteDto buscarCadastroPorId(Integer id) throws Exception {
		var entity = clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado"));
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
					-> new Exception("Cliente não encontrado"));
			
			entity.setNome(cliente.nome());
			entity.setCpf(cliente.cpf());
			entity.setSexo(cliente.sexo());
			entity.setDtNascimento(cliente.dtNascimento());
			entity.setPeso(cliente.peso());
			entity.setAltura(cliente.altura());
			entity.setGorduraCorporal(cliente.gorduraCorporal());
			entity.setTelefone(cliente.telefone());
			entity.setCelular(cliente.celular());
			entity.setEmail(cliente.email());
			entity.setBairro(cliente.bairro());
			entity.setCep(cliente.cep());
			entity.setEndereco(cliente.endereco());
			entity.setCidade(cliente.cidade());
			entity.setEstado(cliente.estado());
			
			clienteRepository.save(entity);
			return new CadastroClienteDto(entity);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void deletarCliente(Integer id) {
		try {
			var entity = clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado"));
			clienteRepository.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}