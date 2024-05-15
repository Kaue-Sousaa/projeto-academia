package br.com.academia.security;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.academia.dto.AuthenticationDto;
import br.com.academia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final UsuarioRepository repository;
	private final TokenService tokenService;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity login(AuthenticationDto login) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.cpf(), login.senha()));
			var usuario = repository.findByCpf(login.cpf());
			if(usuario != null) {
				return ResponseEntity.ok(
						tokenService.createAccessToken(login.cpf(), List.of(usuario.getRole()),usuario));
			}else {
				throw new UsernameNotFoundException("CPF " + login.cpf() + " não encontrado!");
			}
		} catch (Exception e) {
			throw new BadCredentialsException("Usuário inexistente ou senha inválida!");
		}
	}
}
