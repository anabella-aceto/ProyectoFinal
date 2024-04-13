package restsofa.restcontrollertest.estadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Estado;
import restsofa.restcontroller.EstadoRestController;

/**
 * Clase de prueba JUnit para el método "modificar" en EstadoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EstadoRestController` para realizar las
 *            pruebas.
 */
@SpringBootTest
public class EstadoRestControllerTestModificar {

	@Autowired
	private EstadoRestController estadoRestController;

	/**
	 * Prueba del método "modificar".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje del cuerpo de la respuesta. Verifica que el mensaje confirme
	 *       que la modificación fue exitosa.
	 *
	 * @param estadoExistente El estado existente a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificar() {
		// Crea un estado de ejemplo
		Estado estadoExistente = new Estado();
		estadoExistente.setIdEstado(5); // Establece un idEstado existente
		estadoExistente.setNombre("Completado"); // Establece el nombre del estado

		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = estadoRestController.modificar(estadoExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Estado de pedido modificado correctamente"),
				"La modificación debería ser correcta");
	}
}
