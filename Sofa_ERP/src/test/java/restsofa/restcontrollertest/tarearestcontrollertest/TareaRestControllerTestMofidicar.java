package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.TareaDto;
import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0 * 
 *          Clase de prueba JUnit para el método "modificar" en
 *          TareaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `TareaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestMofidicar {
	
	@Autowired
	TareaRestController tareaRestController;
	
	/**
	 * Prueba del método "modificar".
	 *
	 * @Test Anota este método como una prueba JUnit.	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la modificación fue exitosa.
	 *
	 * @param tareaExistente La tarea con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificar() {
		// Crea una tarea de ejemplo
		TareaDto tareaExistente = new TareaDto();
		tareaExistente.setIdTarea(1);; // Establece una idTarea existente
		tareaExistente.setIdPedido(5);; // Establece el pedido de la tarea
		
		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = tareaRestController.modificar(tareaExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
	}

}
