package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.EmpleadoDto;
import restsofa.restcontroller.EmpleadoRestController;

/**
 * Clase de prueba JUnit para el método "modificarEmpleado" en EmpleadoRestController.
 */
@SpringBootTest
public class EmpleadoRestControllerTesModificar {

    @Autowired
    private EmpleadoRestController empleadoRestController;

    /**
     * Prueba del método "modificarEmpleado".
     */
    @Test
    public void testModificar() {
        // Crea un empleado de ejemplo
        EmpleadoDto empExistente = new EmpleadoDto();
        empExistente.setIdEmpleado(6); // Establece un idEmpleado existente
        empExistente.setNombre("Roberto"); // Establece el nombre del empleado

        // Llama al método "modificarEmpleado"
        ResponseEntity<?> responseEntity = empleadoRestController.modificarEmpleado(empExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
    }
}
