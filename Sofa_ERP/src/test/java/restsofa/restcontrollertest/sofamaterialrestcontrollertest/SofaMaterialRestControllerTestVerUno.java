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
 * Clase de prueba JUnit para el método "buscarPorIdMaterialSofa" en SofaMaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaMaterialRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestVerUno {
	
	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;
	
	/**
	 * Prueba del método "buscarPorIdMaterialSofa".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene el empleado del cuerpo de la respuesta.
	 * Verifica que el material de sofá no sea nulo.
	 * Verifica que el material de sofá tenga el idMaterialSofa correcto.
	 * Verifica si el idSofa es correcto.
	 *
	 * @param idSofaMaterial El identificador del material de sofá a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void testbuscarPorId() {
		int idSofaMaterial = 2;
		ResponseEntity<?> responseEntity = sofaMaterialRestController.buscarPorIdMaterialSofa(idSofaMaterial);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el empleado del cuerpo de la respuesta
		SofaMaterial sofaMaterial = (SofaMaterial) responseEntity.getBody();

		// Verifica que el material de sofá no sea nulo
		assertNotNull(sofaMaterial, "El material de sofá no debería ser nulo");

		// Verifica que el material de sofá tenga el idSofaMaterial correcto
		assertEquals(idSofaMaterial, sofaMaterial.getIdSofaMateriales(), "No se encuentra el material de sofá");

		// Verifica si el idSofa es correcto
		assertEquals(1, sofaMaterial.getSofa().getIdSofa(), "El idSofa no coincide");
	}

}
