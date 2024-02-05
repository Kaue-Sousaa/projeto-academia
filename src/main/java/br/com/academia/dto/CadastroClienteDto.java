package br.com.academia.dto;

import java.time.LocalDate;

import br.com.academia.model.CadastroCliente;
import jakarta.validation.constraints.NotBlank;

public record CadastroClienteDto(
		
		@NotBlank
		String nome,

		String rg,
		
		@NotBlank
		String cpf,

		String sexo,
		
		@NotBlank
		LocalDate dtNascimento,

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
				entity.getNome(),
				entity.getRg(),
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
