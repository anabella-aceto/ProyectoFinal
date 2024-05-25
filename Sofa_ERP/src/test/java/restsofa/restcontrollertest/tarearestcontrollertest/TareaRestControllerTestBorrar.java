package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "borrar" en
 *          TareaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `TareaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestBorrar {

	@Autowired
	TareaRestController tareaRestController;

	/**
	 * Prueba del método "borrar" para una tarea existente.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la eliminación fue correcta.
	 *
	 * @param idTarea El identificador de la tarea a borrar.
	 * @return ResponseEntity con el resultado de la operación de borrado.
	 */
	@Test
	public void testBorrarTareaExistente() {
		int idTarea = 14; // Reemplaza con un idTarea válido
		ResponseEntity<?> responseEntity = tareaRestController.borrar(idTarea);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la eliminación fue correcta
		assertTrue(mensaje.contains("Tarea eliminada correctamente"), "La eliminación debería ser correcta");
	}

	/**
	 * Prueba del método "borrar" para una tarea no existente.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea NOT_FOUND. Obtiene
	 *       el mensaje de la respuesta. Verifica que la eliminación no fue exitosa
	 *       debido a la inexistencia de la tarea.
	 *
	 * @param idTarea El identificador de la tarea no existente a intentar borrar.
	 * @return ResponseEntity con el resultado de la operación de borrado.
	 */
	@Test
	public void testBorrarTareaNoExistente() {
		int idTareaNoExistente = -1; // Reemplaza con un idTarea no existente
		ResponseEntity<?> responseEntity = tareaRestController.borrar(idTareaNoExistente);

		// Verifica que el código de estado de la respuesta sea NOT_FOUND
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la eliminación no fue exitosa debido a la inexistencia de la
		// tarea
		assertTrue(mensaje.contains("No se encontró la tarea especificada"),
				"La tarea no debería existir para ser borrada");
	}
}
