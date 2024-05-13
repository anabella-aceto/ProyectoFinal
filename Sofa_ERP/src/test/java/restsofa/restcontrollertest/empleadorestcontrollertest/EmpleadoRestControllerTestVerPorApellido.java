package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 *          Clase de prueba JUnit para el método "buscarPorApellido" en
 *          EmpleadoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EmpleadoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class EmpleadoRestControllerTestVerPorApellido {

	@Autowired
	private EmpleadoRestController empleadoRestController;

	/**
	 * Prueba del método "buscarPorApellido".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       empleado del cuerpo de la respuesta. Verifica que el empleado no sea
	 *       nulo. Verifica si los apellidos del empleado son correctos.
	 *
	 * @param apellidos Los apellidos del empleado a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void testBuscarPorApellidoExistente() {
		String apellidos = "Ruiz";
		ResponseEntity<?> responseEntity = empleadoRestController.buscarPorApellido(apellidos);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el empleado del cuerpo de la respuesta
		Empleado empleado = (Empleado) responseEntity.getBody();

		// Verifica que el empleado no sea nulo
		assertNotNull(empleado, "El empleado no debería ser nulo");

		// Verifica si los apellidos del empleado son correctos
		assertEquals("Ruiz", empleado.getApellidos(), "Los apellidos del empleado no coinciden");
	}

	/**
	 * Prueba del método "buscarPorApellido" cuando se proporciona un apellido
	 * inválido.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el método maneje correctamente un apellido que no existe
	 *       en la base de datos.
	 *
	 * @param apellidos Los apellidos del empleado a buscar. Debe ser un apellido
	 *                  que no existe en la base de datos.
	 * @return ResponseEntity con el resultado de la búsqueda. Debería tener un
	 *         código de estado NOT_FOUND y un cuerpo que indica que no se encontró
	 *         ningún empleado.
	 */
	@Test
	public void testBuscarPorApellidoInvalido() {
		// Definimos un apellido que sabemos que no existe en la base de datos
		String apellidos = "ApellidoQueNoExiste";

		// Llamamos al método para buscar por apellido
		ResponseEntity<?> responseEntity = empleadoRestController.buscarPorApellido(apellidos);

		// Verificamos que el código de estado de la respuesta sea NOT_FOUND
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		// Verificamos que el cuerpo de la respuesta contenga un mensaje indicando que
		// no se encontró ningún empleado
		String mensaje = (String) responseEntity.getBody();
		assertNotNull(mensaje, "El mensaje no debería ser nulo");
		assertTrue(mensaje.contains("No se encontró ningún empleado"),
				"El mensaje debería indicar que no se encontró ningún empleado");
	}
}
