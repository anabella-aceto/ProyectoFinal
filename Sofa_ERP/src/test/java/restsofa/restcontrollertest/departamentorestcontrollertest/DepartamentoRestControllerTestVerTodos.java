package restsofa.restcontrollertest.departamentorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "listarTodos" en DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class DepartamentoRestControllerTestVerTodos {

    @Autowired
    private DepartamentoRestController departamentoRestController;

    /**
     * Prueba para verificar que la respuesta del método listarTodos() del controlador no es nula.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosRespuestaNoNula() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = departamentoRestController.listarTodos();

        // Verificar que la respuesta no sea nula
        assertNotNull(responseEntity);
    }

    /**
     * Prueba para verificar el código de estado de la respuesta del método listarTodos() del controlador.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosCodigoEstado() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = departamentoRestController.listarTodos();

        // Verificar el código de estado de la respuesta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * Prueba para verificar que el cuerpo de la respuesta del método listarTodos() del controlador no es nulo.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosCuerpoRespuestaNoNulo() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = departamentoRestController.listarTodos();

        // Verificar que el cuerpo de la respuesta no sea nulo
        assertNotNull(responseEntity.getBody());
    }

    /**
     * Prueba para verificar que el cuerpo de la respuesta del método listarTodos() del controlador es una lista.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosRespuestaListaDepartamentos() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = departamentoRestController.listarTodos();

        // Verificar que el cuerpo de la respuesta sea una lista de departamentos
        assertTrue(responseEntity.getBody() instanceof List<?>);
    }

    /**
     * Prueba para verificar que la lista de departamentos devuelta por el método listarTodos() del controlador no está vacía.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosListaNoVacia() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = departamentoRestController.listarTodos();

        // Verificar que la lista de departamentos no esté vacía
        List<?> departamentos = (List<?>) responseEntity.getBody();
        assertFalse(departamentos.isEmpty(), "La lista de departamentos no debería estar vacía");
    }

    /**
     * Prueba para verificar si la lista de departamentos contiene un departamento específico.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosContieneDepartamentoEspecifico() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = departamentoRestController.listarTodos();

        // Verificar que la lista de departamentos contenga un departamento específico
        List<Departamento> departamentos = (List<Departamento>) responseEntity.getBody();
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

