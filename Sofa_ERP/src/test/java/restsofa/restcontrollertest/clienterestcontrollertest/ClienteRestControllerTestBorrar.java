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
 * @author Alberto Saboya, Anabella Aceto David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrar" en ClienteRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ClienteRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class ClienteRestControllerTestBorrar {

    @Autowired
    private ClienteRestController clienteRestController;

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
     * @param clientId El identificador del cliente a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrarClienteExistente() {
        int clientId = 5; // Reemplaza con un idCliente válido
        ResponseEntity<?> responseEntity = clienteRestController.borrar(clientId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Eliminación realizada correctamente"), "La eliminación debería ser correcta");
    }
    
    /**
     * Prueba del método "borrar" cuando se intenta borrar un cliente que no existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea NOT_FOUND.
     * Obtiene el mensaje de la respuesta.
     * Verifica que el mensaje de la respuesta indica que el cliente no existe.
     * 
     * @param clientId El identificador del cliente a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrarClienteNoExistente() {
        int clientId = -1; // idCliente que no existe
        ResponseEntity<?> responseEntity = clienteRestController.borrar(clientId);

        // Verifica que el código de estado de la respuesta sea NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que el mensaje de la respuesta indica que el cliente no existe
        assertTrue(mensaje.contains("El cliente con el ID " + clientId + " no existe"), "El cliente no debería existir");
    }
}

