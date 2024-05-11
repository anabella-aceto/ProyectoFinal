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
    public void testModificarClienteExistente() {
        // Crea un cliente de ejemplo
        Cliente clienteExistente = new Cliente();
        clienteExistente.setIdCliente(5); // Establece un idCliente existente
        clienteExistente.setNombre("Roberto"); // Establece el nombre del cliente
        clienteExistente.setApellidos("Valdivia"); // Establece los apellidos del cliente
        clienteExistente.setDireccion("Acera del Casino"); // Establece la dirección del cliente
        clienteExistente.setEmail("robvaldivia@hotmail.com"); // Establece el email del cliente
        clienteExistente.setTelefono("664294832"); // Establece el teléfono del cliente

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = clienteRestController.modificar(clienteExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
    }
    
    /**
     * Prueba del método "modificar" cuando se intenta modificar un cliente que no existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea NOT_FOUND.
     * Obtiene el mensaje de la respuesta.
     * Verifica que el mensaje de la respuesta indica que el cliente no existe.
     *
     * @param clienteNoExistente El cliente que se intenta modificar y no existe.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarClienteNoExistente() {
        // Crea un cliente que no existe en la base de datos
        Cliente clienteNoExistente = new Cliente();
        clienteNoExistente.setIdCliente(-1); // Establece un idCliente que no existe

        // Llama al método "modificar" con el cliente que no existe
        ResponseEntity<?> responseEntity = clienteRestController.modificar(clienteNoExistente);

        // Verifica que el código de estado de la respuesta sea NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que el mensaje de la respuesta indica que el cliente no existe
        assertTrue(mensaje.contains("El cliente con el ID " + clienteNoExistente.getIdCliente() + " no existe"), 
                   "El cliente no debería existir");
    }
}

