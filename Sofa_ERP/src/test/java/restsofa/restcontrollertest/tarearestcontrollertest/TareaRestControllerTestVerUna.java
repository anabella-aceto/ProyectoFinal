package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.TareaDto;
import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
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
     * Prueba del método "una" cuando se busca una tarea que existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea HttpStatus.OK (200).
     * Obtiene la tarea del cuerpo de la respuesta.
     * Verifica que la tarea no sea nula.
     * Verifica que la tarea tenga el idTarea correcto.
     * Verifica si el empleado de la tarea es correcto.
     *
     * @param idTarea El identificador de la tarea a buscar.
     * @return ResponseEntity con el resultado de la búsqueda.
     */
    @Test
    public void testBuscarPorIdTareaExistente() {
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

        // Verifica si el empleado de la tarea es correcto
        assertEquals(1, tarea.getIdEmpleado(), "El empleado no coincide con la tarea");
    }

    /**
     * Prueba del método "una" cuando se busca una tarea que no existe.
     *
     * @Test Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea HttpStatus.NOT_FOUND (404).
     * Verifica que el cuerpo de la respuesta sea nulo.
     *
     * @param idTarea El identificador de la tarea a buscar.
     * @return ResponseEntity con el resultado de la búsqueda.
     */
    @Test
    public void testBuscarPorIdTareaNoExistente() {
        int idTarea = -1; // Id de una tarea que no existe
        ResponseEntity<?> responseEntity = tareaRestController.una(idTarea);

        // Asegura que el código de estado de la respuesta sea HttpStatus.NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verifica que el cuerpo de la respuesta sea nulo
        assertNull(responseEntity.getBody(), "La respuesta debería ser nula");
    }
}
