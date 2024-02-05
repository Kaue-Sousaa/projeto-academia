package br.com.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.dto.CadastroClienteDto;
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
}
