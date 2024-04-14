package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.MaterialRestController;

/**
 * Clase de prueba para MaterialRestController que verifica la funcionalidad de búsqueda de materiales por categoría.
 * 
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot que
 *                 permite cargar el contexto completo de la aplicación.
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas de los métodos de búsqueda por categoría.
 */
@SpringBootTest
public class MaterialRestControllerTestVerPorCategoria {
    
    @Autowired
    private MaterialRestController materialRestController;
    
    /**
     * Prueba la búsqueda de materiales por una categoría existente en la base de datos.
     * Verifica que la respuesta tenga un estado HTTP 200 y que el cuerpo contenga una lista de materiales.
     */
    @Test
    public void buscarMaterialPorCategoriaExistenteTest() {
        // Asumiendo que la categoría 'textil' existe en la base de datos
        String categoria = "textil";
        
        // Ejecutar la acción a probar
        ResponseEntity<?> respuesta = materialRestController.buscarMaterialPorProveedor(categoria);
       
        // Verifica el estado de la respuesta y que el cuerpo no esté vacío
        assertEquals(200, respuesta.getStatusCodeValue());
        assertFalse(((List<?>) respuesta.getBody()).isEmpty());
    }

    /**
     * Prueba la búsqueda de materiales por una categoría que no existe en la base de datos.
     * Verifica que la respuesta tenga un estado HTTP 404 y que el cuerpo contenga el mensaje de error "Categoría no encontrada".
     */
    @Test
    public void buscarMaterialPorCategoriaNoExistenteTest() {
        // Usar una categoría que se sabe que no existe en la base de datos
        String categoriaInexistente = "categoriaInexistente";
        
        // Ejecutar la acción a probar
        ResponseEntity<?> respuesta = materialRestController.buscarMaterialPorProveedor(categoriaInexistente);
        
        // Verifica el estado de la respuesta y que el mensaje de error sea el esperado
        assertEquals(404, respuesta.getStatusCodeValue());
        assertEquals("Categoría no encontrada", respuesta.getBody());
    }
}
