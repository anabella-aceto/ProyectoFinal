package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Tarea;
import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "filtrarPorDepartametno" en
 *          TareaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `TareaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestVerPorDepartamento {
	
	@Autowired
	TareaRestController tareaRestController;

	/**
	 * Prueba del método "filtrarPorDepartamento" con un departamento existente.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea HttpStatus.OK
	 *       (200). Obtiene la lista de tareas por departamento del cuerpo de la
	 *       respuesta. Verifica que la lista no esté vacía. Asegura que el cuerpo
	 *       de la respuesta no sea null.
	 *
	 * @param idEmpleado El identificador del departamento para filtrar las tareas.
	 * @return ResponseEntity con la lista de tareas por departamento.
	 */
	@Test
	public void testBuscarPorDepartamentoExistente() {
	    // Llama al método filtrarPorDepartamento con un idDepartamento válido (1)
	    ResponseEntity<?> respuesta = tareaRestController.filtrarPorDepartamento(1);

	    // Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
	    assertEquals(HttpStatus.OK, respuesta.getStatusCode());

	    // Obtiene la lista de tareas del cuerpo de la respuesta
	    List<Tarea> tareaPorDepartamento = (List<Tarea>) respuesta.getBody();

	    // Verifica que la lista no esté vacía
	    assertFalse(tareaPorDepartamento.isEmpty(), "La lista de tareas por departamento no debería estar vacía");

	    // Asegura que el cuerpo de la respuesta no sea null
	    assertNotNull(respuesta.getBody());
	}

	/**
	 * Prueba del método "filtrarPorDepartamento" con un departamento que no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Llama al método filtrarPorDepartamento con un ID que no existe (-1).
	 *       Asegura que el código de estado de la respuesta sea 400. Verifica el
	 *       mensaje del cuerpo de la respuesta cuando el departamento no existe.
	 *
	 * @return ResponseEntity con un mensaje de error.
	 */
	@Test
	public void testBuscarPorDepartamentoNoExistente() {
		// Llama al método filtrarPorEmpleado con un ID que no existe (-1)
		ResponseEntity<?> respuesta = tareaRestController.filtrarPorDepartamento(-1);

		// Asegura que el código de estado de la respuesta sea 400
		assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());

		// Verifica el mensaje del cuerpo de la respuesta cuando el empleado no existe
		assertEquals("No se encuentran tareas para el departamento ingresado", respuesta.getBody());

	}

}
