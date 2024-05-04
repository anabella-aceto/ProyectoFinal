package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Tarea;
import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "filtrarPorEmpleado" en TareaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `TareaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestVerPorEmpleado {
	
	@Autowired
	TareaRestController tareaRestController;
	
	/**
	 * Prueba del método "filtrarPorEmpleado".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene la lista de tareas por empleado del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Asegura que el cuerpo de la respuesta sea igual a la lista de muestra.
	 * Llama al método filtrarPorEmpleado con un ID inválido (99).
	 * Asegura que el código de estado de la respuesta sea 400.
	 * Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía.
	 *
	 * @param idEmpleado El identificador del empleado para filtrar las tareas.
	 * @return ResponseEntity con la lista de tareas por empleado.
	 */
	@Test
	public void testBuscarPorEmpleado() {
		// Llama al método filtrarPorEmpleado con un idEmpleado válido (1)
		ResponseEntity<?> respuesta = tareaRestController.filtrarPorEmpleado(1);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());

		// Obtiene la lista de empleados del cuerpo de la respuesta
		List<Tarea> tareaPorEmpleado = (List<Tarea>) respuesta.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(tareaPorEmpleado.isEmpty(), "La lista de tareas por empleado no debería estar vacía");

		// Asegura que el cuerpo de la respuesta sea igual a la lista de muestra
		assertEquals(tareaPorEmpleado, respuesta.getBody());

		// Llama al método filtrarPorEmpleado con un ID inválido (99)
		respuesta = tareaRestController.filtrarPorEmpleado(99);

		// Asegura que el código de estado de la respuesta sea 400
		assertEquals(400, respuesta.getStatusCodeValue());

		// Verifica el mensaje del cuerpo de la respuesta cuando la lista está vacía
		assertEquals("No se encuentran tareas para el empleado ingresado", respuesta.getBody());
	}

}
