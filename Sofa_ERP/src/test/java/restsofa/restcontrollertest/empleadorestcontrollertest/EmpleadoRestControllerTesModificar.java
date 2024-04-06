package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.EmpleadoDto;
import restsofa.restcontroller.EmpleadoRestController;

@SpringBootTest
public class EmpleadoRestControllerTesModificar {

	@Autowired
	private EmpleadoRestController empleadoRestController;

	@Test
	public void testModificar() {
		// Crea un empleado de ejemplo
		EmpleadoDto empExistente = new EmpleadoDto();
		empExistente.setIdEmpleado(6); // Establece un idEmpleado existente
		empExistente.setNombre("Roberto"); // Establece el nombre del empleado

		ResponseEntity<?> responseEntity = empleadoRestController.modificarEmpleado(empExistente);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
	}

}
