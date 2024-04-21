package restsofa.restcontrollertest.perfilrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.PerfilRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrar" en PerfilRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PerfilRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestBorrar {
	
	@Autowired
	private PerfilRestController perfilRestController;
	
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
     * @param perfilId El identificador del perfil a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
	
    @Test
    public void testBorrar() {
        int perfilId = 4; // Reemplaza con un idPerfil válido
        ResponseEntity<?> responseEntity = perfilRestController.borrar(perfilId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Perfil eliminado correctamente"), "La eliminación debería ser correcta");
    }

}
