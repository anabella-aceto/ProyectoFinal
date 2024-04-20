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
 * Clase de prueba JUnit para el método "borrar" en DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
 * 
 * @author Alberto Saboya
 * @version 1.0
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
    public void testBorrar() {
        int depId = 5; // Reemplaza con un idDepartamento válido
        ResponseEntity<?> responseEntity = departamentoRestController.eliminarDepto(depId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Departamento eliminado correctamente"), "La eliminación debería ser correcta");
    }

}
