package restsofa.restcontrollertest.perfilrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Perfil;
import restsofa.restcontroller.PerfilRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "todos" en
 *          PerfilRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `PerfilRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class PerfilRestControllerTestVerTodos {

	@Autowired
	private PerfilRestController perfilRestController;

	/**
	 * Prueba para verificar que la respuesta del método todos() del controlador no
	 * es nula.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaNoNula() {
		// Llama al método "todos"
		ResponseEntity<?> responseEntity = perfilRestController.todos();

		// Verificar que la respuesta no sea nula
		assertNotNull(responseEntity);
	}

	/**
	 * Prueba para verificar el código de estado de la respuesta del método todos()
	 * del controlador.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosCodigoEstado() {
		// Llama al método "todos"
		ResponseEntity<?> responseEntity = perfilRestController.todos();

		// Verificar el código de estado de la respuesta
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador no es nulo.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosCuerpoRespuestaNoNulo() {
		// Llama al método "todos"
		ResponseEntity<?> responseEntity = perfilRestController.todos();

		// Verificar que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador es una lista.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaListaPerfiles() {
		// Llama al método "todos"
		ResponseEntity<?> responseEntity = perfilRestController.todos();

		// Verificar que el cuerpo de la respuesta sea una lista de perfiles
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de perfiles devuelta por el método todos()
	 * del controlador no está vacía.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosNoVacia() {
		// Llama al método "todos"
		ResponseEntity<?> responseEntity = perfilRestController.todos();

		// Verificar que la lista de perfiles no esté vacía
		List<?> listaPerfiles = (List<?>) responseEntity.getBody();
		assertFalse(listaPerfiles.isEmpty(), "La lista de perfiles no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de perfiles contiene un perfil específico.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosContienePerfilEspecifico() {
		// Llama al método "todos"
		ResponseEntity<?> responseEntity = perfilRestController.todos();

		// Verificar que la lista de perfiles contenga un perfil específico
		List<Perfil> perfiles = (List<Perfil>) responseEntity.getBody();
		boolean contienePerfilEspecifico = false;
		for (Perfil perfil : perfiles) {
			if (perfil.getIdPerfil() == 2 || "Comercial".equals(perfil.getRol())) {
				contienePerfilEspecifico = true;
				break;
			}
		}
		assertTrue(contienePerfilEspecifico, "La lista debe contener perfiles específicos");
	}
}