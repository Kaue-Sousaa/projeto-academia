package br.com.academia.security;

import br.com.academia.dto.AuthenticationDto;
import br.com.academia.exception.InvalidCpfLengthException;
import br.com.academia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final UsuarioRepository repository;
	private final TokenService tokenService;

	@SuppressWarnings("rawtypes")
	public ResponseEntity login(AuthenticationDto login) {
		authentication(login);
		var usuario = repository.findByCpf(login.cpf());
		if (usuario != null) {
			return ResponseEntity.ok(
					tokenService.createAccessToken(login.cpf(), List.of(usuario.getRole()), usuario));
		} else {
			throw new UsernameNotFoundException("CPF " + login.cpf() + " não encontrado!");
		}
	}

	private void authentication(AuthenticationDto auth) {
		validatedCpfLength(auth.cpf());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(auth.cpf(), auth.senha()));
	}

	private void validatedCpfLength(String cpf) {
		if(cpf.length() != 11){
			throw new InvalidCpfLengthException("CPF tamanho inválido");
		}
	}
}