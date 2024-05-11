package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Sofa;
import restsofa.restcontroller.SofaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "todos" en SofaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestVerTodos {

	@Autowired
	private SofaRestController sofaRestController;
	
    /**
     * Prueba del método "todos".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene la lista de sofás del cuerpo de la respuesta.
     * Verifica que la lista no esté vacía.
     * Verifica si contiene sofás específicos.
     *
     * @return ResponseEntity con la lista de sofás.
     */
	@Test
	public void testTodos() {
		// Llama al método "todos"
		ResponseEntity<?> responseEntity = sofaRestController.todos();

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene la lista de clientes del cuerpo de la respuesta
		List<Sofa> sofas = (List<Sofa>) responseEntity.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(sofas.isEmpty(), "La lista de sofas no debería estar vacía");

		// Verifica si contiene sofás específicos
		boolean contieneSofaEspecifico = false;
		for (Sofa sofa : sofas) {
			if (sofa.getIdSofa() == 3 || sofa.getNombre().equals("Luna")) {
				contieneSofaEspecifico = true;
				break;
			}
		}
		assertTrue(contieneSofaEspecifico, "La lista debe contener sofás específicos");
	}

}
