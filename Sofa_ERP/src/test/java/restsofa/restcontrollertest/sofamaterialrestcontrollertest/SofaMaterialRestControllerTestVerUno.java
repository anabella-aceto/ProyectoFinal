package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 *          Clase de prueba JUnit para el método "buscarPorIdMaterialSofa" en
 *          SofaMaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaMaterialRestController` para realizar
 *            las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestVerUno {

	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;

	/**
	 * Prueba del método "buscarPorIdMaterialSofa" cuando el material de sofá
	 * existe.
	 *
	 * @test Este método verifica la funcionalidad de buscar un material de sofá
	 *       existente por su id. Comprueba que el código de estado de la respuesta
	 *       sea HttpStatus.OK (200). Obtiene el material de sofá del cuerpo de la
	 *       respuesta y verifica que no sea nulo. Verifica que el idMaterialSofa
	 *       del material de sofá coincida con el proporcionado.
	 * 
	 * @return ResponseEntity con el resultado de la búsqueda del material de sofá
	 *         existente.
	 * 
	 * @param idMaterialSofa El identificador único del material de sofá existente.
	 */
	@Test
	public void testBuscarPorIdMaterialSofaExistente() {
		int idMaterialSofa = 2;
		ResponseEntity<?> responseEntity = sofaMaterialRestController.buscarPorIdMaterialSofa(idMaterialSofa);

		// Verifica que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el material de sofá del cuerpo de la respuesta
		SofaMaterial sofaMaterial = (SofaMaterial) responseEntity.getBody();

		// Verifica que el material de sofá no sea nulo
		assertNotNull(sofaMaterial, "El material de sofá no debería ser nulo");

		// Verifica que el material de sofá tenga el idMaterialSofa correcto
		assertEquals(idMaterialSofa, sofaMaterial.getIdSofaMateriales(),
				"El idMaterialSofa del material de sofá no coincide");
	}

	/**
	 * Prueba del método "buscarPorIdMaterialSofa" cuando el material de sofá no
	 * existe.
	 *
	 * @test Este método verifica la funcionalidad de buscar un material de sofá
	 *       inexistente por su id. Comprueba que el código de estado de la
	 *       respuesta sea HttpStatus.BAD_REQUEST (400). Verifica el mensaje de
	 *       error en el cuerpo de la respuesta para asegurar que sea correcto.
	 * 
	 * @return ResponseEntity con el resultado de la búsqueda del material de sofá
	 *         inexistente.
	 * 
	 * @param idMaterialSofa El identificador único del material de sofá
	 *                       inexistente.
	 */
	@Test
	public void testBuscarPorIdMaterialSofaNoExistente() {
		int idMaterialSofa = -1; // Un id de material de sofá que no existe
		ResponseEntity<?> responseEntity = sofaMaterialRestController.buscarPorIdMaterialSofa(idMaterialSofa);

		// Verifica que el código de estado de la respuesta sea HttpStatus.BAD_REQUEST
		// (400)
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		// Verifica el mensaje de error en el cuerpo de la respuesta
		String mensajeError = (String) responseEntity.getBody();
		assertEquals("No se encuentra el material de sofá", mensajeError,
				"El mensaje de error no es el esperado para un material de sofá inexistente");
	}

}
