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
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "modificar" en ClienteRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ClienteRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class ClienteRestControllerTestModificar {

    @Autowired
    private ClienteRestController clienteRestController;

    /**
     * Prueba del método "modificar".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la modificación fue exitosa.
     *
     * @param clienteExistente El cliente con los datos a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
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

