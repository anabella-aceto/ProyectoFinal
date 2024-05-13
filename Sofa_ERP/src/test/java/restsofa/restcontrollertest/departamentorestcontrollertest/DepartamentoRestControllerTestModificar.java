package restsofa.restcontrollertest.departamentorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Departamento;
import restsofa.restcontroller.DepartamentoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "modificarDepto" en DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class DepartamentoRestControllerTestModificar {
	
    @Autowired
    private DepartamentoRestController departamentoRestController;
    
    /**
     * Prueba del método "modificarDepto".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el mensaje de la respuesta.
     * Verifica que la modificación fue exitosa.
     *
     * @param depExistente El departamento con los datos a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarDepartamentoExiste() {
        // Crea un departamento de ejemplo
        Departamento depExistente = new Departamento();
        depExistente.setIdDepartamento(5); // Establece un idDepartamento existente
        depExistente.setNombre("administracion"); // Establece el nombre del departamento

        // Llama al método "modificarDepto"
        ResponseEntity<?> responseEntity = departamentoRestController.modificarDepto(depExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
    }
    
    /**
     * Prueba del método "modificarDepto" cuando el departamento no existe.
     * 
     * Verifica que el código de estado de la respuesta sea 404 (Not Found).
     * Obtiene el mensaje de la respuesta.
     * Verifica que el mensaje indica que el departamento no fue encontrado.
     * 
     * @param depNoExistente El departamento con los datos a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarDepartamentoNoExiste() {
        // Crea un departamento de ejemplo que no existe en la base de datos
        Departamento depNoExistente = new Departamento();
        depNoExistente.setIdDepartamento(-1); // Establece un idDepartamento que no existe
        depNoExistente.setNombre("departamentoNoExistente"); // Establece el nombre del departamento

        // Llama al método "modificarDepto" con el departamento no existente
        ResponseEntity<?> responseEntity = departamentoRestController.modificarDepto(depNoExistente);

        // Verifica que el código de estado de la respuesta sea 404 (Not Found)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que el mensaje indica que el departamento no fue encontrado
        assertTrue(mensaje.contains("Departamento no encontrado"), "El mensaje debería indicar que el departamento no existe");
    }

}

