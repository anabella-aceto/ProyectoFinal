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

@SpringBootTest
public class EmpleadoRestControllerTestVerUno {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
	@Test
	public void testUno() {
		int empId = 3;
		ResponseEntity<?> responseEntity = empleadoRestController.buscarPorId(empId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Empleado empleado = (Empleado) responseEntity.getBody();

		// Verifica que el cliente no sea nulo
		assertNotNull(empleado, "El empleado no debería ser nulo");

		// Verifica que el cliente tenga el idCliente correcto
		assertEquals(empId, empleado.getIdEmpleado(), "No se encuentra el empleado");

		// Verifica si el nombre del cliente es correcto
		assertEquals("Pedro", empleado.getNombre(), "El nombre del empleado no coincide");
	}

}
