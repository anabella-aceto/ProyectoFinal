package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.EmpleadoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrarUno" en EmpleadoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EmpleadoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class EmpleadoRestControllerTesBorrar {

    @Autowired
    private EmpleadoRestController empleadoRestController;

    /**
     * Prueba del método "borrarUno".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la eliminación fue correcta.
     *
     * @param empId El identificador del empleado a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrar() {
        int empId = 9; // Reemplaza con un idEmpleado válido
        ResponseEntity<?> responseEntity = empleadoRestController.borrarUno(empId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Empleado eliminado correctamente"), "La eliminación debería ser correcta");
    }
}

