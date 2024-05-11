package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
 * Clase de prueba JUnit para el método "uno" en ClienteRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ClienteRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class ClienteRestControllerTestVerUno {

    @Autowired
    private ClienteRestController clienteRestController;

    /**
     * Prueba del método "uno".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el cliente del cuerpo de la respuesta.
     * Verifica que el cliente no sea nulo.
     * Verifica que el cliente tenga el idCliente correcto.
     * Verifica si el nombre del cliente es correcto.
     *
     * @param clientId El identificador del cliente a buscar.
     * @return ResponseEntity con el resultado de la búsqueda.
     */
    @Test
    public void testUnoClienteExistente() {
        int clientId = 3;
        ResponseEntity<?> responseEntity = clienteRestController.uno(clientId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el cliente del cuerpo de la respuesta
        Cliente cliente = (Cliente) responseEntity.getBody();

        // Verifica que el cliente no sea nulo
        assertNotNull(cliente, "El cliente no debería ser nulo");

        // Verifica que el cliente tenga el idCliente correcto
        assertEquals(clientId, cliente.getIdCliente(), "El idCliente no coincide");

        // Verifica si el nombre del cliente es correcto
        assertEquals("Carlos", cliente.getNombre(), "El nombre del cliente no coincide");
    }
    
    /**
     * Prueba del método "uno" cuando se intenta buscar un cliente que no existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea NOT_FOUND.
     * Verifica que el cuerpo de la respuesta esté vacío.
     *
     * @param clienteNoExistenteId El identificador del cliente que no existe.
     * @return ResponseEntity con el resultado de la búsqueda.
     */
    @Test
    public void testUnoClienteNoExistente() {
        int clienteNoExistenteId = -1; // idCliente que no existe
        ResponseEntity<?> responseEntity = clienteRestController.uno(clienteNoExistenteId);

        // Verifica que el código de estado de la respuesta sea NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verifica que el cuerpo de la respuesta esté vacío
        assertNull(responseEntity.getBody(), "El cuerpo de la respuesta debería estar vacío");
    }
}

