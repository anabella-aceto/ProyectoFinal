package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.SofaMaterialDto;
import restsofa.restcontroller.SofaMaterialRestController;

/**
 * @author Alberto Saboya
 * @version 1.0 * Clase de prueba JUnit para el método "modificar" en
 *          SofaMaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaMaterialRestController` para realizar
 *            las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestModificarREVISAR {

	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;

    /**
     * Prueba para verificar la modificación de un material de sofá existente.
     *
     * @test Verifica que al modificar un material de sofá existente, el controlador
     *       responde con un código de estado HttpStatus.OK (200) y un mensaje de éxito.
     *
     * @param sofaMaterialExistente Los datos del material de sofá existente a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarSofaMaterialExistente() {
        // Crea un objeto de ejemplo para el material de sofá existente
        SofaMaterialDto sofaMaterialExistente = new SofaMaterialDto();
        sofaMaterialExistente.setIdSofaMateriales(41);
        // Establece los atributos según el material de sofá existente en tu caso de prueba
        sofaMaterialExistente.setIdSofa(1);
        sofaMaterialExistente.setIdMaterial(1);
        sofaMaterialExistente.setCantidadUtilizada(500);

        // Llama al método "modificarSofaMaterial" con el material existente
        ResponseEntity<?> responseEntity = sofaMaterialRestController.modificarSofaMaterial(sofaMaterialExistente);

        // Verifica que el código de estado de la respuesta sea HttpStatus.OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de éxito del cuerpo de la respuesta
        String mensajeExito = (String) responseEntity.getBody();

        // Verifica que el mensaje de éxito no sea nulo
        assertNotNull(mensajeExito, "Modificación realizada correctamente");
    }

    /**
     * Prueba para verificar la modificación de un material de sofá inexistente.
     *
     * @test Verifica que al intentar modificar un material de sofá que no existe, el
     *       controlador responde con un código de estado HttpStatus.BAD_REQUEST (400) y un
     *       mensaje de error.
     *
     * @param sofaMaterialNoExistente Los datos del material de sofá inexistente a modificar.
     * @return ResponseEntity con el resultado de la operación de modificación.
     */
    @Test
    public void testModificarSofaMaterialNoExistente() {
        // Crea un objeto de ejemplo para un material de sofá que no exista
        SofaMaterialDto sofaMaterialNoExistente = new SofaMaterialDto();
        // Establece los atributos según un material de sofá inexistente en tu caso de prueba
        sofaMaterialNoExistente.setIdSofa(1);
        sofaMaterialNoExistente.setIdMaterial(-1); // Un ID que no exista en tu base de datos
        sofaMaterialNoExistente.setCantidadUtilizada(500);

        // Llama al método "modificarSofaMaterial" con el material no existente
        ResponseEntity<?> responseEntity = sofaMaterialRestController.modificarSofaMaterial(sofaMaterialNoExistente);

        // Verifica que el código de estado de la respuesta sea HttpStatus.BAD_REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Obtiene el mensaje de error del cuerpo de la respuesta
        String mensajeError = (String) responseEntity.getBody();

        // Verifica que el mensaje de error no sea nulo
        assertNotNull(mensajeError, "El mensaje de error no debería ser nulo");
    }   

}
