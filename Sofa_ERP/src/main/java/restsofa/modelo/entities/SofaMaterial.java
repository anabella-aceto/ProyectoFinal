package restsofa.modelo.entities;

import java.io.Serializable;

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

/**
 * @author David Rodriguez Moral
 * @version 1.0
 * 
 * Clase que representa los materiales que necesita un sofá.
 */

@Entity //Indica que esta clase es una entidad JPA.
@NoArgsConstructor // Anotación para generar un constructor sin argumentos.
@AllArgsConstructor // Anotación para generar un constructor con argumentos.
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente.
@Table(name="sofa_materiales") // Especifica el nombre de la tabla en la base de datos.

public class SofaMaterial implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sm")
	private int idSofaMateriales;
	
	@ManyToOne
	@JoinColumn(name="id_sofa")
	private Sofa sofa;
	
	@ManyToOne
	@JoinColumn(name="id_material")
	private Material material;
	
	@Column(name="Cantidad_utilizada")
	private int cantidadUtilizada; 
	
}
