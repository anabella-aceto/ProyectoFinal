package restsofa.restcontrollertest.detallepedidorestcontrollertest;

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
import restsofa.restcontroller.DetallePedidoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "todos" en
 *          DetallePedidoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `DetallePeidoRestController` para realizar
 *            las pruebas.
 * 
 */
@SpringBootTest
public class DetallePedidoRestControllerTestVerTodos {

	@Autowired
	private DetallePedidoRestController detallePedidoRestController;

	/**
	 * Prueba para verificar que la respuesta del método todos() del controlador no
	 * es nula.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaNoNula() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = detallePedidoRestController.todos();

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
		ResponseEntity<?> responseEntity = detallePedidoRestController.todos();

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
		ResponseEntity<?> responseEntity = detallePedidoRestController.todos();

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
		ResponseEntity<?> responseEntity = detallePedidoRestController.todos();

		// Verificar que el cuerpo de la respuesta sea una lista de detalles de pedido
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de detalles de pedido devuelta por el
	 * método todos() del controlador no está vacía.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosNoVacia() {
	    // Llamar al método todos() del controlador
	    ResponseEntity<?> responseEntity = detallePedidoRestController.todos();

	    // Verificar que la lista de detalles de pedido no esté vacía
	    List<DetallePedidoDto> detallesPedidos = (List<DetallePedidoDto>) responseEntity.getBody();
	    assertFalse(detallesPedidos == null || detallesPedidos.isEmpty(), "La lista de detalles de pedido no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de detalles de pedido contiene un detalle
	 * de pedido específico.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosContieneDetallePedidoEspecifico() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = detallePedidoRestController.todos();

		// Verificar que la lista de detalles de pedido contenga un detalle de pedido
		// específico
		List<DetallePedidoDto> detallesPedidos = (List<DetallePedidoDto>) responseEntity.getBody();
		boolean contieneDetallePedidoEspecifico = false;
		for (DetallePedidoDto detallePedido : detallesPedidos) {
			if (detallePedido.getIdDePed() == 1 || detallePedido.getIdPedido() == 3) {
				contieneDetallePedidoEspecifico = true;
				break;
			}
		}
		assertTrue(contieneDetallePedidoEspecifico, "La lista debe contener detalles de pedido específicos");
	}
}
