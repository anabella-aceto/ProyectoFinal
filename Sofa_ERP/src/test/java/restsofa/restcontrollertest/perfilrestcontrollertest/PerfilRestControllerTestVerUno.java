package restsofa.restcontrollertest.perfilrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * Clase de prueba JUnit para el método "uno" en PerfilRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PerfilRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestVerUno {
	
	@Autowired
	private PerfilRestController perfilRestController;
	
    /**
     * Prueba del método "uno".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el perfil del cuerpo de la respuesta.
     * Verifica que el perfil no sea nulo.
     * Verifica que el perfil tenga el idPerfil correcto.
     * Verifica si el nombre del perfil es correcto.
     *
     * @param perfilId El identificador del perfil a buscar.
     * @return ResponseEntity con el resultado de la búsqueda.
     */
    @Test
    public void testUno() {
        int perfilId = 2;
        ResponseEntity<?> responseEntity = perfilRestController.uno(perfilId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el perfil del cuerpo de la respuesta
        Perfil perfil = (Perfil) responseEntity.getBody();

        // Verifica que el perfil no sea nulo
        assertNotNull(perfil, "El perfil no debería ser nulo");

        // Verifica que el perfil tenga el idPerfil correcto
        assertEquals(perfilId, perfil.getIdPerfil(), "El idPerfil no coincide");

        // Verifica si el nombre del cliente es correcto
        assertEquals("comercial", perfil.getRol(), "El nombre del perfil no coincide");
    }

}
