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

import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

/**
 * Clase de prueba JUnit para el método "listarTodos" en MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas.
 */
@SpringBootTest
public class MaterialRestControllerTestVerTodos {

	@Autowired
	MaterialRestController materialRestController;

	/**
	 * Prueba del método "listarTodos".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene la
	 *       lista de materiales del cuerpo de la respuesta. Verifica que la lista
	 *       no esté vacía. Verifica si la lista contiene materiales específicos.
	 *
	 * @return ResponseEntity con la lista de materiales.
	 */
	@Test
	public void testTodos() {
		// Llama al método "listarTodos"
		ResponseEntity<?> responseEntity = materialRestController.listarTodos();

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene la lista de materiales del cuerpo de la respuesta
		List<Material> materiales = (List<Material>) responseEntity.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(materiales.isEmpty(), "La lista de materiales no debería estar vacía");

		// Verifica si contiene materiales específicos
		boolean contieneMaterialEspecifico = false;
		for (Material material : materiales) {
			if (material.getIdMaterial() == 4 || material.getNombre().equals("Tela")) {
				contieneMaterialEspecifico = true;
				break;
			}
		}
		assertTrue(contieneMaterialEspecifico, "La lista debe contener materiales específicos");
	}
}
