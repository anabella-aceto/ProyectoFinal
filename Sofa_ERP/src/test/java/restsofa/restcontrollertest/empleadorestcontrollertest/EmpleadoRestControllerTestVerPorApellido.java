package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Empleado;
import restsofa.restcontroller.EmpleadoRestController;

/**
 * Clase de prueba JUnit para el método "buscarPorApellido" en EmpleadoRestController.
 */
@SpringBootTest
public class EmpleadoRestControllerTestVerPorApellido {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
	/**
	 * Prueba del método "buscarPorApellido".
	 */
	@Test
	public void testBuscarPorApellido() {
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
}