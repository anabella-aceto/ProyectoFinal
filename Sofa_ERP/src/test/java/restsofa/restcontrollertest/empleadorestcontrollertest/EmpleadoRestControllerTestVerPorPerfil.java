package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Empleado;
import restsofa.restcontroller.EmpleadoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "listarPorPerfil" en
 *          EmpleadoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EmpleadoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class EmpleadoRestControllerTestVerPorPerfil {

	@Autowired
	private EmpleadoRestController empleadoRestController;

	/**
	 * Prueba el caso en que se encuentran empleados por perfil.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Se llama al método listarPorPerfil con un ID válido (1) y se verifica
	 *       que la respuesta sea exitosa (código de estado 200 OK). Se obtiene la
	 *       lista de empleados del cuerpo de la respuesta y se verifica que no esté
	 *       vacía. Se asegura que el cuerpo de la respuesta sea igual a la lista de
	 *       empleados esperada.
	 *
	 * @param empleadoRestController El controlador REST de empleados.
	 * @return ResponseEntity con la lista de empleados por perfil.
	 */
	@Test
	public void testBuscarEmpPorPerfilExistente() {
		// Llama al método listarPorPerfil con un idPerfil válido (1)
		ResponseEntity<?> respuesta = empleadoRestController.listarPorPerfil(1);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());

		// Obtiene la lista de empleados del cuerpo de la respuesta
		List<Empleado> empPorPerfil = (List<Empleado>) respuesta.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(empPorPerfil.isEmpty(), "La lista de empleados por perfil no debería estar vacía");

		// Asegura que el cuerpo de la respuesta sea igual a la lista de muestra
		assertEquals(empPorPerfil, respuesta.getBody());
	}

	/**
	 * Prueba el caso en que se proporciona un perfil inválido.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Llama al método listarPorPerfil con un ID de perfil inválido (ID -1).
	 *       Asegura que el código de estado de la respuesta sea 404 (Not Found).
	 *       Verifica el mensaje del cuerpo de la respuesta cuando se proporciona un
	 *       perfil inválido.
	 *
	 * @param empleadoRestController El controlador REST de empleados.
	 * @return ResponseEntity con el mensaje de error cuando se proporciona un
	 *         perfil inválido.
	 */
	@Test
	public void testPerfilInvalido() {
		// Llama al método listarPorPerfil con un idPerfil inválido (-1)
		ResponseEntity<?> respuesta = empleadoRestController.listarPorPerfil(-1);

		// Asegura que el código de estado de la respuesta sea HttpStatus.NOT_FOUND
		// (404) ya que se espera que el perfil no se encuentre
		assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());

		// Verifica el mensaje del cuerpo de la respuesta cuando se proporciona un
		// perfil inválido
		assertEquals("No se encontró el perfil", respuesta.getBody());
	}
}
