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
 * Clase que representa un sofá.
 */

@Entity //Indica que esta clase es una entidad JPA
@Table(name = "sofas") //Especifica el nombre de la tabla en la base de datos.
@NoArgsConstructor // Anotación para generar un constructor sin argumentos
@AllArgsConstructor // Anotación para generar un constructor con argumentos
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente

public class Sofa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sofa")
	private int idSofa;
	
	private String nombre;
	
	
	private String descripcion;
	
	private int patas;
	
	@Column(name="medida_cojin")
	private String medidaCojin;
	
	private double precio;

}
