package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.restcontroller.ClienteRestController;

/**
 * Clase de prueba JUnit para el método "modificar" en ClienteRestController.
 */
@SpringBootTest
public class ClienteRestControllerTestModificar {

    @Autowired
    private ClienteRestController clienteRestController;

    /**
     * Prueba del método "modificar".
     */
    @Test
    public void testModificar() {
        // Crea un cliente de ejemplo
        Cliente clienteExistente = new Cliente();
        clienteExistente.setIdCliente(6); // Establece un idCliente existente
        clienteExistente.setNombre("Roberto"); // Establece el nombre del cliente

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = clienteRestController.modificar(clienteExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
    }
}
