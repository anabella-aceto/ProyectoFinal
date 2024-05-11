package restsofa.restcontrollertest.pedidosrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.PedidoDto;
import restsofa.restcontroller.PedidoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0 * 
 * 
 * Clase de prueba JUnit para el método "modificar" en PedidoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `PedidoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class PedidoRestControllerTestModificar {

	@Autowired
	private PedidoRestController pedidoRestController;

	/**
	 * Prueba del método "modificar".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la modificación fue exitosa.
	 *
	 * @param pedidoExistente El pedido con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificar() {
		// Crea un empleado de ejemplo
		PedidoDto pedidoExistente = new PedidoDto();
		pedidoExistente.setIdPedido(9);
		; // Establece un idPedido existente
		pedidoExistente.setIdCliente(1);
		; // Establece el idCliente del pedido

		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = pedidoRestController.modificar(pedidoExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Pedido modificado correctamente"), "La modificación debería ser correcta");
	}

}
