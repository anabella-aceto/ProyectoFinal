package restsofa.restcontrollertest.departamentorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.DepartamentoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrar" en DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest

public class DepartamentoRestControllerTestBorrar {
	
    @Autowired
    private DepartamentoRestController departamentoRestController;
    
    /**
     * Prueba del método "eliminarDpto".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la eliminación fue correcta.
     *
     * @param depId El identificador del departamento a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    
    @Test
    public void testBorrarDepartamentoExiste() {
        int depId = 6; // Reemplaza con un idDepartamento válido
        ResponseEntity<?> responseEntity = departamentoRestController.eliminarDepto(depId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Departamento eliminado correctamente"), "La eliminación debería ser correcta");
    }
    
    /**
     * Prueba del método "eliminarDpto" cuando el departamento no existe.
     * 
     * @Test
     * Anota este método como una prueba JUnit.
     * 
     * Verifica que el código de estado de la respuesta sea 404 (Not Found).
     * Obtiene el mensaje de la respuesta.
     * Verifica que el mensaje indica que el departamento no fue encontrado.
     * 
     * @param depId El identificador del departamento a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrarDepartamentoNoExiste() {
        int depId = -1; // Un id de departamento que no existe
        ResponseEntity<?> responseEntity = departamentoRestController.eliminarDepto(depId);

        // Verifica que el código de estado de la respuesta sea 404 (Not Found)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que el mensaje indica que el departamento no existe
        assertTrue(mensaje.contains("Departamento no encontrado"), "El mensaje debería indicar que el departamento no existe");
    }

}
