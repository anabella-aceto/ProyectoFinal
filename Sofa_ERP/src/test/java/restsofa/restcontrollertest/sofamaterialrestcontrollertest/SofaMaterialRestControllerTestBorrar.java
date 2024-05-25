package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.SofaMaterialRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "borrarUno" en SofaMaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaMaterialRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestBorrar {
	
	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;
	
	/**
	 * Prueba del método "borrarUno" cuando el material de sofá existe.
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea OK.
	 * Obtiene el mensaje de la respuesta.
	 * Verifica que la eliminación fue correcta.
	 *
	 * @param idSofaMaterialExistente El identificador del material de sofá que existe en la base de datos.
	 * @return ResponseEntity con el resultado de la operación de borrado.
	 */
	@Test
	public void testBorrarUnoExistente() {
	    // ID de un material de sofá existente en la base de datos
	    int idSofaMaterialExistente = 41;

	    // Invocamos el método "borrarUno" del controlador
	    ResponseEntity<?> responseEntity = sofaMaterialRestController.borrarUno(idSofaMaterialExistente);

	    // Verificamos que el código de estado de la respuesta sea OK (200)
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	    // Obtenemos el mensaje de la respuesta
	    String mensaje = (String) responseEntity.getBody();

	    // Verificamos que el mensaje indique que la eliminación fue correcta
	    assertTrue(mensaje.contains("Material de sofá eliminado correctamente"), "La eliminación debería ser correcta");
	}

	/**
	 * Prueba del método "borrarUno" cuando el material de sofá no existe.
	 *
	 * @Test
	 * Anota este método como una prueba JUnit.
	 *
	 * Verifica que el código de estado de la respuesta sea BAD_REQUEST.
	 * Obtiene el mensaje de la respuesta.
	 * Verifica que no se pudo encontrar el material de sofá.
	 *
	 * @param idSofaMaterialNoExistente El identificador del material de sofá que no existe en la base de datos.
	 * @return ResponseEntity con el resultado de la operación de borrado.
	 */
	@Test
	public void testBorrarUnoNoExistente() {
	    // ID de un material de sofá que no existe en la base de datos
	    int idSofaMaterialNoExistente = -1; // Reemplaza con un ID que no exista

	    // Invocamos el método "borrarUno" del controlador
	    ResponseEntity<?> responseEntity = sofaMaterialRestController.borrarUno(idSofaMaterialNoExistente);

	    // Verificamos que el código de estado de la respuesta sea BAD_REQUEST (400)
	    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	    // Obtenemos el mensaje de la respuesta
	    String mensaje = (String) responseEntity.getBody();

	    // Verificamos que el mensaje indique que no se pudo encontrar el material de sofá
	    assertTrue(mensaje.contains("Error al borrar material de sofá"), "No se debería encontrar el material de sofá");
	}

}
