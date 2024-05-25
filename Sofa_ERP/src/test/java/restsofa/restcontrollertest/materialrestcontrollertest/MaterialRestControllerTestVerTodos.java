package restsofa.restcontrollertest.materialrestcontrollertest;

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

import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "listarTodos" en
 *          MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class MaterialRestControllerTestVerTodos {

	@Autowired
	MaterialRestController materialRestController;

	/**
	 * Prueba para verificar que la respuesta del método listarTodos() del
	 * controlador no es nula.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarTodosRespuestaNoNula() {
		// Llamar al método listarTodos() del controlador
		ResponseEntity<?> responseEntity = materialRestController.listarTodos();

		// Verificar que la respuesta no sea nula
		assertNotNull(responseEntity);
	}

	/**
	 * Prueba para verificar el código de estado de la respuesta del método
	 * listarTodos() del controlador.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarTodosCodigoEstado() {
		// Llamar al método listarTodos() del controlador
		ResponseEntity<?> responseEntity = materialRestController.listarTodos();

		// Verificar el código de estado de la respuesta
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método listarTodos()
	 * del controlador no es nulo.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarTodosCuerpoRespuestaNoNulo() {
		// Llamar al método listarTodos() del controlador
		ResponseEntity<?> responseEntity = materialRestController.listarTodos();

		// Verificar que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método listarTodos()
	 * del controlador es una lista.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarTodosRespuestaListaMateriales() {
		// Llamar al método listarTodos() del controlador
		ResponseEntity<?> responseEntity = materialRestController.listarTodos();

		// Verificar que el cuerpo de la respuesta sea una lista de materiales
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de materiales devuelta por el método
	 * listarTodos() del controlador no está vacía.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarTodosNoVacia() {
		// Llamar al método listarTodos() del controlador
		ResponseEntity<?> responseEntity = materialRestController.listarTodos();

		// Verificar que la lista de materiales no esté vacía
		List<?> listaMateriales = (List<?>) responseEntity.getBody();
		assertFalse(listaMateriales.isEmpty(), "La lista de materiales no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de materiales contiene un material
	 * específico.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarTodosContieneMaterialEspecifico() {
		// Llamar al método listarTodos() del controlador
		ResponseEntity<?> responseEntity = materialRestController.listarTodos();

		// Verificar que la lista de materiales contenga un material específico
		List<Material> materiales = (List<Material>) responseEntity.getBody();
		boolean contieneMaterialEspecifico = false;
		for (Material material : materiales) {
			if (material.getIdMaterial() == 4 || "Tela".equals(material.getNombre())) {
				contieneMaterialEspecifico = true;
				break;
			}
		}
		assertTrue(contieneMaterialEspecifico, "La lista debe contener materiales específicos");
	}
}
