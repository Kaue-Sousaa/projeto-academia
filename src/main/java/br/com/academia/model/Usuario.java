package br.com.academia.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.enums.UserRoleEn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(schema = "academia",name = "cadastro_usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_cadastro_usuario")
	@SequenceGenerator(name = "seq_cadastro_usuario", sequenceName = "academia.seq_cadastro_usuario", initialValue = 1, allocationSize = 1)
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "sobre_nome", nullable = false)
	private String sobreNome;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "dt_nascimento",nullable = false)
	private String dtNascimento;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "senha")
	private String senha;
	
	@JsonIgnoreProperties
	@Transient
	private String confirmSenha;
	
	@Column(name = "role")
	private String role;
	
	public Usuario(CadastroUsuarioDto cadastroDto) {
			this.id = null;
			this.nome = cadastroDto.nome();
			this.sobreNome = cadastroDto.sobreNome();
			this.email = cadastroDto.email();
			this.cpf = cadastroDto.cpf();
			this.dtNascimento = cadastroDto.dtNascimento();
			this.telefone = cadastroDto.telefone();
			this.genero = cadastroDto.genero();
			this.senha = cadastroDto.senha();
			this.confirmSenha = cadastroDto.confirmSenha();
			this.role = cadastroDto.role();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role.equals(UserRoleEn.ADMIN.name()))
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Override
	public String getUsername() {
		return this.cpf;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}