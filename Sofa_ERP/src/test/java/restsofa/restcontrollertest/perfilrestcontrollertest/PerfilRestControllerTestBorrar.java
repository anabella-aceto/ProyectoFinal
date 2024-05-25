package restsofa.restcontrollertest.perfilrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.PerfilRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "borrar" en
 *          PerfilRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `PerfilRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestBorrar {

	@Autowired
	private PerfilRestController perfilRestController;

	/**
	 * Prueba del método "borrar" cuando el perfil existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la eliminación fue correcta.
	 */
	@Test
	public void testBorrarPerfilExistente() {
		int perfilId = 4; // Reemplaza con un idPerfil válido

		ResponseEntity<?> responseEntity = perfilRestController.borrar(perfilId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la eliminación fue correcta
		assertTrue(mensaje.contains("Perfil eliminado correctamente"), "La eliminación debería ser correcta");
	}

	/**
	 * Prueba del método "borrar" cuando el perfil no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea NOT_FOUND.
	 */
	@Test
	public void testBorrarPerfilNoExistente() {
		int perfilId = -1; // Reemplaza con un idPerfil que no exista

		ResponseEntity<?> responseEntity = perfilRestController.borrar(perfilId);

		// Verifica que el código de estado de la respuesta sea NOT_FOUND
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
}
