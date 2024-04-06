package restsofa.modelo.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un DTO (Data Transfer Object) para un empleado.
 */

@Component // Indica que esta clase es un componente gestionado por Spring.
@AllArgsConstructor // Genera un constructor con todos los argumentos usando Lombok.
@NoArgsConstructor // Genera un constructor sin argumentos usando Lombok.
@Data // Genera getters, setters, toString, equals y hashCode automáticamente usando Lombok.

public class EmpleadoDto {

    /**
     * El identificador del empleado.
     */
    private int idEmpleado;

    /**
     * El nombre del empleado.
     */
    private String nombre;

    /**
     * Los apellidos del empleado.
     */
    private String apellidos;   

    /**
     * El identificador del departamento al que pertenece el empleado.
     */
    private int idDepartamento;

    /**
     * El identificador del perfil del empleado.
     */
    private int idPerfil;

    /**
     * La fecha de ingreso del empleado.
     */
    private Date fechaIngreso;

    /**
     * El salario del empleado.
     */
    private double salario;
}
