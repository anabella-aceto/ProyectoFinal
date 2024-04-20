package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.restcontroller.ClienteRestController;

/**
 * Clase de prueba JUnit para el método "todos" en ClienteRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ClienteRestController` para realizar las pruebas.
 * 
 * @author Alberto Saboya
 * @version 1.0
 */
@SpringBootTest
public class ClienteRestControllerTestVerTodos {

    @Autowired
    private ClienteRestController clienteRestController;

    /**
     * Prueba del método "todos".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene la lista de clientes del cuerpo de la respuesta.
     * Verifica que la lista no esté vacía.
     * Verifica si contiene clientes específicos.
     *
     * @return ResponseEntity con la lista de clientes.
     */
    @Test
    public void testTodos() {
        // Llama al método "todos"
        ResponseEntity<?> responseEntity = clienteRestController.todos();

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene la lista de clientes del cuerpo de la respuesta
        List<Cliente> clientes = (List<Cliente>) responseEntity.getBody();

        // Verifica que la lista no esté vacía
        assertFalse(clientes.isEmpty(), "La lista de clientes no debería estar vacía");

        // Verifica si contiene clientes específicos
        boolean contieneClienteEspecifico = false;
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == 4 || cliente.getNombre().equals("Laura")) {
                contieneClienteEspecifico = true;
                break;
            }
        }
        assertTrue(contieneClienteEspecifico, "La lista debe contener clientes específicos");
    }
}

