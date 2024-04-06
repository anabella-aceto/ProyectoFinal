package restsofa.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un departamento.
 */

@Entity // Indica que esta clase es una entidad JPA
@Table(name="departamentos") // Especifica el nombre de la tabla en la base de datos
@NoArgsConstructor // Genera un constructor sin argumentos usando Lombok
@AllArgsConstructor // Genera un constructor con todos los argumentos usando Lombok
@Data // Genera getters, setters, toString, equals y hashCode automáticamente usando Lombok

public class Departamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_depto")
	private int idDepartamento;
	
	private String nombre;
}
