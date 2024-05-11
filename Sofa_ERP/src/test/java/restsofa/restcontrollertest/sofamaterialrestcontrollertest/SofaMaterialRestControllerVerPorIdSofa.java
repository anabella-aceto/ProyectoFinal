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
	 * Prueba del método "buscarPorSofa".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene la lista de materiales de sofá del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Asegura que el cuerpo de la respuesta sea igual a la lista de muestra.
	 * Llama al método buscarPorSofa con un idSofa inválido (99).
	 * Asegura que el código de estado de la respuesta sea 400.
	 * Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía.
	 *
	 * @param idSofa El identificador del sofá para filtrar los materiales de sofá.
	 * @return ResponseEntity con la lista de materiales de sofá.
	 */
	@Test
	public void testbuscarPorId() {
		int idSofa = 2;
		ResponseEntity<?> respuesta = sofaMaterialRestController.buscarPorSofa(idSofa);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());

		// Obtiene el material de sofá del cuerpo de la respuesta
		List<SofaMaterial> sofaMateriales = (List<SofaMaterial>) respuesta.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(sofaMateriales.isEmpty(), "La lista de materiales del sofá no debería estar vacía");

		// Asegura que el cuerpo de la respuesta sea igual a la lista de muestra
		assertEquals(sofaMateriales, respuesta.getBody());

		// Llama al método buscarPorSofa con un ID inválido (99)
		respuesta = sofaMaterialRestController.buscarPorSofa(99);

		// Asegura que el código de estado de la respuesta sea 400
		assertEquals(400, respuesta.getStatusCodeValue());

		// Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía
		assertEquals("La lista está vacía", respuesta.getBody());
	}

}
