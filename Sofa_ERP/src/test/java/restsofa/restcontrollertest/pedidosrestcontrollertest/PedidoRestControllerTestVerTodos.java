package restsofa.restcontrollertest.pedidosrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.PedidoDto;
import restsofa.restcontroller.PedidoRestController;

@SpringBootTest
public class PedidoRestControllerTestVerTodos {

	/**
	 * @author Alberto Saboya
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
	@Autowired
	private PedidoRestController pedidoRestController;

	/**
	 * Prueba del método "todos".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene la
	 *       lista de pedidos del cuerpo de la respuesta. Verifica que la lista no
	 *       esté vacía. Verifica si contiene pedidos específicos.
	 *
	 * @return ResponseEntity con la lista de empleados.
	 */
	@Test
	public void testTodos() {
		ResponseEntity<?> responseEntity = pedidoRestController.todos();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		List<PedidoDto> pedidos = (List<PedidoDto>) responseEntity.getBody();
		assertFalse(pedidos.isEmpty(), "La lista de pedidos no debería estar vacía");

		boolean containsSpecificPedido = false;
		for (PedidoDto pedido : pedidos) {
			if (pedido.getIdPedido() == 4 || pedido.getIdCliente() == 5) {
				containsSpecificPedido = true;
				break;
			}
		}
		assertTrue(containsSpecificPedido, "La lista debe contener pedidos");
	}

}
