package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.modelo.entities.Sofa;
import restsofa.restcontroller.SofaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "modificar" en
 *          SofaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestModificar {

	@Autowired
	private SofaRestController sofaRestController;

	/**
	 * Prueba del método "modificar".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la modificación fue exitosa.
	 *
	 * @param sofaExistente El cliente con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificar() {
		// Crea un sofa de ejemplo
		Sofa sofaExistente = new Sofa();
		sofaExistente.setIdSofa(6);
		; // Establece un idSofa existente
		sofaExistente.setNombre("Alhambra"); // Establece el nombre del sofa

		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = sofaRestController.modificar(sofaExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación de sofá realizada correctamente"),
				"La modificación debería ser correcta");
	}

}
