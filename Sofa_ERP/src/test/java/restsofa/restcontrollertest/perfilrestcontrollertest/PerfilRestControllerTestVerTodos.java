package restsofa.restcontrollertest.perfilrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
 * Clase de prueba JUnit para el método "todos" en PerfilRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PerfilRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestVerTodos {
	
	@Autowired
	private PerfilRestController perfilRestController;
	
    /**
     * Prueba del método "todos".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene la lista de perfiles del cuerpo de la respuesta.
     * Verifica que la lista no esté vacía.
     * Verifica si contiene perfiles específicos.
     *
     * @return ResponseEntity con la lista de perfiles.
     */	
    @Test
    public void testTodos() {
        // Llama al método "todos"
        ResponseEntity<?> responseEntity = perfilRestController.todos();

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene la lista de perfiles del cuerpo de la respuesta
        List<Perfil> perfiles = (List<Perfil>) responseEntity.getBody();

        // Verifica que la lista no esté vacía
        assertFalse(perfiles.isEmpty(), "La lista de perfiles no debería estar vacía");

        // Verifica si contiene perfiles específicos
        boolean contienePerfilEspecifico = false;
        for (Perfil perfil : perfiles) {
            if (perfil.getIdPerfil() == 2 || perfil.getRol().equals("Comercial")) {
                contienePerfilEspecifico = true;
                break;
            }
        }
        assertTrue(contienePerfilEspecifico, "La lista debe contener perfiles específicos");
    }

}
