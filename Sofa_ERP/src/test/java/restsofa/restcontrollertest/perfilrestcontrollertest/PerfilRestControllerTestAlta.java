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
 * Clase de prueba JUnit para el método "alta" en PerfilRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PerfilRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestAlta {
	
	@Autowired
	private PerfilRestController perfilRestController;
	
    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el perfil guardado del cuerpo de la respuesta.
     * Verifica que el perfil guardado no sea nulo.
     * Verifica que el rol del perfil guardado coincida con el rol esperado.
     *
     * @param nuevoPerfil El perfil a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crea un perfil de ejemplo
        Perfil nuevoPerfil = new Perfil();
        nuevoPerfil.setRol("repartidor");; // Establece el rol del perfil

        // Llama al método "alta"
        ResponseEntity<?> responseEntity = perfilRestController.alta(nuevoPerfil);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el perfil guardado del cuerpo de la respuesta
        Perfil perfilGuardado = (Perfil) responseEntity.getBody();

        // Verifica que el perfil guardado no sea nulo
        assertNotNull(perfilGuardado, "El perfil guardado no debería ser nulo");

        // Verifica que el rol del perfil guardado coincida con el rol esperado
        assertEquals("repartidor", perfilGuardado.getRol(), "El rol del perfil guardado no coincide");
    }

}
