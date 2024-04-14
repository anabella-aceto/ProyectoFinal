package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

/**
 * Clase de prueba JUnit para el método "buscarMaterialPorProveedor" en
 * MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot que
 *                 permite cargar el contexto completo de la aplicación.
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas de los métodos de búsqueda por ID de proveedor.
 */
@SpringBootTest
public class MaterialRestControllerTestVerPorIdProveedor {

	@Autowired
	MaterialRestController materialRestController;

	/**
	 * Prueba del método "buscarMaterialPorProveedor" con el idProveedor 4.
	 *
	 * @Test Anota este método como una prueba JUnit. Verifica que la respuesta
	 *       tenga el estado HTTP correcto y que el cuerpo de la respuesta no sea
	 *       nulo. Obtiene la lista de materiales del cuerpo de la respuesta y
	 *       verifica que la lista no esté vacía. Filtra los materiales que
	 *       corresponden al proveedor con id 4 y verifica que los materiales
	 *       filtrados sean los esperados.
	 */
	@Test
	public void buscarMaterialPorProveedor_DeberiaRetornarMateriales() {
		// Llama al método "buscarMaterialPorProveedor" con el idProveedor 4
		ResponseEntity<?> responseEntity = materialRestController.buscarMaterialPorProveedor(4);

		// Aserciones para verificar que la respuesta contiene los materiales esperados
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertNotNull(responseEntity.getBody());
		assertTrue(responseEntity.getBody() instanceof List);
		List<?> listaMateriales = (List<?>) responseEntity.getBody();
		assertFalse(listaMateriales.isEmpty());

		// Aserciones específicas para los materiales del proveedor 4
		// Aquí debes asegurarte de que los materiales corresponden al proveedor 4
		// Esto es un ejemplo, debes ajustar las aserciones a los datos reales de tu
		// aplicación
		Material material = (Material) listaMateriales.get(0);
		assertEquals("Cinchas", material.getNombre());
		assertEquals(4, material.getProveedor().getIdProveedor());
	}

}
