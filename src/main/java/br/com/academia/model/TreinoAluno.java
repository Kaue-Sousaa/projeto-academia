package br.com.academia.model;

import br.com.academia.dto.TreinoAlunoDto;
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
@Table(schema = "academia", name = "treino_aluno")
@AllArgsConstructor
@NoArgsConstructor
public class TreinoAluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_cadastro_treino_aluno")
	@SequenceGenerator(name = "seq_cadastro_treino_aluno", sequenceName = "academia.seq_cadastro_treino_aluno", initialValue = 1, allocationSize = 1)
	private Integer id;

	@Column(name = "nomeAluno", nullable = false)
	private String nomeAluno;

	@Column(name = "id_categoria", nullable = false)
	private Integer categoria;

	@Column(name = "id_sub_categoria", nullable = false)
	private Integer subCategoria;

	@Column(name = "horario", nullable = false)
	private String horario;

	public TreinoAluno(TreinoAlunoDto treinoAlunoDto) {
		this.id = treinoAlunoDto.id();
		this.nomeAluno = treinoAlunoDto.nomeAluno();
		this.categoria = treinoAlunoDto.categoria();
		this.subCategoria = treinoAlunoDto.subCategoria();
		this.horario = treinoAlunoDto.horario();
	}
}