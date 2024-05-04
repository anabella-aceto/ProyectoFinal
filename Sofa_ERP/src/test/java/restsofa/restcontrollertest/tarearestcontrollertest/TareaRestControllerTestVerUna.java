package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.TareaDto;
import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "una" en TareaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `TareaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestVerUna {
	
	@Autowired
	TareaRestController tareaRestController;
	
	/**
	 * Prueba del método "una".
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
	 * Obtiene la tarea del cuerpo de la respuesta.
	 * Verifica que la tarea no sea nula.
	 * Verifica que la tarea tenga el idTarea correcto.
	 * Verifica si el pedido de la tarea es correcto.
	 *
	 * @param idTarea El identificador de la tarea a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void testbuscarPorId() {
		int idTarea = 1;
		ResponseEntity<?> responseEntity = tareaRestController.una(idTarea);

		// Asegura que el código de estado de la respuesta sea HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene la tarea del cuerpo de la respuesta
		TareaDto tarea = (TareaDto) responseEntity.getBody();

		// Verifica que la tarea no sea nula
		assertNotNull(tarea, "La tarea no debería ser nula");

		// Verifica que la tarea tenga el idTarea correcto
		assertEquals(idTarea, tarea.getIdTarea(), "No se encuentra la tarea");

		// Verifica si el pedido de la tarea es correcto
		assertEquals(3, tarea.getIdPedido(), "El pedido no coincide con la tarea");
	}

}
