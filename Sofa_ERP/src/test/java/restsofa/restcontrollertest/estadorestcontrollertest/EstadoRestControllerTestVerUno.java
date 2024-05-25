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

/**
 * @author Alberto Saboya, Anabella Av¡ceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "uno" en `EstadoRestController`.
 * Verifica el comportamiento del método "uno" que obtiene un estado por su ID.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EstadoRestController` para realizar las pruebas.
 * 
 */

@SpringBootTest
public class EstadoRestControllerTestVerUno {

    @Autowired
    private EstadoRestController estadoRestController;

    /**
     * Caso de prueba para el método "uno".
     * Llama al método "uno" con un ID de estado específico y verifica el código de estado de la respuesta,
     * que el estado no sea nulo, que tenga el ID correcto y que su nombre sea "Finalizado".
     *
     * @param idEstadoExistente El ID de estado existe.     
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testUnoEstadoEncontrado() throws Exception {
        int idEstadoExistente = 3;
        ResponseEntity<?> responseEntity = estadoRestController.uno(idEstadoExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el estado del cuerpo de la respuesta
        Estado estado = (Estado) responseEntity.getBody();

        // Verifica que el estado no sea nulo
        assertNotNull(estado, "El estado no debería ser nulo");

        // Verifica que el estado tenga el idEstado correcto
        assertEquals(idEstadoExistente, estado.getIdEstado(), "El idEstado no coincide");

        // Verifica si el nombre del estado es correcto
        assertEquals("Finalizado", estado.getNombre(), "El nombre del estado no coincide");
    }
    
    /**
     * Caso de prueba para el método "uno" cuando el estado no se encuentra.
     * Llama al método "uno" con un ID de estado que no existe y verifica que se devuelva un código de estado 404 Not Found.
     *
     * @param idEstadoNoExistente El ID de estado que no existe.
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testUnoEstadoNoEncontrado() throws Exception {
        int idEstadoNoExistente = -1; // ID de estado que no existe
        ResponseEntity<?> responseEntity = estadoRestController.uno(idEstadoNoExistente);

        // Verifica que el código de estado de la respuesta sea Not Found (404)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}

