package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * Clase de prueba JUnit para el método "eliminarMaterial" en MaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `MaterialRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class MaterialRestControllerTestBorrar {

    @Autowired
    private MaterialRestController materialRestController;

    /**
     * Prueba del método "eliminarMaterial".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la eliminación fue correcta.
     *
     * @param materialId El identificador del material a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrarMaterialExistente() {
        int materialId = 13; // Reemplaza con un idMaterial válido
        ResponseEntity<?> responseEntity = materialRestController.eliminarMaterial(materialId);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Material eliminado correctamente"), "La eliminación debería ser correcta");
    }
    
    /**
     * Prueba del método "eliminarMaterial".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea NOT_FOUND.
     * Obtiene el mensaje de la respuesta.
     * Verifica que se informe que el material no existe.
     *
     * @param materialId El identificador del material a borrar.
     * @return ResponseEntity con el resultado de la operación de borrado.
     */
    @Test
    public void testBorrarMaterialNoExistente() {
        int materialId = -1; // Reemplaza con un idMaterial que no exista
        ResponseEntity<?> responseEntity = materialRestController.eliminarMaterial(materialId);

        // Verifica que el código de estado de la respuesta sea NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que se informe que el material no existe
        assertTrue(mensaje.contains("El material no existe"), "El material no debería existir");
    }
}
