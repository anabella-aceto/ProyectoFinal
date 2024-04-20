package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

/**
 * Clase de prueba JUnit para el método "buscarPorNombre" en
 * MaterialRestController.
 *
 * @SpringBootTest 
 * Indica que esta clase es una prueba de Spring Boot que
 * permite cargar el contexto completo de la aplicación.
 * @Autowired 
 * Inyecta la instancia de `MaterialRestController` para realizar las
 * pruebas de los métodos de búsqueda por nombre.
 * 
 * @author Alberto Saboya
 * @version 1.0
 */
@SpringBootTest
public class MaterialRestControllerTestVerPorNombre {

	@Autowired
	private MaterialRestController materialRestController;

	/**
	 * Prueba del método "buscarPorNombre" con el nombre "Tela".
	 *
	 * @Test Anota este método como una prueba JUnit. Verifica que la respuesta
	 *       tenga el estado HTTP correcto y que el cuerpo de la respuesta no sea
	 *       nulo. Obtiene la lista de materiales del cuerpo de la respuesta y
	 *       verifica que la lista no esté vacía. Filtra los materiales que
	 *       contienen la palabra "Tela" en el nombre y verifica que los materiales
	 *       filtrados sean los esperados.
	 */
	@Test
	public void buscarPorNombreTest() {
		// Llama al método "buscarPorNombre" con el nombre "Tela"
		ResponseEntity<?> responseEntity = materialRestController.buscarPorNombre("Tela");

		// Verifica que la respuesta tenga el estado HTTP correcto
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verifica que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());

		// Obtiene la lista de materiales del cuerpo de la respuesta
		@SuppressWarnings("unchecked")
		List<Material> materiales = (List<Material>) responseEntity.getBody();

		// Verifica que la lista de materiales no esté vacía
		assertFalse(materiales.isEmpty());

		// Filtra los materiales que contienen la palabra "Tela" en el nombre
		List<Material> materialesFiltrados = materiales.stream()
				.filter(material -> material.getNombre().contains("Tela")).collect(Collectors.toList());

		// Verifica que los materiales filtrados sean los esperados
		assertFalse(materialesFiltrados.isEmpty());
		assertTrue(materialesFiltrados.stream().anyMatch(material -> material.getDescripcion().equals("Rivera beige")));
		assertTrue(materialesFiltrados.stream().anyMatch(material -> material.getDescripcion().equals("Rivera negro")));
	}
}
