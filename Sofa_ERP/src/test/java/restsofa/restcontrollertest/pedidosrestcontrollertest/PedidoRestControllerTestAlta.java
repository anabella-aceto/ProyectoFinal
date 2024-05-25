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

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "alta" en PedidoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PedidoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class PedidoRestControllerTestAlta {
	
	@Autowired
	private PedidoRestController pedidoRestController;
	
    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el pedido guardado del cuerpo de la respuesta.
     * Verifica que el pedido guardado no sea nulo.
     * Verifica si el idCliente asignado al pedido es correcto.
     *
     * @param nuevoPedido El pedido a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crea un pedido de ejemplo
        PedidoDto nuevoPedido = new PedidoDto();
        nuevoPedido.setIdCliente(3); // Establece el idCliente del pedido

        // Llama al método "alta"
        ResponseEntity<?> responseEntity = pedidoRestController.alta(nuevoPedido);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el cuerpo de la respuesta
        Object responseBody = responseEntity.getBody();

        // Verifica que el cuerpo de la respuesta no sea nulo
        assertNotNull(responseBody, "El pedido guardado no debería ser nulo");

        // Si el cuerpo de la respuesta es una cadena (mensaje de éxito), lo muestra
        if (responseBody instanceof String) {
            System.out.println(responseBody);
        }
        // Si el cuerpo de la respuesta es una instancia de PedidoDto, verifica su idCliente
        else if (responseBody instanceof PedidoDto) {
            PedidoDto pedidoGuardado = (PedidoDto) responseBody;
            assertEquals(3, pedidoGuardado.getIdCliente(), "El idCliente del pedido guardado no coincide");
        } else {
            fail("Tipo de cuerpo de respuesta inesperado: " + responseBody.getClass().getName());
        }
    }

}
