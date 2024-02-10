package br.com.academia.dto;

import br.com.academia.model.CadastroFuncionario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroFuncionarioDto(
		
		Integer id,
		
		@NotBlank
		String nome,

		@NotBlank
		@Size(min = 11)
		String cpf,

		String sexo,
		
		@NotBlank
		String dtNascimento,

		String telefone,

		String celular,

		String email,

		String bairro,

		String cep,

		String endereco,

		String cidade,

		String estado,
		
		String role
		
		) {
	
	public CadastroFuncionarioDto(CadastroFuncionario entity) {
		this ( 
				entity.getId(),
				entity.getNome(),
				entity.getCpf(),
				entity.getSexo(),
				entity.getDtNascimento(),
				entity.getTelefone(),
				entity.getCelular(),
				entity.getEmail(),
				entity.getBairro(),
				entity.getCep(),
				entity.getEndereco(),
				entity.getCidade(),
				entity.getEstado(),
				entity.getRole()
			);
	}
	
}
