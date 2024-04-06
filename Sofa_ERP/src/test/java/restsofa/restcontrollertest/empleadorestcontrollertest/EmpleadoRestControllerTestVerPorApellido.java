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
public class EmpleadoRestControllerTestVerPorApellido {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
	@Test
	public void testUno() {
		String apellidos = "Ruiz";
		ResponseEntity<?> responseEntity = empleadoRestController.buscarPorApellido(apellidos);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Empleado empleado = (Empleado) responseEntity.getBody();

		// Verifica que el cliente no sea nulo
		assertNotNull(empleado, "El empleado no debería ser nulo");

		// Verifica si los apellidos del cliente es correcto
		assertEquals("Ruiz", empleado.getApellidos(), "Los apellidos del empleado no coinciden");
	}
}
