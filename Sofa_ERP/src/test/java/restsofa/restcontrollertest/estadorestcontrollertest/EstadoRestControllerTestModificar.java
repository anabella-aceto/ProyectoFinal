package restsofa.restcontrollertest.estadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * Clase de prueba JUnit para el método "modificar" en EstadoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EstadoRestController` para realizar las
 *            pruebas.
 *           
 */
@SpringBootTest
public class EstadoRestControllerTestModificar {

    @Autowired
    private EstadoRestController estadoRestController;

    /**
     * Prueba del método "modificar" cuando el estado a modificar existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     *       Verifica que el código de estado de la respuesta sea OK.
     *       Obtiene el mensaje del cuerpo de la respuesta.
     *
     * @param estadoExistente El estado existente a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarEstadoExistente() {
        // Crea un estado de ejemplo existente
        Estado estadoExistente = new Estado();
        estadoExistente.setIdEstado(5); // Establece un idEstado existente
        estadoExistente.setNombre("Exito"); // Establece el nombre del estado

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = estadoRestController.modificar(estadoExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Estado de pedido modificado correctamente"),
                "La modificación debería ser correcta");
    }

    /**
     * Prueba del método "modificar" cuando el estado a modificar no existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     *       Verifica que el código de estado de la respuesta sea NOT_FOUND.
     *
     * @param estadoNoExistente El estado que no existe y se intenta modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarEstadoNoExistente() {
        // Crea un estado de ejemplo que no existe
        Estado estadoNoExistente = new Estado();
        estadoNoExistente.setIdEstado(-1); // Establece un idEstado que no existe
        estadoNoExistente.setNombre("Inexistente"); // Establece un nombre para el estado inexistente

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = estadoRestController.modificar(estadoNoExistente);

        // Verifica que el código de estado de la respuesta sea NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
