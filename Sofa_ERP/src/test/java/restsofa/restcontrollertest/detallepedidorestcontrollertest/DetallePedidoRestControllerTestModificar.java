package restsofa.restcontrollertest.detallepedidorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.DetallePedidoDto;
import restsofa.restcontroller.DetallePedidoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0 * Clase de prueba JUnit para el método "modificar" en
 *          DetallePedidoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `DetallePedidoRestController` para
 *            realizar las pruebas.
 * 
 */
@SpringBootTest
public class DetallePedidoRestControllerTestModificar {

	@Autowired
	private DetallePedidoRestController detallePedidoRestController;

	/**
	 * Realiza una prueba para modificar un detalle de pedido existente.
	 *
	 * @param detalleDto El detalle de pedido que se va a modificar. Debe contener
	 *                   un idDePed existente, idPedido y idSofa.
	 * @return ResponseEntity<?> Una respuesta HTTP que indica el resultado de la
	 *         modificación. Si tiene éxito, el código de estado será 200 (OK) y el
	 *         cuerpo contendrá un mensaje indicando que el detalle de pedido se
	 *         modificó correctamente. Si falla, el código de estado será diferente
	 *         de 200 y el cuerpo contendrá un mensaje de error.
	 */
	@Test
	public void testModificarDetallePedidoExistente() {
		// Crea un detalle de pedido de ejemplo
		DetallePedidoDto detalleDto = new DetallePedidoDto();
		detalleDto.setIdDePed(4); // Establece un idDePed existente
		detalleDto.setIdPedido(4); // Establece el idPedido
		detalleDto.setIdSofa(1); // Establece el idSofa
		detalleDto.setFecha(new Date());
		detalleDto.setDensCojin(20);
		detalleDto.setCantidad(2);
		detalleDto.setPlazas(3);
		detalleDto.setPrecio(1500);

		// Llama al método "modificar"
		ResponseEntity<?> response = detallePedidoRestController.modificar(detalleDto);

		// Verifica que la respuesta sea la esperada
		assertEquals(200, response.getStatusCodeValue()); // Verifica que el código de estado sea 200 (OK)
		assertEquals("Detalle de pedido modificado correctamente", response.getBody()); // Verifica el mensaje de la
																						// respuesta
	}

	/**
	 * Realiza una prueba para modificar un detalle de pedido que no existe.
	 *
	 * @param detalleDto El detalle de pedido que se va a intentar modificar. Debe
	 *                   contener un idDePed que no existe.
	 * @return ResponseEntity<?> Una respuesta HTTP que indica el resultado de la
	 *         modificación. Si tiene éxito, el código de estado será 200 (OK) y el
	 *         cuerpo contendrá un mensaje indicando que el detalle de pedido se
	 *         modificó correctamente. Si falla, el código de estado será diferente
	 *         de 200 y el cuerpo contendrá un mensaje de error.
	 */
	@Test
	public void testModificarDetallePedidoNoExistente() {
		// Crea un detalle de pedido con un idDePed no existente
		DetallePedidoDto detalleDto = new DetallePedidoDto();
		detalleDto.setIdDePed(-1); // Establece un id que no existe

		// Llama al método "modificar"
		ResponseEntity<?> response = detallePedidoRestController.modificar(detalleDto);

		// Verifica que la respuesta sea la esperada
		assertEquals(404, response.getStatusCodeValue()); // Verifica que el código de estado sea 404 (Not Found)
		assertEquals("El detalle de pedido con el ID -1 no existe", response.getBody()); // Verifica el mensaje de la
																							// respuesta
	}
}
