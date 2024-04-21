package restsofa.restcontrollertest.estadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * Clase de prueba JUnit para el método "alta" en EstadoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `EstadoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class EstadoRestControllerTestAlta {

    @Autowired
    private EstadoRestController estadoRestController;

    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Verifica que el cuerpo de la respuesta contenga el mensaje esperado y el nombre del estado.
     * Asume que la clase Estado tiene un método getNombre() que devuelve el nombre del estado.
     *
     * @param estado El estado a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crear el objeto Estado que se enviará en la solicitud POST
        Estado estado = new Estado();
        estado.setNombre("Exito");

        // Invocar el método alta
        ResponseEntity<?> result = estadoRestController.alta(estado);

        // Verificar que la respuesta tenga el estado HTTP correcto
        assertEquals(HttpStatus.OK, result.getStatusCode());

        // Verificar que el cuerpo de la respuesta contenga el mensaje esperado
        assertTrue(result.getBody().toString().contains("Estado de pedido procesado correctamente"));
        assertTrue(result.getBody().toString().contains(estado.getNombre()));

        // Asumiendo que tienes un getter para el nombre en la clase Estado
        assertEquals("Exito", estado.getNombre());
    }
}

