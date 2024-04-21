package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * Clase de prueba JUnit para el método "listarEmpleados" en EmpleadoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EmpleadoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class EmpleadoRestControllerTestVerTodos {

	@Autowired
	private EmpleadoRestController empleadoRestController;

	/**
	 * Prueba del método "listarEmpleados".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea OK.
	 * Obtiene la lista de empleados del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Verifica si contiene empleados específicos.
	 *
	 * @return ResponseEntity con la lista de empleados.
	 */
	@Test
	public void testTodos() {
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		List<Empleado> empleados = (List<Empleado>) responseEntity.getBody();	
		assertFalse(empleados.isEmpty(), "La lista de empleados no debería estar vacía");

		boolean containsSpecificClient = false;
		for (Empleado empleado : empleados) {
			if (empleado.getIdEmpleado() == 4 || empleado.getNombre().equals("Laura")) {
				containsSpecificClient = true;
				break;
			}
		}				
		assertTrue(containsSpecificClient, "La lista debe contener empleados");
	}

}
