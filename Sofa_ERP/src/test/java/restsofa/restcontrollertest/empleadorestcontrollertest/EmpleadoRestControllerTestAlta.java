package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.EmpleadoDto;
import restsofa.modelo.entities.Empleado;
import restsofa.restcontroller.EmpleadoRestController;

/**
 * Clase de prueba JUnit para el método "alta" en EmpleadoRestController.
 */
@SpringBootTest
public class EmpleadoRestControllerTestAlta {

    @Autowired
    private EmpleadoRestController empleadoRestController;

    /**
     * Prueba del método "alta".
     */
    @Test
    public void testAlta() {
        // Crea un empleado de ejemplo
        EmpleadoDto nuevoEmpleado = new EmpleadoDto();
        nuevoEmpleado.setNombre("Elisa"); // Establece el nombre del empleado

        // Llama al método "alta"
        ResponseEntity<?> responseEntity = empleadoRestController.alta(nuevoEmpleado);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el empleado guardado del cuerpo de la respuesta
        Empleado empGuardado = (Empleado) responseEntity.getBody();

        // Verifica que el empleado guardado no sea nulo
        assertNotNull(empGuardado, "El empleado guardado no debería ser nulo");

        // Verifica si el nombre del empleado es correcto
        assertEquals("Elisa", empGuardado.getNombre(), "El nombre del empleado guardado no coincide");
    }
}
