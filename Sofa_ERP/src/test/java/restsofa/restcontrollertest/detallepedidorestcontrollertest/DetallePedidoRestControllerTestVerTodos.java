package restsofa.restcontrollertest.detallepedidorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
 * Clase de prueba JUnit para el método "todos" en DetallePedidoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DetallePeidoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class DetallePedidoRestControllerTestVerTodos {
	
	@Autowired
	private DetallePedidoRestController detallePedidoRestController;
	
	/**
	 * Prueba del método "todos".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea OK.
	 * Obtiene la lista de detalles de pedido del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Verifica si contiene detalles de pedido específicos.
	 *
	 * @return ResponseEntity con la lista de detalles de pedido.
	 */
	@Test
	public void testTodos() {

        ResponseEntity<?> responseEntity = detallePedidoRestController.todos();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verificar el tipo de objeto devuelto por el controlador
        assertTrue(responseEntity.getBody() instanceof List);

        // Convertir la lista de DTOs devuelta por el controlador
        List<DetallePedidoDto> detallesPedidosDto = (List<DetallePedidoDto>) responseEntity.getBody();

        // Verificar que la lista no esté vacía
        assertFalse(detallesPedidosDto.isEmpty(), "La lista de detalles de pedidos no debería estar vacía");

        // Verificar que al menos un detalle de pedido tenga ciertos valores específicos
        boolean containsSpecificDetail = false;
        for (DetallePedidoDto detallePedidoDto : detallesPedidosDto) {
            if (detallePedidoDto.getIdDePed() == 1 || detallePedidoDto.getIdPedido() == 3) {
                containsSpecificDetail = true;
                break;
            }
        }
        assertTrue(containsSpecificDetail, "La lista debe contener detalles de pedido con ciertos valores específicos");
    }


}
