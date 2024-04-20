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
import restsofa.service.ProveedorService;

@SpringBootTest
public class ProveedorRestControllerTestVerTodos {
	
	@Autowired
	private ProveedorRestController proveedorRestController;	
	
	/*
    @Test
    public void testTodos() {
        // Llama al método "todos"
        ResponseEntity<?> responseEntity = proveedorRestController.

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene la lista de proveedores del cuerpo de la respuesta
        List<Proveedor> proveedores = (List<Proveedor>) responseEntity.getBody();

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
    */

}
