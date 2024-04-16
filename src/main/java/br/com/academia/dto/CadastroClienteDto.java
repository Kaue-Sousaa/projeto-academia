package br.com.academia.dto;

import br.com.academia.model.CadastroCliente;

public record CadastroClienteDto(
		Integer id,
		
		String nome,
		
		String sobreNome,
		
		String email,
		
		String cpf,
		
		String dtNascimento,
	
		String telefone,
		
		String genero,
		
		String senha,
		
		String confirmSenha,
		
		String role
		
		) {
	
	public CadastroClienteDto(CadastroCliente entity) {
		this ( 
				entity.getId(),
				entity.getNome(),
				entity.getSobreNome(),
				entity.getEmail(),
				entity.getCpf(),
				entity.getDtNascimento(),
				entity.getTelefone(),
				entity.getGenero(),
				entity.getSenha(),
				entity.getConfirmSenha(),
				entity.getRole()
			);
	}
	
}
