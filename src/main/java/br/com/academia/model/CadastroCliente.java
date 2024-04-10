package br.com.academia.model;

import br.com.academia.dto.CadastroClienteDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(schema = "academia",name = "cadastro_cliente")
@NoArgsConstructor
@AllArgsConstructor
public class CadastroCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_cadastro_cliente")
	@SequenceGenerator(name = "seq_cadastro_cliente", sequenceName = "academia.seq_cadastro_cliente", initialValue = 1, allocationSize = 1)
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
	
	@Column(name = "DDI")
	private String telefone;
	
	public CadastroCliente(CadastroClienteDto cadastroDto) {
			this.id = null;
			this.nome = cadastroDto.nome();
			this.sobreNome = cadastroDto.sobreNome();
			this.email = cadastroDto.email();
			this.cpf = cadastroDto.cpf();
			this.dtNascimento = cadastroDto.dtNascimento();
			this.telefone = cadastroDto.telefone();
	}
}