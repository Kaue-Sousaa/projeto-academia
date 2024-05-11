package br.com.academia.model;

import br.com.academia.dto.CategoriaDto;
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
@Table(schema = "academia", name = "categoria")
@AllArgsConstructor
@NoArgsConstructor
public class Categoria{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_categoria")
	@SequenceGenerator(name = "seq_categoria", sequenceName = "academia.seq_categoria", initialValue = 1, allocationSize = 1)
	private Integer id;
	
	@Column(name = "nome_categoria")
	private String categoria;
	
	public Categoria(CategoriaDto categoriaDto) {
		this.id = categoriaDto.id();
		this.categoria = categoriaDto.categoria();
	}
}
