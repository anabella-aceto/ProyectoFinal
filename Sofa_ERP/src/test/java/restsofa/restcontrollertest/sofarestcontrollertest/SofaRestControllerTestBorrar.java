package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.SofaRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "borrar" en SofaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestBorrar {

	@Autowired
	private SofaRestController sofaRestController;

	/**
	 * Prueba del método "borrar" cuando el sofá existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la eliminación fue correcta.
	 */
	@Test
	public void testBorrar_SofaExistente() {
		int sofaIdExistente = 6; // Reemplaza con un idSofa válido que exista

		// Llama al método "borrar"
		ResponseEntity<?> responseEntity = sofaRestController.borrar(sofaIdExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la eliminación fue correcta
		assertTrue(mensaje.contains("Sofá eliminado correctamente"), "La eliminación debería ser correcta");
	}

	/**
	 * Prueba del método "borrar" cuando el sofá no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea NOT_FOUND. Obtiene
	 *       el mensaje de la respuesta. Verifica que la eliminación no se pudo
	 *       realizar.
	 */
	@Test
	public void testBorrar_SofaNoExistente() {
		int sofaIdNoExistente = -1; // Reemplaza con un idSofa que no exista

		// Llama al método "borrar"
		ResponseEntity<?> responseEntity = sofaRestController.borrar(sofaIdNoExistente);

		// Verifica que el código de estado de la respuesta sea NOT_FOUND
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la eliminación no fue correcta
		assertTrue(mensaje.contains("El sofá a eliminar no existe"), "La eliminación no debería ser posible");
	}
}
