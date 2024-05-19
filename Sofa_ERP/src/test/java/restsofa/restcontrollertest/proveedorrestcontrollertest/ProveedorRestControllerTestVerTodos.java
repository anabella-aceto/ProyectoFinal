package restsofa.restcontrollertest.proveedorrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
 *          Clase de prueba JUnit para el método "mostrartodos" en
 *          ProveedorRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `ProveedorRestController` para realizar
 *            las pruebas.
 * 
 */
@SpringBootTest
public class ProveedorRestControllerTestVerTodos {

	@Autowired
	private ProveedorRestController proveedorRestController;

	/**
	 * Prueba para verificar que la respuesta del método mostrartodos() del
	 * controlador no sea nula.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testMostrartodosRespuestaNoNula() {
		// Llama al método "mostrartodos"
		ResponseEntity<?> responseEntity = proveedorRestController.mostrartodos();

		// Verifica que la respuesta no sea nula
		assertNotNull(responseEntity);
	}

	/**
	 * Prueba para verificar el código de estado de la respuesta del método
	 * mostrartodos() del controlador.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testMostrartodosCodigoEstado() {
		// Llama al método "mostrartodos"
		ResponseEntity<?> responseEntity = proveedorRestController.mostrartodos();

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método mostrartodos()
	 * del controlador no sea nulo.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testMostrartodosCuerpoRespuestaNoNulo() {
		// Llama al método "mostrartodos"
		ResponseEntity<?> responseEntity = proveedorRestController.mostrartodos();

		// Verifica que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());

		// Si la respuesta es un mensaje de error, imprímelo
		if (!(responseEntity.getBody() instanceof List<?>)) {
			System.out.println("Mensaje de error: " + responseEntity.getBody());
		}
	}

	/**
	 * Prueba para verificar que la lista de proveedores devuelta por el método
	 * mostrartodos() del controlador no esté vacía.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testMostrartodosListaNoVacia() {
		// Llama al método "mostrartodos"
		ResponseEntity<?> responseEntity = proveedorRestController.mostrartodos();

		// Verifica que la lista de proveedores no esté vacía si la respuesta es una
		// lista
		if (responseEntity.getBody() instanceof List<?>) {
			List<?> proveedores = (List<?>) responseEntity.getBody();
			assertFalse(proveedores.isEmpty(), "La lista de proveedores no debería estar vacía");
		}
	}

	/**
	 * Prueba para verificar que la lista de proveedores devuelta por el método
	 * mostrartodos() del controlador contenga un proveedor específico.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testMostrartodosContieneProveedorEspecifico() {
		// Llama al método "mostrartodos"
		ResponseEntity<?> responseEntity = proveedorRestController.mostrartodos();

		// Verifica que la lista de proveedores contenga un proveedor específico si la
		// respuesta es una lista
		List<Proveedor> proveedores = (List<Proveedor>) responseEntity.getBody();
		boolean contieneProveedorEspecifico = false;
		for (Proveedor proveedor : proveedores) {
			if (proveedor.getIdProveedor() == 1 || "ACME".equals(proveedor.getNombre())) {
				contieneProveedorEspecifico = true;
				break;
			}
		}
		assertTrue(contieneProveedorEspecifico, "La lista debe contener proveedores específicos");
	}
}
