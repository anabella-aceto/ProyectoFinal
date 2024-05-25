package restsofa.restcontrollertest.pedidosrestcontrollertest;

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

import restsofa.modelo.DTO.PedidoDto;
import restsofa.restcontroller.PedidoRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "todos" en
 *          PedidoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `PedidoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class PedidoRestControllerTestVerTodos {

	
	@Autowired
	private PedidoRestController pedidoRestController;

	/**
	 * Prueba para verificar el código de estado de la respuesta del método todos()
	 * del controlador.
	 * 
     * @Test
     * Anota este método como una prueba JUnit.	 
	 */
	@Test
	public void testTodosCodigoEstado() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.todos();

		// Verificar el código de estado de la respuesta
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador no es nulo.
	 * 
     * @Test
     * Anota este método como una prueba JUnit.	 
	 */
	@Test
	public void testTodosCuerpoRespuestaNoNulo() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.todos();

		// Verificar que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que la lista de pedidos devuelta por el método todos()
	 * del controlador no está vacía.
	 * 
     * @Test
     * Anota este método como una prueba JUnit.	 
	 */
	@Test
	public void testTodosNoVacia() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.todos();

		// Verificar que la lista de pedidos no esté vacía
		List<?> pedidos = (List<?>) responseEntity.getBody();
		assertFalse(pedidos.isEmpty(), "La lista de pedidos no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de pedidos contiene un pedido específico.
	 * 
     * @Test
     * Anota este método como una prueba JUnit.	 
	 */
	@Test
	public void testTodosContienePedidoEspecifico() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.todos();

		// Verificar que la lista de pedidos contenga un pedido específico
		List<PedidoDto> pedidos = (List<PedidoDto>) responseEntity.getBody();

		boolean contienePedido = false;
		for (PedidoDto pedido : pedidos) {
			if (pedido.getIdPedido() == 4 || pedido.getIdCliente() == 5) {
				contienePedido = true;
				break;
			}
		}

		assertTrue(contienePedido, "La lista debe contener pedidos específicos");
	}
}
