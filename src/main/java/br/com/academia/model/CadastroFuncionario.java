package br.com.academia.model;

import br.com.academia.dto.CadastroFuncionarioDto;
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
@Table(schema = "academia",name = "cadastro_funcionario")
@NoArgsConstructor
@AllArgsConstructor
public class CadastroFuncionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_cadastro_funcionario")
	@SequenceGenerator(name = "seq_cadastro_funcionario", sequenceName = "academia.seq_cadastro_funcionario", initialValue = 1, allocationSize = 1)
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "dt_nascimento",nullable = false)
	private String dtNascimento;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "role")
	private String role;
	
	public CadastroFuncionario(CadastroFuncionarioDto cadastroDto) {
			this.id = null;
			this.nome = cadastroDto.nome();
			this.cpf = cadastroDto.cpf();
			this.sexo = cadastroDto.sexo();
			this.dtNascimento = cadastroDto.dtNascimento();
			this.telefone = cadastroDto.telefone();
			this.celular = cadastroDto.celular();
			this.email = cadastroDto.email();
			this.bairro = cadastroDto.bairro();
			this.cep = cadastroDto.cep();
			this.endereco = cadastroDto.endereco();
			this.cidade = cadastroDto.cidade();
			this.estado = cadastroDto.estado();
			this.role = cadastroDto.role();
	}
}