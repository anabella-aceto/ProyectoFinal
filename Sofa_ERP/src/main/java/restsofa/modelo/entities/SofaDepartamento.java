package restsofa.modelo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sofas_depto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SofaDepartamento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sd")
	int id_sd;
	
	@ManyToOne
	@JoinColumn(name="id_depto")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="id_sofa")
	private Sofa sofa;
}
