package restsofa.restcontrollertest.estadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.EstadoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrar" en EstadoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EstadoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class EstadoRestControllerTestBorrar {
	
    @Autowired
    private EstadoRestController estadoRestController;
    
    /**
     * Prueba del método "borrar".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje del cuerpo de la respuesta.
     * Verifica que el mensaje confirme que la eliminación fue exitosa.
     *
     * @param estadoId El ID del estado a eliminar.
     * @return ResponseEntity con el resultado de la operación de eliminación.
     */
    @Test
    public void testBorrar() {
        int estadoId = 5; // Reemplaza con un idEstado válido
        ResponseEntity<?> responseEntity = estadoRestController.borrar(estadoId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        String mensajeEsperado = "Estado de pedido eliminado correctamente";
        assertTrue(mensaje != null && mensaje.contains(mensajeEsperado), "La eliminación debería ser correcta");
    }
}
