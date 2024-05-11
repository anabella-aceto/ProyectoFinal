package restsofa.restcontrollertest.detallepedidorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.DetallePedidoRestController;

/**
 * Prueba del método "borrarUno".
 *
 * @Test
 * Anota este método como una prueba JUnit.
 *
 * Verifica que el código de estado de la respuesta sea OK.
 * Obtiene el mensaje de la respuesta.
 * Verifica que la eliminación fue correcta.
 *
 * @param empId El identificador del empleado a borrar.
 * @return ResponseEntity con el resultado de la operación de borrado.
 */
@SpringBootTest
public class DetallePedidoRestControllerTestBorrar {

    @Autowired
    private DetallePedidoRestController detallePedidoRestController;

    /**
     * Prueba del método "borrar".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la eliminación fue correcta.
     *
     * @param idDetalle El identificador del detalle de pedido a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrarExistente() {
        int idDetalle = 5; // Reemplaza con un idDetalle válido
        ResponseEntity<?> responseEntity = detallePedidoRestController.borrar(idDetalle);
       
        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Detalle de pedido eliminado correctamente"), "La eliminación debería ser correcta");        
        
    }

    /**
     * Prueba del método "borrar" para un detalle de pedido inexistente.
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que la respuesta del controlador sea un código de estado HTTP 400 (Bad Request).
     * Verifica que el mensaje de la respuesta sea "Detalle de pedido no se ha podido eliminar".
     *
     * @param idDetalle El identificador del detalle de pedido a intentar borrar.
     */
    @Test
    public void testBorrarNoExistente() {
        // Define un ID de detalle inexistente para simular el intento de eliminación
        int idDetalle = 99;
        
        // Llama al método de borrado del controlador de DetallePedidoRestController
        ResponseEntity<?> responseEntity = detallePedidoRestController.borrar(idDetalle);
        
        // Verifica que la respuesta del controlador sea un código de estado HTTP 400 (Bad Request)
        assertEquals(400, responseEntity.getStatusCodeValue());
        
        // Verifica que el mensaje de la respuesta sea "Detalle de pedido no se ha podido eliminar"
        assertEquals("Detalle de pedido no se ha podido eliminar", responseEntity.getBody());
    }

}
