package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.MaterialRestController;

/**
 * Clase de prueba para MaterialRestController.
 * Utiliza Spring Boot Test para cargar el contexto de la aplicación y realizar pruebas de integración.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot que
 *                 permite cargar el contexto completo de la aplicación.
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas de los métodos de búsqueda por nombre.
 */
@SpringBootTest
public class MaterialRestControllerTestPorRefProveedor {
    
    @Autowired
    private MaterialRestController materialRestController;
    
    /**
     * Prueba el método buscarPorRefProveedor con una referencia de proveedor existente.
     * Verifica que la respuesta tenga un estado HTTP 200 y que el cuerpo contenga el material esperado.
     */
    @Test
    public void buscarPorRefProveedorTest() {
        // Asumiendo que '456' es la referencia de un proveedor existente en la base de datos
        String refMaterialProveedor = "456";
        
        // Ejecutar la acción a probar
        ResponseEntity<?> respuesta = materialRestController.buscarPorRefProveedor(refMaterialProveedor);
       
        // Verifica el estado de la respuesta:
        assertEquals(200, respuesta.getStatusCodeValue());       
    }
    
    /**
     * Prueba el método buscarPorRefProveedor con una referencia de proveedor que no existe.
     * Verifica que la respuesta tenga un estado HTTP 500 y que el cuerpo contenga el mensaje de error adecuado.
     */
    @Test
    public void buscarPorRefProveedorNoEncontradoTest() {
        // Usar una referencia que se sabe que no existe en la base de datos
        String refMaterialProveedorInexistente = "refInexistente";
        
        // Ejecutar la acción a probar
        ResponseEntity<?> respuesta = materialRestController.buscarPorRefProveedor(refMaterialProveedorInexistente);
        
        // Verifica el estado de la respuesta:
        assertEquals(500, respuesta.getStatusCodeValue());
        
        // Verifica el cuerpo de la respuesta:
        assertEquals("No se encuentra el material", respuesta.getBody());
    }
}

