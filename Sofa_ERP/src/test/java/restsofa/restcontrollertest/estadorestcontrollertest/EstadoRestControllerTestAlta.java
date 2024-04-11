package restsofa.restcontrollertest.estadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Estado;
import restsofa.restcontroller.EstadoRestController;

@SpringBootTest
public class EstadoRestControllerTestAlta {
	
    @Autowired
    private EstadoRestController estadoRestController;
    
    @Test
    public void testAlta() {
        // Crea un objeto Estado de ejemplo
        Estado nuevoEstado = new Estado();
        nuevoEstado.setNombre("Nuevo Estado");

        // Llama al método "alta"
        ResponseEntity<?> responseEntity = estadoRestController.alta(nuevoEstado);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifica el mensaje de respuesta
        String responseMessage = (String) responseEntity.getBody();
        String expectedMessage = "Estado de pedido procesado correctamente" + nuevoEstado.getNombre();
        assertEquals(expectedMessage, responseMessage, "Error al procesar el estado de pedido");
    }
}
