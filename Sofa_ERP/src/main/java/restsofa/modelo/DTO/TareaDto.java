package restsofa.modelo.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import restsofa.modelo.entities.DetallePedido;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Clase que representa un DTO (Data Transfer Object) para una tarea.
 */

@Component // Indica que esta clase es un componente gestionado por Spring.
@NoArgsConstructor // Genera un constructor sin argumentos usando Lombok.
@AllArgsConstructor // Genera un constructor con todos los argumentos usando Lombok.
@Data // Genera getters, setters, toString, equals y hashCode automáticamente usando Lombok.

public class TareaDto {

    /**
     * El identificador de la tarea.
     */
    private int idTarea;

    /**
     * El identificador del empleado asignado a la tarea.
     */
    private int idEmpleado;

    /**
     * El identificador del departamento al que pertenece la tarea.
     */
    private int idDepartamento;

    /**
     * El identificador del estado de la tarea.
     */
    private int idEstado;

    /**
     * La fecha de la tarea.
     */
    private Date fecha;    
    
    /**
     * El detalle de pedido de la tarea.
     */
    private int idDetalle;  

}
