package restsofa.restcontrollertest.detallepedidorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * Clase de prueba JUnit para el método "uno" en DetallePedidoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DetallePedidoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class DetallePedidoRestControllerTestVerUno {
	
	@Autowired
	private DetallePedidoRestController detallePedidoRestController;
	
	/**
	 * Prueba del método "uno".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene el detalle de pedido del cuerpo de la respuesta.
	 * Verifica que el detalle de pedido no sea nulo.
	 * Verifica que el detalle de pedido tenga el idDetallePedido correcto.
	 * Verifica si el nombre del detalle de pedido es correcto.
	 *
	 * @param idDetallePedido El identificador del detalle de pedido a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void testbuscarPorId() {
		int idDetallePedido = 1;
		ResponseEntity<?> responseEntity = detallePedidoRestController.uno(idDetallePedido);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el empleado del cuerpo de la respuesta
		DetallePedidoDto detallePedido = (DetallePedidoDto) responseEntity.getBody();

		// Verifica que el detalle de pedido no sea nulo
		assertNotNull(detallePedido, "El detalle de pedido no debería ser nulo");

		// Verifica que el detalle de pedido tenga el idDetallePedido correcto
		assertEquals(idDetallePedido, detallePedido.getIdDePed(), "No se encuentra el detalle de pedido");

		// Verifica si el pedido asociado es correcto
		assertEquals(3, detallePedido.getIdPedido(), "El pedido asociado no coincide");
	}

}
