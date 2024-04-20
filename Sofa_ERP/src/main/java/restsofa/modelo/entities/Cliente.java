package restsofa.modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Rodriguez Moral
 * @version 1.0
 * 
 * Clase que representa un cliente.
 */

@NoArgsConstructor // Anotación para generar un constructor sin argumentos
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente
@Entity // Anotación para indicar que esta clase es una entidad JPA
@Table(name="clientes") // Anotación para especificar el nombre de la tabla en la base de datos

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private int idCliente;
	
	private String nombre;
	
	private String apellidos;
	
	private String direccion;
	
	private String email;
	
	private String telefono;

}
