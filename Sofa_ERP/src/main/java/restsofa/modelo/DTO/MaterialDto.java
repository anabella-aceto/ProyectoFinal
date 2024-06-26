package restsofa.modelo.DTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0Clase que representa un DTO (Data Transfer Object) para un material.
 */

@Component // Indica que esta clase es un componente gestionado por Spring
@NoArgsConstructor // Genera un constructor sin argumentos usando Lombok.
@AllArgsConstructor // Genera un constructor con todos los argumentos usando Lombok.
@Data // Genera getters, setters, toString, equals y hashCode automáticamente usando Lombok.

public class MaterialDto {

    /**
     * El identificador del material.
     */
    private int idMaterial;

    /**
     * El nombre del material.
     */
    private String nombre;

    /**
     * La descripción del material.
     */
    private String descripcion;

    /**
     * La cantidad disponible del material.
     */
    private int cantidad;

    /**
     * El identificador del proveedor del material.
     */
    private int idProveedor;

    /**
     * La referencia del material proporcionada por el proveedor.
     */
    private int refMaterialProveedor;
    
    /**
     * La categoria a la que pertenece el material
     */
    private String categoria;
    
    /**
     * La unidad de medida de ese material
     */
    private String unidadMedida;
     
}
	
	
	
	


