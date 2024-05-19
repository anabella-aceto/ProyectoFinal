package restsofa.restcontrollertest.perfilrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Perfil;
import restsofa.restcontroller.PerfilRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "uno" en PerfilRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `PerfilRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestVerUno {

	@Autowired
	private PerfilRestController perfilRestController;

	/**
	 * Prueba del método "uno" que busca un perfil existente por su identificador.
	 *
	 * @param perfilId El identificador del perfil a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void test_VerUnoPerfilExistente() {

		int perfilId = 2;

		// Llama al método "uno"
		ResponseEntity<?> responseEntity = perfilRestController.uno(perfilId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el perfil del cuerpo de la respuesta
		Perfil perfil = (Perfil) responseEntity.getBody();

		// Verifica que el perfil no sea nulo
		assertNotNull(perfil, "El perfil no debería ser nulo");

		// Verifica que el perfil tenga el idPerfil correcto
		assertEquals(perfilId, perfil.getIdPerfil(), "El idPerfil no coincide");

		// Verifica si el nombre del perfil es correcto
		assertEquals("comercial", perfil.getRol(), "El nombre del perfil no coincide");
	}

	/**
	 * Prueba del método "uno" cuando el perfil no existe.
	 *
	 * @param perfilId El identificador del perfil a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void test_VerUnoPerfilNoExistente() {

		int perfilIdNoExistente = -1; // Se asume que este perfil no existe

		// Llama al método "uno"
		ResponseEntity<?> responseEntity = perfilRestController.uno(perfilIdNoExistente);

		// Verifica que el código de estado de la respuesta sea NOT_FOUND
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		// Verifica que el cuerpo de la respuesta contenga el mensaje de error esperado
		String expectedErrorMessage = "No se encontró ningún perfil con el identificador: " + perfilIdNoExistente;
		assertEquals(expectedErrorMessage, responseEntity.getBody(), "El mensaje de error no coincide");
	}
}
