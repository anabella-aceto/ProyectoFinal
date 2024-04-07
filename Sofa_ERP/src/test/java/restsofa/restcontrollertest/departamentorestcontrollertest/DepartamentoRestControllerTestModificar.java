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
 * Clase de prueba JUnit para el método "modificarDepto" en DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
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
    public void testModificar() {
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

}

