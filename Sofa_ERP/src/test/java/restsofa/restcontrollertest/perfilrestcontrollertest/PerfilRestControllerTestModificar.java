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
 * @author Alberto Saboya
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
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la modificación fue exitosa.
     *
     * @param perfilExistente El perfil con los datos a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
	
    @Test
    public void testModificar() {
        // Crea un perfil de ejemplo
        Perfil perfilExistente = new Perfil();
        perfilExistente.setIdPerfil(4);; // Establece un idPerfil existente
        perfilExistente.setRol("viajante");; // Establece el rol del perfil

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = perfilRestController.modificar(perfilExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
    }

}
