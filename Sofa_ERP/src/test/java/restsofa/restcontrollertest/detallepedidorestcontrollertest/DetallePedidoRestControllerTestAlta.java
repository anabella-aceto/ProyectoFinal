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

@SpringBootTest
public class DetallePedidoRestControllerTestAlta {
    
    @Autowired
    private DetallePedidoRestController detallePedidoRestController;  

    @Test
    public void testAlta() {  	
        
        // Crea un detalle de pedido de ejemplo
        DetallePedidoDto nuevoDetalle = new DetallePedidoDto();
        nuevoDetalle.setIdPedido(4); // Establece el pedido del detalle de pedido
        nuevoDetalle.setIdSofa(1); // Establece el sofa del detalle de pedido
        nuevoDetalle.setIdEstado(1); // Establece el estado del detalle de pedido
        nuevoDetalle.setCantidad(1); // Establece la cantidad del detalle de pedido
        nuevoDetalle.setDensCojin(20); // Establece la densidad del cojín del detalle de pedido
        nuevoDetalle.setFecha(new Date()); // Establece la fecha del detalle de pedido
        nuevoDetalle.setPlazas(3); // Establece el número de plazas de detalle e pedido
        nuevoDetalle.setPrecio(1500); // Establece el precio del detalle de pedido
        
        // Llama al método "alta"
        ResponseEntity<?> responseEntity = detallePedidoRestController.alta(nuevoDetalle);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifica que el mensaje de la respuesta indique que el detalle se procesó correctamente
        String mensajeRespuesta = (String) responseEntity.getBody();
        System.out.println("Mensaje de respuesta: " + mensajeRespuesta); // Depuración
        assertNotNull(mensajeRespuesta, "El mensaje de respuesta no debería ser nulo");
        assertTrue(mensajeRespuesta.contains("Detalle de pedido procesado correctamente"), "El detalle de pedido no se procesó correctamente");
    }
}



