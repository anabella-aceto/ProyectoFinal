package restsofa.modelo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="sofa_materiales")
public class SofaMaterial {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sm")
	private int id_sofa_materiales;
	
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="id_sofa")
	private Sofa sofa;
	
	@ManyToOne
	@JoinColumn(name="id_material")
	private Material material;
	
}
