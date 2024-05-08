package br.com.academia.controller.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.dto.AuthenticationDto;
import br.com.academia.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthService authService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
		var token = authService.login(data);
		if(token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação de Usuário Inválido!");
		}
		return token;
	}
}
