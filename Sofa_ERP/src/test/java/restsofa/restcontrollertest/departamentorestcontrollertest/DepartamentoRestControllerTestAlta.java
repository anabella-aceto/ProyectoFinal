package restsofa.restcontrollertest.departamentorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Departamento;
import restsofa.restcontroller.DepartamentoRestController;

/**
 * Clase de prueba JUnit para el método "alta" en DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
 */
@SpringBootTest
public class DepartamentoRestControllerTestAlta {
	
    @Autowired
    private DepartamentoRestController departamentoRestController;
    
    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el departamento guardado del cuerpo de la respuesta.
     * Verifica que el departamento guardado no sea nulo.
     * Verifica que el nombre del departamento guardado coincida con el nombre esperado.
     *
     * @param nuevoDpto El departamento a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crea un departamento de ejemplo
        Departamento nuevoDpto = new Departamento();
        nuevoDpto.setNombre("contabilidad"); // Establece el nombre del departamento

        // Llama al método "alta"
        ResponseEntity<?> responseEntity = departamentoRestController.alta(nuevoDpto);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el departamento guardado del cuerpo de la respuesta
        Departamento dptoGuardado = (Departamento) responseEntity.getBody();

        // Verifica que el departamento guardado no sea nulo
        assertNotNull(dptoGuardado, "El departamento guardado no debería ser nulo");

        // Verifica que el nombre del cliente guardado coincida con el nombre esperado
        assertEquals("contabilidad", dptoGuardado.getNombre(), "El nombre del departamento guardado no coincide");
    }

}

