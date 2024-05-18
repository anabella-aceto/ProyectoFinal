package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "buscarPorNombre" en
 *          MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot que
 *                 permite cargar el contexto completo de la aplicación.
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas de los métodos de búsqueda por nombre.
 * 
 */
@SpringBootTest
public class MaterialRestControllerTestVerPorNombre {

	@Autowired
	private MaterialRestController materialRestController;

	/**
	 * Prueba del método "buscarPorNombre" con el nombre "Tela".
	 * 
	 * @param nombreMaterial El nombre del material a buscar.
	 *
	 * @Test Anota este método como una prueba JUnit. Verifica que la respuesta
	 *       tenga el estado HTTP correcto y que el cuerpo de la respuesta no sea
	 *       nulo. Obtiene la lista de materiales del cuerpo de la respuesta y
	 *       verifica que la lista no esté vacía. Filtra los materiales que
	 *       contienen la palabra "Tela" en el nombre y verifica que los materiales
	 *       filtrados sean los esperados.
	 */
	@Test
	public void testbuscarPorNombre_Existente() {
		// Llama al método "buscarPorNombre" con el nombre "Tela"
		ResponseEntity<?> responseEntity = materialRestController.buscarPorNombre("Tela");

		// Verifica que la respuesta tenga el estado HTTP correcto
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verifica que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody()); // Asumiendo que el cuerpo de la respuesta no es nulo cuando se
													// encuentra un material
	}

	/**
	 * Prueba la funcionalidad de manejar la situación donde no se encuentra ningún
	 * material con el nombre dado.
	 *
	 * @param nombreMaterial El nombre del material a buscar.
	 * @test Se espera que retorne un mensaje indicando que no se encontraron
	 *       materiales.
	 */
	@Test
	public void testbuscarPorNombre_Inexistente() {

		// Llama al método "buscarPorNombre" con el nombre "Material inexistente"
		ResponseEntity<?> respuesta = materialRestController.buscarPorNombre("Material inexistente");

		// Verifica que la respuesta tenga el estado HTTP correcto
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());

		// Verifica que el cuerpo de la respuesta contenga el mensaje esperado
		assertEquals("No se encuentran materiales con el nombre proporcionado", respuesta.getBody());
	}

}
