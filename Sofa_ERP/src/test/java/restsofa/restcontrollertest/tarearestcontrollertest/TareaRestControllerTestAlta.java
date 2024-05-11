package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

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
 * Clase de prueba JUnit para el método "alta" en TareaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `TareaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestAlta {
	
	@Autowired
	TareaRestController tareaRestController;
	
    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Verifica que el cuerpo de la respuesta contenga el mensaje esperado
     *
     * @param nuevaTarea La tarea a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
	@Test
	public void testAlta() {
	    // Crear tarea de ejemplo
	    TareaDto nuevaTarea = new TareaDto();
	    nuevaTarea.setIdPedido(3);
	    nuevaTarea.setIdDepartamento(1);
	    nuevaTarea.setIdEmpleado(1);
	    nuevaTarea.setIdEstado(1);
	    nuevaTarea.setFecha(new Date());
	    
	    // Llamar al método "alta"
	    ResponseEntity<?> responseEntity = tareaRestController.alta(nuevaTarea);

	    // Verificar que el código de estado de la respuesta sea OK
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	    // Verificar que el cuerpo de la respuesta contenga el mensaje esperado
	    String mensaje = (String) responseEntity.getBody();
	    assertNotNull(mensaje, "El mensaje no debería ser nulo");
	    assertTrue(mensaje.contains("Tarea procesada correctamente"), "El mensaje debe indicar que la tarea se procesó correctamente");
	}


}
