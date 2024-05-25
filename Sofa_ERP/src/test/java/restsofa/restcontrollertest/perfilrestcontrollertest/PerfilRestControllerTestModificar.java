package restsofa.restcontrollertest.perfilrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Perfil;
import restsofa.restcontroller.PerfilRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "modificar" en PerfilRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PerfilRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestModificar {
	
	@Autowired
	private PerfilRestController perfilRestController;
	
    /**
     * Prueba del método "modificar".
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la modificación fue exitosa.
     *
     * @param perfilExistente El perfil con los datos a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarPerfilExiste() {
        // Crea un perfil de ejemplo
        Perfil perfilExistente = new Perfil();
        perfilExistente.setIdPerfil(4); // Establece un idPerfil existente
        perfilExistente.setRol("viajante"); // Establece el rol del perfil

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = perfilRestController.modificar(perfilExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
    }

    /**
     * Prueba del método "modificar" cuando el perfil a modificar no se encuentra.
     *
     * Verifica que el código de estado de la respuesta sea NOT_FOUND.
     * Obtiene el mensaje de la respuesta.
     * Verifica que se reciba el mensaje adecuado.
     *
     * @param perfilNoExistente El perfil que no se encuentra en el sistema.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarPerfilNoExiste() {
        // Crea un perfil con un idPerfil que no existe
        Perfil perfilNoExistente = new Perfil();
        perfilNoExistente.setIdPerfil(-1); // Establece un idPerfil inexistente
        perfilNoExistente.setRol("viajante"); // Establece el rol del perfil

        // Llama al método "modificar" con un perfil que no existe
        ResponseEntity<?> responseEntity = perfilRestController.modificar(perfilNoExistente);

        // Verifica que el código de estado de la respuesta sea NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que se reciba el mensaje adecuado
        assertTrue(mensaje.contains("Perfil no encontrado"), "Debería recibir un mensaje indicando que el perfil no fue encontrado");
    }
}
