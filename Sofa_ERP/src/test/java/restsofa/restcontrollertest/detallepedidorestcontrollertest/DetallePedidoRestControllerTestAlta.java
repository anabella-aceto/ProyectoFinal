package restsofa.restcontrollertest.detallepedidorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.DetallePedidoDto;
import restsofa.restcontroller.DetallePedidoRestController;

/**
 * @author Alberto Saboya, Anabella Aceto David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "alta" en
 *          DetallePedidoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `DetallePedidoRestController` para
 *            realizar las pruebas.
 * 
 */
@SpringBootTest
public class DetallePedidoRestControllerTestAlta {

	@Autowired
	private DetallePedidoRestController detallePedidoRestController;

	/**
	 * Prueba el método "alta" para agregar un nuevo detalle de pedido.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Crea un detalle de pedido de ejemplo con valores específicos. Llama al
	 *       método "alta" para agregar el nuevo detalle de pedido. Verifica que el
	 *       código de estado de la respuesta sea CREATED (201). Verifica que el
	 *       mensaje de la respuesta indique que el detalle se procesó
	 *       correctamente.
	 *
	 * @return ResponseEntity con el resultado de la operación de alta.
	 */
	@Test
	public void testAlta() {

		// Crea un detalle de pedido de ejemplo
		DetallePedidoDto nuevoDetalle = new DetallePedidoDto();
		nuevoDetalle.setIdPedido(6); // Establece el pedido del detalle de pedido
		nuevoDetalle.setIdSofa(1); // Establece el sofa del detalle de pedido
		nuevoDetalle.setCantidad(3); // Establece la cantidad del detalle de pedido
		nuevoDetalle.setDensCojin(20); // Establece la densidad del cojín del detalle de pedido
		nuevoDetalle.setFecha(new Date()); // Establece la fecha del detalle de pedido
		nuevoDetalle.setPlazas(3); // Establece el número de plazas de detalle e pedido
		nuevoDetalle.setPrecio(1500); // Establece el precio del detalle de pedido

		// Llama al método "alta"
		ResponseEntity<?> responseEntity = detallePedidoRestController.alta(nuevoDetalle);

		// Verifica que el código de estado de la respuesta sea CREATED (201)
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		// Verifica que el mensaje de la respuesta indique que el detalle se procesó
		// correctamente
		String mensajeRespuesta = (String) responseEntity.getBody();
		System.out.println("Mensaje de respuesta: " + mensajeRespuesta); // Depuración
		assertNotNull(mensajeRespuesta, "El mensaje de respuesta no debería ser nulo");
		assertTrue(mensajeRespuesta.contains("Detalle de pedido procesado correctamente"),
				"El detalle de pedido no se procesó correctamente");
	}
}
