package br.com.academia.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {
	
	private final SecurityFilter securityFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors
						.configurationSource(request -> {
							CorsConfiguration corsConfiguration = new CorsConfiguration();
							corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
							corsConfiguration.setAllowedMethods(
									Arrays.asList("POST", "PUT", "GET", "PATCH", "DELETE", "OPTIONS"));
							corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
							corsConfiguration.setAllowCredentials(true);
							return corsConfiguration;
						}))
				.sessionManagement(
						session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/auth/login", "/usuario/cadastro").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/usuario/excluir/**", "/categoria/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/categoria/cadastro/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/categoria", "/categoria/**").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.exceptionHandling(this::configureExceptionHandling)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	private void configureExceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> exception) {
	    exception.accessDeniedHandler((request, response, accessDeniedException) -> {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Acesso Negado!");
	    });
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}