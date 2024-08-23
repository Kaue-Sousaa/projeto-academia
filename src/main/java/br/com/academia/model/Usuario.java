package br.com.academia.model;

import br.com.academia.dto.CadastroUsuarioDto;
import br.com.academia.enums.UserRoleEn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(schema = "academia",name = "cadastro_usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_cadastro_usuario")
	@SequenceGenerator(name = "seq_cadastro_usuario", sequenceName = "academia.seq_cadastro_usuario", initialValue = 1, allocationSize = 1)
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "sobre_nome", nullable = false)
	private String sobreNome;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "dt_nascimento",nullable = false)
	private String dataNascimento;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "senha")
	private String senha;

	@Transient
	private String confirmarSenha;
	
	@Column(name = "role")
	private String role;
	
	public Usuario(CadastroUsuarioDto cadastroDto) {
			this.id = null;
			this.nome = cadastroDto.nome();
			this.sobreNome = cadastroDto.sobreNome();
			this.email = cadastroDto.email();
			this.cpf = cadastroDto.cpf();
			this.dataNascimento = cadastroDto.dataNascimento();
			this.telefone = cadastroDto.telefone();
			this.genero = cadastroDto.genero();
			this.senha = cadastroDto.senha();
			this.confirmarSenha = cadastroDto.confirmarSenha();
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