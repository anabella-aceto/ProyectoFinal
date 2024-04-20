package restsofa.restcontrollertest.departamentorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Departamento;
import restsofa.restcontroller.DepartamentoRestController;

/**
 * Clase de prueba JUnit para el método "listarTodos" en DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
 * 
 * @author Alberto Saboya
 * @version 1.0
 */
@SpringBootTest
public class DepartamentoRestControllerTestVerTodos {

    @Autowired
    private DepartamentoRestController departamentoRestController;

    /**
     * Prueba del método "listarTodos".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene la lista de departamentos del cuerpo de la respuesta.
     * Verifica que la lista no esté vacía.
     * Verifica si contiene departamentos específicos.
     *
     * @return ResponseEntity con la lista de departamentos.
     */
    @Test
    public void testTodos() {
        // Llama al método "listarTodos"
        ResponseEntity<?> responseEntity = departamentoRestController.listarTodos();

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene la lista de departamentos del cuerpo de la respuesta
        List<Departamento> departamentos = (List<Departamento>) responseEntity.getBody();

        // Verifica que la lista no esté vacía
        assertFalse(departamentos.isEmpty(), "La lista de departamentos no debería estar vacía");

        // Verifica si contiene departamentos específicos
        boolean contieneDptoEspecifico = false;
        for (Departamento departamento : departamentos) {
            if (departamento.getIdDepartamento() == 4 || departamento.getNombre().equals("enfundado")) {
                contieneDptoEspecifico = true;
                break;
            }
        }
        assertTrue(contieneDptoEspecifico, "La lista debe contener departamentos específicos");
    }
}

