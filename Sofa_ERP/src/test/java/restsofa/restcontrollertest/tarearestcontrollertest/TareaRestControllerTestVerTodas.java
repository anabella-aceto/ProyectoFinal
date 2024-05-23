package restsofa.restcontrollertest.tarearestcontrollertest;

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

import restsofa.modelo.DTO.DetallePedidoDto;
import restsofa.modelo.DTO.TareaDto;
import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "todos" en TareaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `TareaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestVerTodas {

	@Autowired
	TareaRestController tareaRestController;

	/**
	 * Prueba para verificar que la respuesta del método todos() del controlador no
	 * es nula.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaNoNula() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = tareaRestController.todos();

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
		ResponseEntity<?> responseEntity = tareaRestController.todos();

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
		ResponseEntity<?> responseEntity = tareaRestController.todos();

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
	public void testTodosRespuestaListaDetallesPedido() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = tareaRestController.todos();

		// Verificar que el cuerpo de la respuesta sea una lista de tareas
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de tareas devuelta por el método todos()
	 * del controlador no está vacía.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosNoVacia() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = tareaRestController.todos();

		// Verificar que la lista de detalles de pedido no esté vacía
		List<DetallePedidoDto> tareas = (List<DetallePedidoDto>) responseEntity.getBody();
		assertFalse(tareas == null || tareas.isEmpty(), "La lista de tareas no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de tareas contiene una tarea
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosContieneTareaEspecifica() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = tareaRestController.todos();

		// Verificar que la lista de tareas que contenga una tarea
		// específica
		List<TareaDto> tareas = (List<TareaDto>) responseEntity.getBody();
		boolean contieneTareaEspecifica = false;
		for (TareaDto tarea : tareas) {
			if (tarea.getIdTarea() == 1 || tarea.getIdEmpleado() == 1) {
				contieneTareaEspecifica = true;
				break;
			}
		}
		assertTrue(contieneTareaEspecifica, "La lista debe contener tareas específicas");
	}
}
