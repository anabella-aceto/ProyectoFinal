package restsofa.restcontrollertest.proveedorrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Proveedor;
import restsofa.restcontroller.ProveedorRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "modificar" en
 *          ProveedorRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `ProveedorRestController` para realizar
 *            las pruebas.
 * 
 */
@SpringBootTest
public class ProveedorRestControllerTestModificar {

	@Autowired
	private ProveedorRestController proveedorRestController;

	/**
	 * Prueba del método "modificar" cuando el proveedor existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la modificación fue exitosa.
	 *
	 * @param proveedorExistente El proveedor con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificarProveedorExiste() {
		// Crea un proveedor de ejemplo existente
		Proveedor proveedorExistente = new Proveedor();
		proveedorExistente.setIdProveedor(7);
		proveedorExistente.setNombre("Neos");
		proveedorExistente.setDescripcion("Consultoría");
		proveedorExistente.setTelefono(687456321);

		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = proveedorRestController.modificar(proveedorExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
	}

	/**
	 * Prueba del método "modificar" cuando el proveedor no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea BAD_REQUEST (400).
	 *       Verifica que el mensaje de la respuesta indique que el proveedor no
	 *       existe.
	 *
	 * @param proveedorNoExistente El proveedor que se intenta modificar y no existe
	 *                             en el sistema.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */
	@Test
	public void testModificarProveedorNoExiste() {
		// Crea un proveedor de ejemplo no existente
		Proveedor proveedorNoExistente = new Proveedor();
		proveedorNoExistente.setIdProveedor(-1); // ID que probablemente no exista
		proveedorNoExistente.setNombre("Neos");
		proveedorNoExistente.setDescripcion("Consultoría");
		proveedorNoExistente.setTelefono(687456321);
		
		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = proveedorRestController.modificar(proveedorNoExistente);

		// Verifica que el código de estado de la respuesta sea BAD_REQUEST (400)
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		// Verifica que el mensaje de la respuesta indique que el proveedor no existe
		String mensaje = (String) responseEntity.getBody();
		assertTrue(mensaje.contains("Error al modificar proveedor en la base de datos"),
				"El proveedor no debería existir");
	}
}
