package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "buscarMaterialPorProveedor" en
 * MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot que
 *                 permite cargar el contexto completo de la aplicación.
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas de los métodos de búsqueda por ID de proveedor.
 *            
 */
@SpringBootTest
public class MaterialRestControllerTestVerPorIdProveedor {

	@Autowired
	MaterialRestController materialRestController;

    /**
     * Prueba del método "buscarPorRefProveedor" con un proveedor existente.
     *
     * @Test Anota este método como una prueba JUnit.
     * @param idProveedor El ID del proveedor.
     * @return void
     */
    @Test
    public void buscarPorRefProveedor_Existente() {
        // Llama al método "buscarPorRefProveedor" con un proveedor existente
        ResponseEntity<?> responseEntity = materialRestController.buscarPorRefProveedor(357);

        // Aserciones para verificar que la respuesta contiene los materiales esperados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof Material);
    }
	
    /**
     * Prueba del método "buscarPorRefProveedor" con un proveedor inexistente.
     *
     * @Test Anota este método como una prueba JUnit.
     * @param idProveedor El ID del proveedor.
     * @return void
     */
    @Test
    public void buscarPorRefProveedor_Inexistente() {
        // Llama al método "buscarPorRefProveedor" con un proveedor inexistente
        ResponseEntity<?> responseEntity = materialRestController.buscarPorRefProveedor(-1);

        // Aserciones para verificar que la respuesta contiene un error cuando el proveedor no existe
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("No se encuentra el material", responseEntity.getBody());
    }
}
