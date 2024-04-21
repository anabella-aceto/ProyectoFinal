package restsofa.restcontrollertest.proveedorrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Proveedor;
import restsofa.restcontroller.ProveedorRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "alta" en ProveedorRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ProveedorRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class ProveedorRestControllerTestAlta {
	
    @Autowired
    private ProveedorRestController proveedorRestController;
    
    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el proveedor guardado del cuerpo de la respuesta.
     * Verifica que el provedor guardado no sea nulo.
     * Verifica que el nombre del proveedor guardado coincida con el nombre esperado.
     *
     * @param nuevoProveedor El proveedor a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    
    @Test
    public void testAlta() {
        // Crea un proveedor de ejemplo
        Proveedor nuevoProveedor = new Proveedor();
        nuevoProveedor.setNombre("Integra");; // Establece el nombre del proveedor

        // Llama al método "alta"
        ResponseEntity<?> responseEntity = proveedorRestController.alta(nuevoProveedor);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el proveedor guardado del cuerpo de la respuesta
        Proveedor proveedorGuardado = (Proveedor) responseEntity.getBody();

        // Verifica que el proveedor guardado no sea nulo
        assertNotNull(proveedorGuardado, "El proveedor guardado no debería ser nulo");

        // Verifica que el nombre del proveedor guardado coincida con el nombre esperado
        assertEquals("Integra", proveedorGuardado.getNombre(), "El nombre del proveedor guardado no coincide");
    }

}
