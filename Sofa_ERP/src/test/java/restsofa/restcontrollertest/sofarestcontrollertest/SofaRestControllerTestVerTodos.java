package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
 *          Clase de prueba JUnit para el método "todos" en SofaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestVerTodos {

	@Autowired
	private SofaRestController sofaRestController;

	/**
	 * Prueba para verificar que la respuesta del método todos() del controlador no
	 * es nula.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaNoNula() {
		// Llama al método todos() del controlador
		ResponseEntity<?> responseEntity = sofaRestController.todos();

		// Verifica que la respuesta no sea nula
		assertNotNull(responseEntity);
	}

	/**
	 * Prueba para verificar el código de estado de la respuesta del método todos()
	 * del controlador.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosCodigoEstado() {
		// Llama al método todos() del controlador
		ResponseEntity<?> responseEntity = sofaRestController.todos();

		// Verifica el código de estado de la respuesta
		assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador no es nulo.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosCuerpoRespuestaNoNulo() {
		// Llama al método todos() del controlador
		ResponseEntity<?> responseEntity = sofaRestController.todos();

		// Verifica que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador es una lista.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaListaSofas() {
		// Llama al método todos() del controlador
		ResponseEntity<?> responseEntity = sofaRestController.todos();

		// Verifica que el cuerpo de la respuesta sea una lista de sofás
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de sofás devuelta por el método todos()
	 * del controlador no está vacía.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosNoVacia() {
		// Llama al método todos() del controlador
		ResponseEntity<?> responseEntity = sofaRestController.todos();

		// Verifica que la lista de sofás no esté vacía
		List<?> sofas = (List<?>) responseEntity.getBody();
		assertFalse(sofas.isEmpty());
	}

	/**
	 * Prueba para verificar si la lista de sofás contiene un sofá específico.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosContieneSofaEspecifico() {
		// Llama al método todos() del controlador
		ResponseEntity<?> responseEntity = sofaRestController.todos();

		// Verifica si la lista de sofás contiene un sofá específico
		List<Sofa> sofas = (List<Sofa>) responseEntity.getBody();
		boolean contieneSofaEspecifico = false;
		for (Sofa sofa : sofas) {
			if (sofa.getIdSofa() == 3 || "Luna".equals(sofa.getNombre())) {
				contieneSofaEspecifico = true;
				break;
			}
		}
		assertTrue(contieneSofaEspecifico, "La lista debe contener sofás específicos");
	}
}