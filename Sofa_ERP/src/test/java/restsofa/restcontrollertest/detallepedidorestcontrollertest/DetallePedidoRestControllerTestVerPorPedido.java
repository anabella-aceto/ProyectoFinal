package restsofa.restcontrollertest.detallepedidorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.DetallePedidoRestController;

/**
 * @authors Alberto Saboya, Anabella Aceto David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "verPorPedido" en DetallePediodRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DetallepedidoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class DetallePedidoRestControllerTestVerPorPedido {
	
    @Autowired
    private DetallePedidoRestController detallePedidoRestController; 
    
    /**
     * Prueba el método "filtrarPorPedido" con un ID de pedido existente.
     *
     * @param idPedido El ID del pedido existente a filtrar.
     * @return ResponseEntity con el detalle de pedido encontrado si existe, o un mensaje de error si no.
     */
    @Test
    public void testFiltrarPorPedidoExistente() {
        // Define un ID de pedido existente
        int idPedido = 3;
        // Llama al método del controlador para filtrar por pedido
        ResponseEntity<?> responseEntity = detallePedidoRestController.filtrarPorPedido(idPedido);
        // Verifica que el código de estado de la respuesta sea OK (200)
        assertEquals(200, responseEntity.getStatusCodeValue());
        // Verifica que el cuerpo de la respuesta no esté vacío
        assertTrue(responseEntity.hasBody());
    }

    /**
     * Prueba el método "filtrarPorPedido" con un ID de pedido inexistente.
     *
     * @param idPedido El ID del pedido inexistente a filtrar.
     * @return ResponseEntity con un mensaje de error indicando que el pedido no se encontró.
     */
    @Test
    public void testFiltrarPorPedidoNoExistente() {
        // Define un ID de pedido inexistente
        int idPedido = -1;
        // Llama al método del controlador para filtrar por pedido
        ResponseEntity<?> responseEntity = detallePedidoRestController.filtrarPorPedido(idPedido);
        // Verifica que el código de estado de la respuesta sea BadRequest (400)
        assertEquals(400, responseEntity.getStatusCodeValue());
        // Verifica que el cuerpo de la respuesta no esté vacío
        assertTrue(responseEntity.hasBody());
    }

}
