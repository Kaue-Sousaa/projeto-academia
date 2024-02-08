package br.com.academia.dto;

import java.time.LocalDate;

import br.com.academia.model.CadastroCliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroClienteDto(
		
		Integer id,
		
		@NotBlank
		String nome,

		@NotBlank
		@Size(min = 11)
		String cpf,

		String sexo,
		
		@NotBlank
		String dtNascimento,

		Float peso,

		Float altura,

		Float gorduraCorporal,

		String telefone,

		String celular,

		String email,

		String bairro,

		String cep,

		String endereco,

		String cidade,

		String estado
		
		) {
	
	public CadastroClienteDto(CadastroCliente entity) {
		this ( 
				entity.getId(),
				entity.getNome(),
				entity.getCpf(),
				entity.getSexo(),
				entity.getDtNascimento(),
				entity.getPeso(),
				entity.getAltura(),
				entity.getGorduraCorporal(),
				entity.getTelefone(),
				entity.getCelular(),
				entity.getEmail(),
				entity.getBairro(),
				entity.getCep(),
				entity.getEndereco(),
				entity.getCidade(),
				entity.getEstado()
			);
	}
	
}
