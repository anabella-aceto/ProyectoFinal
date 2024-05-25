package restsofa.restcontrollertest.pedidosrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.PedidoDto;
import restsofa.restcontroller.PedidoRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez 
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "uno" en PedidoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PedidoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class PedidoRestControllerTestVerUno {

	@Autowired
	private PedidoRestController pedidoRestController;

	/**
	 * Prueba del método "uno".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene el pedido del cuerpo de la respuesta.
	 * Verifica que el pedido no sea nulo.
	 * Verifica que el pedido tenga el idPedido correcto.
	 * Verifica si el idCliente del pedido es correcto.
	 *
	 * @param empId El identificador del pedido a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void testBuscarPorId() {
		int pedidoId = 3;
		ResponseEntity<?> responseEntity = pedidoRestController.uno(pedidoId);

		// Asegura que el código de pedido de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el pedido del cuerpo de la respuesta
		PedidoDto pedido = (PedidoDto) responseEntity.getBody();

		// Verifica que el pedido no sea nulo
		assertNotNull(pedido, "El pedido no debería ser nulo");

		// Verifica que el pedido tenga el idPedido correcto
		assertEquals(pedidoId, pedido.getIdPedido(), "No se encuentra el pedido");

		// Verifica si el nombre del empleado es correcto
		assertEquals(1, pedido.getIdCliente(), "El idCliente del pedido no coincide");
	}

}
