package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.SofaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrar" en SofaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestBorrar {
	
	@Autowired
	private SofaRestController sofaRestController;

    /**
     * Prueba del método "borrar".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la eliminación fue correcta.
     *
     * @param sofaId El identificador del cliente a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrar() {
        int sofaId = 6; // Reemplaza con un idSofa válido
        ResponseEntity<?> responseEntity = sofaRestController.borrar(sofaId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Sofá eliminado correctamente"), "La eliminación debería ser correcta");
    }

}
