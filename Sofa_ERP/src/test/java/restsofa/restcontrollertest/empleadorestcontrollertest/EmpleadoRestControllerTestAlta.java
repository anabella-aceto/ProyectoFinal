package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.EmpleadoDto;
import restsofa.modelo.entities.Empleado;
import restsofa.restcontroller.EmpleadoRestController;

@SpringBootTest
public class EmpleadoRestControllerTestAlta {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
	@Test
	public void testAlta() {
		// Crea un empleado de ejemplo
		EmpleadoDto nuevoEmpleado = new EmpleadoDto();

		nuevoEmpleado.setNombre("Elisa"); // Establece el nombre del empleado

		ResponseEntity<?> responseEntity = empleadoRestController.alta(nuevoEmpleado);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Empleado empGuardado = (Empleado) responseEntity.getBody();

		// Verifica que el empleado guardado no sea nulo
		assertNotNull(empGuardado, "El empleado guardado no debería ser nulo");

		// Verifica si el nombre del empleado es correcto
		assertEquals("Elisa", empGuardado.getNombre(), "El nombre del empleado guardado no coincide");
	}

}
