package restsofa.restcontrollertest.estadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.EstadoRestController;

/**
 * @author Alberto Saboya, Anabella Av¡ceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "borrar" en
 *          EstadoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EstadoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class EstadoRestControllerTestBorrar {

	@Autowired
	private EstadoRestController estadoRestController;

	/**
	 * Prueba del método "borrar".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje del cuerpo de la respuesta. Verifica que el mensaje confirme
	 *       que la eliminación fue exitosa.
	 *
	 * @param estadoId El ID del estado a eliminar.
	 * @return ResponseEntity con el resultado de la operación de eliminación.
	 */
	@Test
	public void testBorrarEstadoExistente() {
		int estadoId = 5; // Reemplaza con un idEstado válido
		ResponseEntity<?> responseEntity = estadoRestController.borrar(estadoId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la eliminación fue correcta
		String mensajeEsperado = "Estado de pedido eliminado correctamente";
		assertTrue(mensaje != null && mensaje.contains(mensajeEsperado), "La eliminación debería ser correcta");
	}

	/**
	 * Prueba del método "borrar" cuando el estado a eliminar no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea BAD_REQUEST.
	 *       Obtiene el mensaje del cuerpo de la respuesta. Verifica que el mensaje
	 *       indique que no se puede eliminar el estado.
	 *
	 * @param estadoId El ID del estado a eliminar.
	 * @return ResponseEntity con el resultado de la operación de eliminación.
	 */
	@Test
	public void testBorrarEstadoInexistente() {
		int estadoId = -1; // ID de un estado que no existe
		ResponseEntity<?> responseEntity = estadoRestController.borrar(estadoId);

		// Verifica que el código de estado de la respuesta sea BAD_REQUEST
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que no se pudo eliminar el estado
		String mensajeEsperado = "Estado de pedido no se ha podido eliminar";
		assertTrue(mensaje != null && mensaje.contains(mensajeEsperado), "No se pudo eliminar el estado");
	}
}
