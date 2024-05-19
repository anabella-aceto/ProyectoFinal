package restsofa.restcontrollertest.proveedorrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.ProveedorRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrar" en ProveedorRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ProveedorRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class ProveedorRestControllerTestBorrar {
	
    @Autowired
    private ProveedorRestController proveedorRestController;
    
    /**
     * Prueba del método "borrar" cuando el proveedor existe.
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la eliminación fue correcta.
     *
     * @param proveedorId El identificador del proveedor a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    
    @Test
    public void testBorrarProveedorExiste() {
        int proveedorId = 7; // Reemplaza con un idCliente válido
        ResponseEntity<?> responseEntity = proveedorRestController.borrar(proveedorId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Proveedor eliminado"), "La eliminación debería ser correcta");
    }
    
    /**
     * Prueba del método "borrar" cuando el proveedor no existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     *       Verifica que el código de estado de la respuesta sea BAD_REQUEST (400).
     *       Verifica que el mensaje de la respuesta indique que el proveedor no existe.
     *
     * @param proveedorIdNoExistente El identificador del proveedor que se intenta borrar y no existe en el sistema.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrarProveedorNoExiste() {
        int proveedorIdNoExistente = -1; // ID que probablemente no exista

        // Llama al método "borrar"
        ResponseEntity<?> responseEntity = proveedorRestController.borrar(proveedorIdNoExistente);

        // Verifica que el código de estado de la respuesta sea BAD_REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Verifica que el mensaje de la respuesta indique que el proveedor no existe
        String mensaje = (String) responseEntity.getBody();
        assertTrue(mensaje.contains("Proveedor no eliminado"), "El proveedor no debería existir");
    }
}