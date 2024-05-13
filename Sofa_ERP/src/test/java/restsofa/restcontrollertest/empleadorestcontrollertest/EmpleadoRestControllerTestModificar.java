package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.EmpleadoDto;
import restsofa.restcontroller.EmpleadoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0 * Clase de prueba JUnit para el método "modificarEmpleado" en
 *          EmpleadoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EmpleadoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class EmpleadoRestControllerTestModificar {

	@Autowired
	private EmpleadoRestController empleadoRestController;

	/**
	 * Prueba el método "modificarEmpleado" cuando el empleado existe.
	 *
	 * @Test Anota este método como una prueba JUnit. Verifica que el código de
	 *       estado de la respuesta sea OK y que el mensaje de la respuesta indique
	 *       una modificación exitosa.
	 *
	 * @param empleadoExistente El empleado con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificarEmpleadoExistente() {
		// Crea un empleado de ejemplo
		EmpleadoDto empExistente = new EmpleadoDto();
		empExistente.setIdEmpleado(6); // Establece un idEmpleado existente
		empExistente.setNombre("Roberto"); // Establece el nombre del empleado
		empExistente.setApellidos("Gomez"); // Establece el apellido del empleado
		empExistente.setIdDepartamento(2); // Establece el departamento del empleado
		empExistente.setIdPerfil(2); // Establece el perfil del empleado
		empExistente.setFechaIngreso(new Date());
		empExistente.setSalario(32000);

		// Llama al método "modificarEmpleado"
		ResponseEntity<?> responseEntity = empleadoRestController.modificarEmpleado(empExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
	}

}
