package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
 * @author Alberto Saboya
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
	 * Prueba del método "listarTodos".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea OK.
	 * Obtiene la lista de materiales de sofá del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Verifica si contiene materiales de sofá específicos.
	 *
	 * @return ResponseEntity con la lista de materiales de sofá.
	 */
	@Test
	public void testTodos() {
        // Obtiene la respuesta del controlador para listar todos los materiales
        ResponseEntity<?> responseEntity = sofaMaterialRestController.listarTodos();

        // Verifica que la respuesta sea un estado HTTP 200 (OK)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene la lista de materiales de sofás del cuerpo de la respuesta
        List<SofaMaterial> sofaMateriales = (List<SofaMaterial>) responseEntity.getBody();

        // Verifica que la lista no esté vacía
        assertFalse(sofaMateriales.isEmpty(), "La lista de materiales del sofá no debería estar vacía");

        // Verifica si la lista contiene un material específico de sofá
        boolean containsSpecificSofaMaterial = false;
        for (SofaMaterial sofaMaterial : sofaMateriales) {
            if (sofaMaterial.getIdSofaMateriales() == 1 || sofaMaterial.getIdSofaMateriales() == 1
                    || sofaMaterial.getMaterial().getIdMaterial() == 1 || sofaMaterial.getCantidadUtilizada() == 20) {
                containsSpecificSofaMaterial = true;
                break;
            }
        }
        assertTrue(containsSpecificSofaMaterial, "La lista debe contener materiales de sofás");
    }

}
