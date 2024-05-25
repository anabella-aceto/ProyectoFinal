package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Sofa;
import restsofa.restcontroller.SofaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "modificar" en
 *          SofaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestModificar {

	@Autowired
	private SofaRestController sofaRestController;

	/**
	 * Prueba del método "modificar" cuando el sofá existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la modificación fue exitosa.
	 */
	@Test
	public void testModificar_SofaExistente() {
		// Crea un sofá de ejemplo que existe
		Sofa sofaExistente = new Sofa();
		sofaExistente.setIdSofa(6); // Establece un idSofa existente
		sofaExistente.setNombre("Alhambra"); // Establece el nombre del sofá
		sofaExistente.setDescripcion("Sofa de reminiscencias moriscas"); // Establece la descripción del sofá
		sofaExistente.setPatas(6); // Establece el número de patas del sofá
		sofaExistente.setPrecio(2000); // Establece el precio del sofá
		sofaExistente.setMedidaCojin("50"); // Establece la medida del cojín del sofá

		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = sofaRestController.modificar(sofaExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación de sofá realizada correctamente"),
				"La modificación debería ser correcta");
	}

	/**
	 * Prueba del método "modificar" cuando el sofá no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea BAD_REQUEST.
	 *       Obtiene el mensaje de la respuesta. Verifica que la modificación no se
	 *       pudo realizar.
	 */
	@Test
	public void testModificar_SofaNoExistente() {
		// Crea un sofá de ejemplo que no existe
		Sofa sofaNoExistente = new Sofa();
		sofaNoExistente.setIdSofa(-1); // Establece un idSofa que no existe
		sofaNoExistente.setNombre("Granada"); // Establece el nombre del sofá
		sofaNoExistente.setDescripcion("Sofa moderno"); // Establece la descripción del sofá
		sofaNoExistente.setPatas(4); // Establece el número de patas del sofá
		sofaNoExistente.setPrecio(1500); // Establece el precio del sofá
		sofaNoExistente.setMedidaCojin("45"); // Establece la medida del cojín del sofá

		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = sofaRestController.modificar(sofaNoExistente);

		// Verifica que el código de estado de la respuesta sea BAD_REQUEST
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación no fue exitosa
		assertTrue(mensaje.contains("Error al modificar el sofá en la BBDD"), "La modificación no debería ser posible");
	}
}
