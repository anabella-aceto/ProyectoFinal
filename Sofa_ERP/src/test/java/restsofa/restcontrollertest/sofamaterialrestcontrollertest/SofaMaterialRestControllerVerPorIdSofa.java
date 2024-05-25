package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.SofaMaterial;
import restsofa.restcontroller.SofaMaterialRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "buscarPorSofa" en SofaMaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaMaterialRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerVerPorIdSofa {
	
	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;
	
	/**
	 * Prueba del método "buscarPorSofa" cuando el sofá existe.
	 *
	 * @test
	 * Este método verifica la funcionalidad de buscar materiales de sofá por un sofá existente.
	 * Comprueba que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene la lista de materiales de sofá del cuerpo de la respuesta y verifica que no esté vacía.
	 * Asegura que el cuerpo de la respuesta sea igual a la lista de materiales de sofá obtenida.
	 * 
	 * @return ResponseEntity con la lista de materiales de sofá asociados al sofá existente.
	 * 
	 * @param idSofa El identificador único del sofá existente.
	 */
	@Test
	public void testBuscarPorSofaExistente() {
		int idSofa = 2;
		ResponseEntity<?> respuesta = sofaMaterialRestController.buscarPorSofa(idSofa);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());

		// Obtiene la lista de materiales de sofá del cuerpo de la respuesta
		List<SofaMaterial> sofaMateriales = (List<SofaMaterial>) respuesta.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(sofaMateriales.isEmpty(), "La lista de materiales del sofá no debería estar vacía");

		// Asegura que el cuerpo de la respuesta sea igual a la lista de materiales de sofá obtenida
		assertEquals(sofaMateriales, respuesta.getBody());
	}

	/**
	 * Prueba del método "buscarPorSofa" cuando el sofá no existe.
	 *
	 * @test
	 * Este método verifica la funcionalidad de buscar materiales de sofá por un sofá inexistente.
	 * Comprueba que el código de estado de la respuesta sea HttpStatus.BAD_REQUEST (400).
	 * Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía.
	 * 
	 * @return ResponseEntity con un mensaje de error indicando que la lista de materiales de sofá está vacía.
	 * 
	 * @param idSofa El identificador único del sofá inexistente.
	 */
	@Test
	public void testBuscarPorSofaNoExistente() {
		int idSofa = -1; // Un id de sofá que no existe
		ResponseEntity<?> respuesta = sofaMaterialRestController.buscarPorSofa(idSofa);

		// Asegura que el código de estado de la respuesta sea HttpStatus.BAD_REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());

		// Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía
		assertEquals("La lista está vacía", respuesta.getBody());
	}

}
