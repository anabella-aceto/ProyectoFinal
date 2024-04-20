package restsofa.modelo.entities;

import java.io.Serializable;
import java.util.Date;

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
 * @author David Rodriguez Moral
 * @version 1.0
 * 
 * Clase que representa un proveedor.
 */

@Entity // Indica que esta clase es una entidad JPA.
@Table(name="proveedores") // Especifica el nombre de la tabla en la base de datos.
@NoArgsConstructor // Anotación para generar un constructor sin argumentos.
@AllArgsConstructor // Anotación para generar un constructor con argumentos.
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente.

public class Proveedor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor")
	private int idProveedor;
	

	    private String nombre;
	    
    
    private int telefono;
    
      private String descripcion; 
}
