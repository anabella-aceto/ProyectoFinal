package restsofa.restcontrollertest.pedidosrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.PedidoRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "borrar" en
 *          PedidoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `PedidoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class PedidoRestControllerTestBorrar {

	@Autowired
	private PedidoRestController pedidoRestController;

	/**
	 * Prueba del método "borrar".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la eliminación del pedido fue
	 *       correcta.
	 *
	 * @param pedidoId El identificador del pedido a eliminar.
	 * @return ResponseEntity con el resultado de la operación de eliminación del
	 *         pedido.
	 */

	@Test
	public void testBorrar() {
		int pedidoId = 9; // Reemplaza con un idPedido válido
		ResponseEntity<?> responseEntity = pedidoRestController.borrar(pedidoId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la eliminación del pedido fue correcta
		assertTrue(mensaje.contains("Pedido eliminado correctamente"), "La eliminación debería ser correcta");
	}

}
