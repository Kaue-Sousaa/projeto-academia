package br.com.academia.model;

import br.com.academia.dto.SubCategoriaDto;
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
@Table(schema = "academia", name = "sub_categoria")
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_sub_categoria")
	@SequenceGenerator(name = "seq_sub_categoria", sequenceName = "academia.seq_sub_categoria", initialValue = 1, allocationSize = 1)
	private Integer id;
	
	@Column(name = "sub_categoria")
	private String subCategoria;
	
	@Column(name = "id_categoria")
	private Integer idCategoria;
	
	public SubCategoria(SubCategoriaDto dto) {
		this.id = dto.id();
		this.subCategoria = dto.subCategoria();
		this.idCategoria = dto.idCategoria();
	}
}