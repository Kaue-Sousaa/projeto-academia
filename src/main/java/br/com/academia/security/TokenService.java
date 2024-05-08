package br.com.academia.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import br.com.academia.dto.TokenDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	private long validityInMilliseconds = 3600000; //1h
	
	private final UserDetailsService userDetailsService;
	
	public TokenDto createAccessToken(String cpf, List<String> roles) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		return new TokenDto(
				getAccessToken(cpf, roles, now, validity),
				getRefreshToken(cpf, roles,  now));
	}

	public TokenDto refreshToken(String refreshToken) {
		if(refreshToken.contains("Bearer ")) {
			refreshToken = refreshToken.substring("Bearer ".length());
		}
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
		DecodedJWT decodedJWT = verifier.verify(refreshToken);
		String cpf = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		return createAccessToken(cpf, roles);
	}
	
	private String getAccessToken(String cpf, List<String> roles, Date now, Date validity) {
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.withSubject(cpf)
				.withIssuer(issuerUrl)
				.sign(Algorithm.HMAC256(secret))
				.strip();
	}
	
	private String getRefreshToken(String cpf, List<String> roles, Date now) {
		Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validityRefreshToken)
				.withSubject(cpf)
				.sign(Algorithm.HMAC256(secret))
				.strip();
	}
	
	public boolean validateToken(String token) {
		try {
			return !decodedToken(token).getExpiresAt().before(new Date());
		} catch (TokenExpiredException e) {
			return false;
		}
	}
	
	private DecodedJWT decodedToken(String token) {
		Algorithm alg = Algorithm.HMAC256(secret.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		return verifier.verify(token);
	}
	
	public Authentication getAuthentication(String token) {
		try {
			DecodedJWT decodedJWT = decodedToken(token);
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
			return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
		} catch (Exception e) {
			throw new BadCredentialsException("Usuário inexistente ou senha inválida!");
		}
	}
}
