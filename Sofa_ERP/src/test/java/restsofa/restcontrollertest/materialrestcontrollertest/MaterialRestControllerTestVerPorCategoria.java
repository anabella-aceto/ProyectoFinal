package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba para MaterialRestController que verifica la funcionalidad de búsqueda de materiales por categoría.
 * 
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot que
 *                 permite cargar el contexto completo de la aplicación.
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas de los métodos de búsqueda por categoría.
 *           
 */
@SpringBootTest
public class MaterialRestControllerTestVerPorCategoria {
    
    @Autowired
    private MaterialRestController materialRestController;
    
    /**
     * Prueba la búsqueda de materiales por una categoría existente en la base de datos.
     *
     * @test Verifica que la respuesta tenga un estado HTTP 200 y que el cuerpo contenga una lista de materiales.
     * 
     * @param categoria La categoría existente en la base de datos.
     */
    @Test
    public void buscarMaterialPorCategoriaExistenteTest() {
        // Asumiendo que la categoría 'textil' existe en la base de datos
        String categoria = "textil";
        
        // Ejecutar la acción a probar
        ResponseEntity<?> respuesta = materialRestController.buscarMaterialPorCategoria(categoria);
       
        // Verifica el estado de la respuesta y que el cuerpo no esté vacío
        assertEquals(HttpStatus.OK.value(), respuesta.getStatusCodeValue());
        assertTrue(respuesta.getBody() instanceof List);
        assertFalse(((List<?>) respuesta.getBody()).isEmpty());
    }

    /**
     * Prueba la búsqueda de materiales por una categoría que no existe en la base de datos.
     *
     * @test Verifica que la respuesta tenga un estado HTTP 404 y que el cuerpo contenga el mensaje de error adecuado.
     * 
     * @param categoriaInexistente La categoría que no existe en la base de datos.
     * 
     * @return El resultado de la prueba.
     */
    @Test
    public void buscarMaterialPorCategoriaNoExistenteTest() {
        // Usar una categoría que se sabe que no existe en la base de datos
        String categoriaInexistente = "categoriaInexistente";
        
        // Ejecutar la acción a probar
        ResponseEntity<?> respuesta = materialRestController.buscarMaterialPorCategoria(categoriaInexistente);
        
        // Verifica el estado de la respuesta y que el mensaje de error sea el esperado
        assertEquals(HttpStatus.NOT_FOUND.value(), respuesta.getStatusCodeValue());
        assertEquals("No se encontraron materiales para la categoría proporcionada", respuesta.getBody());
    }
}
