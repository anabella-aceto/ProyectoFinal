package restsofa.restcontrollertest.pedidosrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Pedido;
import restsofa.restcontroller.PedidoRestController;

/**
 * 
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Prueba unitaria para el controlador PedidoRestController.
 * Verifica el método filtrarPorFecha.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `PedidoRestController` para realizar las pruebas.
 *  
 */
@SpringBootTest
public class PedidoRestControllerTestVerPorFecha {
	
	@Autowired
	private PedidoRestController pedidoRestController;
	
    /**
     * Prueba para el método filtrarPorFecha.
     * Verifica que el código de estado de la respuesta sea OK,
     * que el cuerpo de la respuesta no esté vacío y contenga una lista de objetos Pedido,
     * y que la lista no esté vacía.
     *
     * @param fechaInicio Fecha de inicio para el intervalo de búsqueda
     * @param fechaFin    Fecha de fin para el intervalo de búsqueda
     * @return ResponseEntity con la lista de pedidos filtrados o un mensaje de error
     */
    @Test
    public void testFiltrarPorFecha() {
        // Establece fechas de inicio y fin válidas (reemplaza con fechas válidas reales)
        Date fechaInicio = new Date(); 
        Date fechaFin = new Date(); 

        // Llama al método del controlador
        ResponseEntity<?> responseEntity = pedidoRestController.filtrarPorFecha(fechaInicio, fechaFin);

        // Verifica el código de estado de la respuesta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "El código de estado debería ser 200 OK");

        // Verifica que el cuerpo de la respuesta no esté vacío
        assertNotNull(responseEntity.getBody(), "El cuerpo de la respuesta no debería ser nulo");

        // Verifica que el cuerpo de la respuesta contenga una lista de objetos Pedido
        assertTrue(responseEntity.getBody() instanceof List, "El cuerpo de la respuesta debe ser una lista");

        // Verifica que la lista no esté vacía
        List<Pedido> pedidoList = (List<Pedido>) responseEntity.getBody();
        assertFalse(pedidoList.isEmpty(), "La lista de pedidos no debería estar vacía");
    }

}
