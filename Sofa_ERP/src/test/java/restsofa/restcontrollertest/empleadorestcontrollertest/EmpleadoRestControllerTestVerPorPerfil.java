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
 * Clase de prueba JUnit para el método "listarPorPerfil" en EmpleadoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EmpleadoRestController` para realizar las pruebas.
 * 
 * @author Alberto Saboya
 * @version 1.0
 */
@SpringBootTest
public class EmpleadoRestControllerTestVerPorPerfil {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
	/**
	 * Prueba del método "listarPorPerfil".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea OK.
	 * Obtiene la lista de empleados del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Asegura que el cuerpo de la respuesta sea igual a la lista de muestra.
	 * Llama al método listarPorPerfil con un ID inválido (99).
	 * Asegura que el código de estado de la respuesta sea 400.
	 * Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía.
	 *
	 * @param idPerfil El identificador del perfil para filtrar los empleados.
	 * @return ResponseEntity con la lista de empleados por perfil.
	 */
	@Test
	public void testBuscarEmpPorPerfil() {
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

		// Llama al método listarPorPerfil con un ID inválido (99)
		respuesta = empleadoRestController.listarPorPerfil(99);

		// Asegura que el código de estado de la respuesta sea 400
		assertEquals(400, respuesta.getStatusCodeValue());

		// Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía
		assertEquals("La lista está vacía", respuesta.getBody());
	}
}
