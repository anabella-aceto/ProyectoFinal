package restsofa.restcontrollertest.estadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Estado;
import restsofa.restcontroller.EstadoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "todos" en `EstadoRestController`.
 * Verifica el comportamiento del método "todos" que obtiene la lista de estados.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EstadoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class EstadoRestControllerTestVerTodos {

    @Autowired
    private EstadoRestController estadoRestController;

    /**
     * Caso de prueba para el método "todos" del controlador de estados.
     * Se verifica que al llamar al método "todos" del controlador, se reciba un código de estado 200 OK,
     * que la lista de estados no esté vacía y que contenga estados específicos.
     *
     * @test Verifica el correcto funcionamiento del método "todos" del controlador de estados.
     * @return No devuelve ningún valor, pero lanza una excepción si la prueba falla.
     */
    @Test
    public void testTodos() {
        // Llama al método "todos"
        ResponseEntity<?> responseEntity = estadoRestController.todos();

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene la lista de estados del cuerpo de la respuesta
        List<Estado> estados = (List<Estado>) responseEntity.getBody();

        // Verifica que la lista no esté vacía
        assertFalse(estados.isEmpty(), "La lista de estados no debería estar vacía");

        // Verifica si contiene estados específicos
        boolean contieneEstadoEspecifico = false;
        for (Estado estado : estados) {
            if (estado.getIdEstado() == 2 || estado.getNombre().equals("Procesando")) {
                contieneEstadoEspecifico = true;
                break;
            }
        }
        assertTrue(contieneEstadoEspecifico, "La lista debe contener estados específicos");
    }
}

