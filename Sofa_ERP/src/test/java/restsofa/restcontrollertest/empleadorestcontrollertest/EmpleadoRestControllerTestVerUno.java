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
 * Clase de prueba JUnit para el método "buscarPorId" en EmpleadoRestController.
 */
@SpringBootTest
public class EmpleadoRestControllerTestVerUno {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
	/**
	 * Prueba del método "buscarPorId".
	 */
	@Test
	public void testbuscarPorId() {
		int empId = 3;
		ResponseEntity<?> responseEntity = empleadoRestController.buscarPorId(empId);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el empleado del cuerpo de la respuesta
		Empleado empleado = (Empleado) responseEntity.getBody();

		// Verifica que el empleado no sea nulo
		assertNotNull(empleado, "El empleado no debería ser nulo");

		// Verifica que el empleado tenga el idEmpleado correcto
		assertEquals(empId, empleado.getIdEmpleado(), "No se encuentra el empleado");

		// Verifica si el nombre del empleado es correcto
		assertEquals("Pedro", empleado.getNombre(), "El nombre del empleado no coincide");
	}
}
