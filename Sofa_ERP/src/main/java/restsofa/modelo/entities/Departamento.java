package restsofa.modelo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="departamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Departamento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_depto")
	private int idDepartamento;
	
	private String nombre;
}
