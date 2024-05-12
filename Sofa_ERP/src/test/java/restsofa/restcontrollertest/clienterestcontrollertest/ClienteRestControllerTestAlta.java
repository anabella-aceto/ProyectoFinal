package restsofa.restcontrollertest.clienterestcontrollertest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * Clase de prueba JUnit para el método "alta" en ClienteRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ClienteRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class ClienteRestControllerTestAlta {

    @Autowired
    private ClienteRestController clienteRestController;

    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el cliente guardado del cuerpo de la respuesta.
     * Verifica que el cliente guardado no sea nulo.
     * Verifica que el nombre del cliente guardado coincida con el nombre esperado.
     *
     * @param nuevoCliente El cliente a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crea un cliente de ejemplo
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Elisa"); // Establece el nombre del cliente
        nuevoCliente.setApellidos("Salamanca"); // Establece el apellido del cliente
        nuevoCliente.setDireccion("Calle Rosales"); // Establece la dirección del cliente
        nuevoCliente.setEmail("elisasalamanca@gmail.com");  // Establece el email del cliente
        nuevoCliente.setTelefono("956351487"); // Establece el teléfono del cliente
        
        // Llama al método "alta"
        ResponseEntity<?> responseEntity = clienteRestController.alta(nuevoCliente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el cliente guardado del cuerpo de la respuesta
        Cliente clienteGuardado = (Cliente) responseEntity.getBody();

        // Verifica que el cliente guardado no sea nulo
        assertNotNull(clienteGuardado, "El cliente guardado no debería ser nulo");

        // Verifica que el nombre del cliente guardado coincida con el nombre esperado
        assertEquals("Elisa", clienteGuardado.getNombre(), "El nombre del cliente guardado no coincide");
    }    

}

