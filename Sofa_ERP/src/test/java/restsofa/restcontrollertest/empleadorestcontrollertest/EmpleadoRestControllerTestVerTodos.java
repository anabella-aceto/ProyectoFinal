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

@SpringBootTest
public class EmpleadoRestControllerTestVerTodos {

	@Autowired
	private EmpleadoRestController empleadoRestController;

	@Test
	public void testTodos() {
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		List<Empleado> empleados = (List<Empleado>) responseEntity.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(empleados.isEmpty(), "La lista de empleados no debería estar vacía");

		// Verifica si contiene empleados
		boolean containsSpecificClient = empleados.stream()
				.anyMatch(empleado -> empleado.getIdEmpleado() == 4 || empleado.getNombre().equals("Laura"));

		assertTrue(containsSpecificClient, "La lista debe contener empleados");
	}

}
