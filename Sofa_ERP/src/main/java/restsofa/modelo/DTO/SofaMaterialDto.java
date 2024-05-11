package restsofa.modelo.DTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un DTO (Data Transfer Object) para la relación entre sofá y material.
 */

@Component // Indica que esta clase es un componente gestionado por Spring.
@AllArgsConstructor // Genera un constructor con todos los argumentos usando Lombok.
@NoArgsConstructor // Genera un constructor sin argumentos usando Lombok.
@Data // Genera getters, setters, toString, equals y hashCode automáticamente usando Lombok.

public class SofaMaterialDto {

	/**
     * El identificador del material del sofá.
     */
    private int idSofaMateriales;
	
	/**
     * El identificador del sofá.
     */
    private int idSofa;

    /**
     * El identificador del material asociado al sofá.
     */
    private int idMaterial;

    /**
     * La cantidad utilizada del material en el sofá.
     */
    private int cantidadUtilizada;
}