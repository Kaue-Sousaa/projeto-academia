package br.com.academia.dto;

import br.com.academia.model.Usuario;

public record CadastroUsuarioDto(
		Integer id,
		String nome,
		String sobreNome,
		String email,
		String cpf,
		String dataNascimento,
		String telefone,
		String genero,
		String senha,
		String confirmarSenha,
		String role
		
		) {
	
	public CadastroUsuarioDto(Usuario entity) {
		this ( 
				entity.getId(),
				entity.getNome(),
				entity.getSobreNome(),
				entity.getEmail(),
				entity.getCpf(),
				entity.getDataNascimento(),
				entity.getTelefone(),
				entity.getGenero(),
				entity.getSenha(),
				entity.getConfirmarSenha(),
				entity.getRole()
			);
	}
	
}
