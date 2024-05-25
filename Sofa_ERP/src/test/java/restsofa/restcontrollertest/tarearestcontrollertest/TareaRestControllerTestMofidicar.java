package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0 
 * 
 * 			Clase de prueba JUnit para el método "modificar" en
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
     * Prueba del método "modificar" de TareaRestController cuando la tarea existe.
     *
     * Este método verifica que la respuesta al modificar una tarea existente tenga
     * un código de estado HTTP OK (200). También verifica que el mensaje de la
     * respuesta indique que la modificación se realizó correctamente.
     *
     * @throws Exception si ocurre un error durante la prueba.
     */
    @Test
    public void testModificarTareaExistente() {
        // Crea una tarea de ejemplo
        TareaDto tareaExistente = new TareaDto();
        tareaExistente.setIdTarea(15); // Establece una idTarea existente
        tareaExistente.setIdDetalle(3); // Establece el detalle de pedido de la tarea
        tareaExistente.setIdEstado(2); // Establece el estado de la tarea
        tareaExistente.setIdEmpleado(8); // Establece el empleado de la tarea
        tareaExistente.setIdDepartamento(2); // Establece el departamento de la tarea
        tareaExistente.setFecha(new Date()); // Establece la fecha de la tarea

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = tareaRestController.modificar(tareaExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
    }

    /**
     * Prueba del método "modificar" de TareaRestController cuando la tarea no existe.
     *
     * Este método verifica que la respuesta al intentar modificar una tarea que no existe
     * tenga un código de estado HTTP NOT FOUND (404). También verifica que el mensaje de la
     * respuesta indique que la tarea no fue encontrada.
     *
     * @throws Exception si ocurre un error durante la prueba.
     */
    @Test
    public void testModificarTareaNoExistente() {
        // Crea una tarea de ejemplo con un ID que no existe
        TareaDto tareaNoExistente = new TareaDto();
        tareaNoExistente.setIdTarea(-1); // Establece una idTarea que no existe
        tareaNoExistente.setIdDetalle(3); // Establece el detalle de pedido de la tarea
        tareaNoExistente.setIdEstado(2); // Establece el estado de la tarea
        tareaNoExistente.setIdEmpleado(8); // Establece el empleado de la tarea
        tareaNoExistente.setIdDepartamento(2); // Establece el departamento de la tarea
        tareaNoExistente.setFecha(new Date()); // Establece la fecha de la tarea

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = tareaRestController.modificar(tareaNoExistente);

        // Verifica que el código de estado de la respuesta sea NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación no fue exitosa
        assertTrue(mensaje.contains("Tarea no encontrada"), "La tarea no debería ser encontrada");
    }
}