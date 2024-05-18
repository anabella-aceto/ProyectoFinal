package restsofa.restcontrollertest.estadorestcontrollertest;

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

import restsofa.modelo.entities.Estado;
import restsofa.restcontroller.EstadoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "todos" en
 *          `EstadoRestController`. Verifica el comportamiento del método
 *          "todos" que obtiene la lista de estados.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EstadoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class EstadoRestControllerTestVerTodos {

	@Autowired
	private EstadoRestController estadoRestController;

	/**
	 * Prueba para verificar que la respuesta del método todos() del controlador no
	 * es nula.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaNoNula() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = estadoRestController.todos();

		// Verificar que la respuesta no sea nula
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
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = estadoRestController.todos();

		// Verificar el código de estado de la respuesta
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador no es nulo.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosCuerpoRespuestaNoNulo() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = estadoRestController.todos();

		// Verificar que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador es una lista.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaListaEstados() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = estadoRestController.todos();

		// Verificar que el cuerpo de la respuesta sea una lista de estados
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de estados devuelta por el método todos()
	 * del controlador no está vacía.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosNoVacia() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = estadoRestController.todos();

		// Verificar que la lista de estados no esté vacía
		List<?> listaEstados = (List<?>) responseEntity.getBody();
		assertFalse(listaEstados.isEmpty(), "La lista de estados no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de estados contiene un estado específico.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosContieneEstadoEspecifico() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = estadoRestController.todos();

		// Verificar que la lista de estados contenga un estado específico
		List<Estado> estados = (List<Estado>) responseEntity.getBody();
		boolean contieneEstadoEspecifico = false;
		for (Estado estado : estados) {
			if (estado.getIdEstado() == 2 || "Procesando".equals(estado.getNombre())) {
				contieneEstadoEspecifico = true;
				break;
			}
		}
		assertTrue(contieneEstadoEspecifico, "La lista debe contener estados específicos");
	}
}
