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
 * Clase de prueba JUnit para el método "buscarPorDpto" en EmpleadoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EmpleadoRestController` para realizar las pruebas.
 */
@SpringBootTest
public class EmpleadoRestControllerVerPorDpto {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
	/**
	 * Prueba del método "buscarPorDpto".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene la lista de empleados del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Asegura que el cuerpo de la respuesta sea igual a la lista de muestra.
	 * Llama al método buscarPorDpto con un ID inválido (99).
	 * Asegura que el código de estado de la respuesta sea 400.
	 * Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía.
	 *
	 * @param idDepartamento El identificador del departamento para filtrar los empleados.
	 * @return ResponseEntity con la lista de empleados por departamento.
	 */
	@Test
	public void testBuscarEmpPorDpto() {
		// Llama al método buscarPorDpto con un idDepartamento válido (1)
		ResponseEntity<?> respuesta = empleadoRestController.buscarPorDpto(1);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());

		// Obtiene la lista de empleados del cuerpo de la respuesta
		List<Empleado> empPorDpto = (List<Empleado>) respuesta.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(empPorDpto.isEmpty(), "La lista de empleados por departamento no debería estar vacía");

		// Asegura que el cuerpo de la respuesta sea igual a la lista de muestra
		assertEquals(empPorDpto, respuesta.getBody());

		// Llama al método buscarPorDpto con un ID inválido (99)
		respuesta = empleadoRestController.buscarPorDpto(99);

		// Asegura que el código de estado de la respuesta sea 400
		assertEquals(400, respuesta.getStatusCodeValue());

		// Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía
		assertEquals("La lista está vacía", respuesta.getBody());
	}
}
