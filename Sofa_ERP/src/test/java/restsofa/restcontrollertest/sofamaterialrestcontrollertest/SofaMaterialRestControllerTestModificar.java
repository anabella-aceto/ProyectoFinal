package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.SofaMaterialDto;
import restsofa.restcontroller.SofaMaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0 * 
 *          Clase de prueba JUnit para el método "modificar" en
 *          SofaMaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaMaterialRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestModificar {
	
	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;
	
	/**
	 * Prueba del método "modificar".
	 *
	 * @Test Anota este método como una prueba JUnit.	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la modificación fue exitosa.
	 *
	 * @param sofaMaterialExistente El material de sofá con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificar() {
		// Crea un empleado de ejemplo
		SofaMaterialDto sofaMaterialExistente = new SofaMaterialDto();
		sofaMaterialExistente.setIdSofaMateriales(41);; // Establece un idSofaMaterial existente
		sofaMaterialExistente.setIdSofa(5);; // Establece el idSofa
		sofaMaterialExistente.setIdMaterial(8); // Establece el idMaterial
		sofaMaterialExistente.setCantidadUtilizada(400); // Establece la cantidad
		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = sofaMaterialRestController.modificar(sofaMaterialExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
	}

}
