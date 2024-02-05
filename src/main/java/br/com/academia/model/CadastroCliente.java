package br.com.academia.model;

import java.time.LocalDate;

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
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "rg",unique = true, nullable = false)
	private String rg;
	
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "dt_nascimento",nullable = false)
	private LocalDate dtNascimento;
	
	@Column(name = "peso")
	private Float peso;
	
	@Column(name = "altura")
	private Float altura;
	
	@Column(name = "gordura_corporal")
	private Float gorduraCorporal;
	
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
}