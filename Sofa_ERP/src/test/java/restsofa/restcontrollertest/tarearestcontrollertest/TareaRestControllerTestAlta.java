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

import restsofa.modelo.DTO.TareaAltaDto;
import restsofa.modelo.DTO.TareaDto;
import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
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
     * Este método prueba que el método para dar de alta una tarea funciona correctamente.
     * Se crea una nueva tarea y se verifica que la respuesta sea la esperada.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     * @param nuevaTarea El DTO de la tarea a dar de alta.
     * 
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crear datos de ejemplo
        TareaAltaDto nuevaTarea = new TareaAltaDto();
        nuevaTarea.setIdDetalle(2);
        nuevaTarea.setFecha(new Date());
        
        // Llamar al método "alta"
        ResponseEntity<?> responseEntity = tareaRestController.alta(nuevaTarea);

        // Verificar que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verificar que el cuerpo de la respuesta contenga el mensaje esperado
        String mensaje = (String) responseEntity.getBody();
        assertNotNull(mensaje, "El mensaje no debería ser nulo");
        assertTrue(mensaje.contains("Tareas creadas correctamente"), "El mensaje debe indicar que las tareas se crearon correctamente");
    }
}
