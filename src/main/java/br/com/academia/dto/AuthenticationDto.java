package br.com.academia.dto;

import jakarta.validation.constraints.Pattern;

public record AuthenticationDto(

		@Pattern(regexp = "^[0-9]+$",
				message = "O campo CPF deve estar no formato correto e conter apenas números!")
		String cpf,

		String senha

) {
}
