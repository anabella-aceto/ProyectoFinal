package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * Clase de prueba JUnit para el método "todos" en TareaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `TareaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestVerTodas {

	@Autowired
	TareaRestController tareaRestController;

	/**
	 * Prueba del método "todos".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea OK.
	 * Obtiene la lista de tareas del cuerpo de la respuesta.
	 * Verifica que la lista no esté vacía.
	 * Verifica si contiene tareas específicas.
	 *
	 * @return ResponseEntity con la lista de tareas.
	 */
	@Test
	public void testVerTodas() {
		
		ResponseEntity<?> responseEntity = tareaRestController.todos();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		List<Tarea> tareas = (List<Tarea>) responseEntity.getBody();	
		assertFalse(tareas.isEmpty(), "La lista de tareas no debería estar vacía");

		boolean containsSpecificTarea = false;
		for (Tarea tarea : tareas) {
			if (tarea.getIdTarea() == 1 || tarea.getPedido().getIdPedido() == 3) {
				containsSpecificTarea = true;
				break;
			}
		}				
		assertTrue(containsSpecificTarea, "La lista debe contener tareas");
	
	}
}
