package restsofa.restcontrollertest.proveedorrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
 * Clase de prueba JUnit para el método "mostrartodos" en ProveedorRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `ProveedorRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class ProveedorRestControllerTestVerTodos {
	
    @Autowired
    private ProveedorRestController proveedorRestController;
    
    /**
     * Prueba del método "mostrartodos".
     *
     * @Test anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene la lista de proveedores del cuerpo de la respuesta.
     * Verifica que la lista no esté vacía.
     * Verifica si contiene proveedores específicos.
     * 
     * @return ResponseEntity con la lista de proveedores.
     */

    @Test
    public void testTodos() {
        // Llama al método "mostrartodos"
        ResponseEntity<List<Proveedor>> responseEntity = proveedorRestController.mostrartodos();

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene la lista de proveedores del cuerpo de la respuesta
        List<Proveedor> proveedores = responseEntity.getBody();

        // Verifica que la lista no esté vacía
        assertFalse(proveedores.isEmpty(), "La lista de proveedores no debería estar vacía");

        // Verifica si contiene proveedores específicos
        boolean contieneProveedorEspecifico = false;
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdProveedor() == 3 || proveedor.getNombre().equals("Innova")) {
                contieneProveedorEspecifico = true;
                break;
            }
        }
        assertTrue(contieneProveedorEspecifico, "La lista debe contener proveedores específicos");
    }    

}
