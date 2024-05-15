package br.com.academia.dto;

public record TokenDto(
		 String accessToken,
		 String usuario,
		 String email,
		 String role
		) {

}
