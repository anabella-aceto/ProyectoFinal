package restsofa.restcontrollertest.pedidosrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.PedidoDto;
import restsofa.modelo.entities.Pedido;
import restsofa.restcontroller.PedidoRestController;

@SpringBootTest
public class PedidoRestControllerTestVerTodosSinDto {

	/**
	 * @author Alberto Saboya
	 * @version 1.0
	 * 
	 *          Clase de prueba JUnit para el método "lista" en
	 *          PedidoRestController.
	 *
	 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
	 *
	 * @Autowired Inyecta la instancia de `PedidoRestController` para realizar las
	 *            pruebas.
	 * 
	 */
	@Autowired
	private PedidoRestController pedidoRestController;

	/**
	 * Prueba para verificar que la respuesta del método lista() del controlador no
	 * es nula.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListaPedidosRespuestaNoNula() {
		// Llamar al método lista() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.lista();

		// Verificar que la respuesta no sea nula
		assertNotNull(responseEntity);
	}

	/**
	 * Prueba para verificar el código de estado de la respuesta del método lista()
	 * del controlador.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListaPedidosCodigoEstado() {
		// Llamar al método lista() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.lista();

		// Verificar el código de estado de la respuesta
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método lista() del
	 * controlador no es nulo.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListaPedidosCuerpoRespuestaNoNulo() {
		// Llamar al método lista() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.lista();

		// Verificar que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método lista() del
	 * controlador es una lista.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListaPedidosRespuestaListaPedidos() {
		// Llamar al método lista() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.lista();

		// Verificar que el cuerpo de la respuesta sea una lista de pedidos
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de pedidos devuelta por el método lista()
	 * del controlador no está vacía.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListaPedidosNoVacia() {
		// Llamar al método lista() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.lista();

		// Verificar que la lista de pedidos no esté vacía
		List<?> listaPedidos = (List<?>) responseEntity.getBody();
		assertFalse(listaPedidos.isEmpty());
	}

	/**
	 * Prueba para verificar si la lista de pedidos contiene un pedido específico.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListaPedidosContienePedidoEspecifico() {
		// Llamar al método lista() del controlador
		ResponseEntity<?> responseEntity = pedidoRestController.lista();

		// Verificar que la lista de pedidos contenga un pedido específico
		List<Pedido> pedidos = (List<Pedido>) responseEntity.getBody();
		boolean contienePedidoEspecifico = false;
		for (Pedido pedido : pedidos) {
			if (pedido.getIdPedido() == 4 || pedido.getCliente().getIdCliente() == 5) {
				contienePedidoEspecifico = true;
				break;
			}
		}
		assertTrue(contienePedidoEspecifico, "La lista debe contener pedidos específicos");
	}
}
