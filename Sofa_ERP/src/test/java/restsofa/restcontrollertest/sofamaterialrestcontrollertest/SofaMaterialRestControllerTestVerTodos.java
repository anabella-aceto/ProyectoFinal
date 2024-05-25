package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

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

import restsofa.modelo.entities.SofaMaterial;
import restsofa.restcontroller.SofaMaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "listarTodos" en sofaMaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaMaterialRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestVerTodos {

	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;

    /**
     * Prueba para verificar que la respuesta del método listarTodos() del controlador no es nula.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosRespuestaNoNula() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = sofaMaterialRestController.listarTodos();

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
        ResponseEntity<?> responseEntity = sofaMaterialRestController.listarTodos();

        // Verificar el código de estado de la respuesta
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    /**
     * Prueba para verificar que el cuerpo de la respuesta del método listarTodos() del controlador no es nulo.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosCuerpoRespuestaNoNulo() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = sofaMaterialRestController.listarTodos();

        // Verificar que el cuerpo de la respuesta no sea nulo
        assertNotNull(responseEntity.getBody());
    }

    /**
     * Prueba para verificar que el cuerpo de la respuesta del método listarTodos() del controlador es una lista.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosRespuestaListaSofaMateriales() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = sofaMaterialRestController.listarTodos();

        // Verificar que el cuerpo de la respuesta sea una lista de materiales de sofá
        assertTrue(responseEntity.getBody() instanceof List<?>);
    }

    /**
     * Prueba para verificar que la lista de materiales de sofá devuelta por el método listarTodos() del controlador no está vacía.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosNoVacia() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = sofaMaterialRestController.listarTodos();

        // Verificar que la lista de materiales de sofá no esté vacía
        List<?> listaSofaMateriales = (List<?>) responseEntity.getBody();
        assertFalse(listaSofaMateriales.isEmpty());
    }

    /**
     * Prueba para verificar si la lista de materiales de sofá contiene un material específico.
     *
     * @Test Anota este método como una prueba JUnit.
     */
    @Test
    public void testListarTodosContieneMaterialEspecifico() {
        // Llamar al método listarTodos() del controlador
        ResponseEntity<?> responseEntity = sofaMaterialRestController.listarTodos();

        // Verificar si la lista de materiales de sofá contiene un material específico
        List<SofaMaterial> sofaMateriales = (List<SofaMaterial>) responseEntity.getBody();
        boolean contieneMaterialEspecifico = false;
        for (SofaMaterial sofaMaterial : sofaMateriales) {
            if (sofaMaterial.getIdSofaMateriales() == 1 || sofaMaterial.getIdSofaMateriales() == 1
                    || sofaMaterial.getMaterial().getIdMaterial() == 1 || sofaMaterial.getCantidadUtilizada() == 20) {
                contieneMaterialEspecifico = true;
                break;
            }
        }
        assertTrue(contieneMaterialEspecifico, "La lista debe contener materiales de sofá específicos");
    }
}
