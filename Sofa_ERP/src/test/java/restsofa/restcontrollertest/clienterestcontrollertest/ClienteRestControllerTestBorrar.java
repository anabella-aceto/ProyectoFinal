package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.ClienteRestController;

/**
 * Clase de prueba JUnit para el método "borrar" en ClienteRestController.
 */
@SpringBootTest
public class ClienteRestControllerTestBorrar {

    @Autowired
    private ClienteRestController clienteRestController;

    /**
     * Prueba del método "borrar".
     */
    @Test
    public void testBorrar() {
        int clientId = 6; // Reemplaza con un idCliente
        ResponseEntity<?> responseEntity = clienteRestController.borrar(clientId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Eliminación realizada correctamente"), "La eliminación debería ser correcta");
    }
}
