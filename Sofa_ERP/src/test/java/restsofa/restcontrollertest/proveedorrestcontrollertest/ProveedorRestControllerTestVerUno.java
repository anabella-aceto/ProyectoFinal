package restsofa.restcontrollertest.proveedorrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Proveedor;
import restsofa.restcontroller.ProveedorRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "uno" en
 *          ProveedorRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `ProveedorRestController` para realizar
 *            las pruebas.
 * 
 */
@SpringBootTest
public class ProveedorRestControllerTestVerUno {

	@Autowired
	private ProveedorRestController proveedorRestController;

	/**
	 * Prueba del método "uno" cuando el proveedor existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       proveedor del cuerpo de la respuesta. Verifica que el proveedor no sea
	 *       nulo. Verifica que el proveedor tenga el idProveedor correcto. Verifica
	 *       si el nombre del proveedor es correcto.
	 *
	 * @param proveedorId El identificador del proveedor a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void testUnoProveedorExiste() {
		int proveedorId = 3;
		ResponseEntity<?> responseEntity = proveedorRestController.uno(proveedorId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el proveedor del cuerpo de la respuesta
		Proveedor proveedor = (Proveedor) responseEntity.getBody();

		// Verifica que el proveedor no sea nulo
		assertNotNull(proveedor, "El proveedor no debería ser nulo");

		// Verifica que el proveedor tenga el idCliente correcto
		assertEquals(proveedorId, proveedor.getIdProveedor(), "El idProveedor no coincide");

		// Verifica si el nombre del proveedor es correcto
		assertEquals("Innova", proveedor.getNombre(), "No se encuentra el proveedor");
	}

	/**
	 * Prueba del método "uno" cuando el proveedor no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea NOT_FOUND.
	 *       Verifica que el cuerpo de la respuesta contenga un mensaje de error.
	 */
	@Test
	public void testUnoProveedorNoExiste() {
	    int proveedorId = -1; // ID de proveedor que no existe
	    ResponseEntity<?> responseEntity = proveedorRestController.uno(proveedorId);

	    // Verifica que el código de estado de la respuesta sea NOT_FOUND
	    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

	    // Verifica que el cuerpo de la respuesta contenga un mensaje de error
	    assertNotNull(responseEntity.getBody(), "El cuerpo de la respuesta debería contener un mensaje de error");
	    assertEquals("No se encuentra el proveedor", responseEntity.getBody(), "El mensaje de error es incorrecto");
	}
}
