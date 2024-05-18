package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.restcontroller.ClienteRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "todos" en
 *          ClienteRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `ClienteRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class ClienteRestControllerTestVerTodos {

	@Autowired
	private ClienteRestController clienteRestController;

	/**
	 * Prueba para verificar que la respuesta del método todos() del controlador no
	 * es nula.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaNoNula() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = clienteRestController.todos();

		// Verificar que la respuesta no sea nula
		assertNotNull(responseEntity);
	}

	/**
	 * Prueba para verificar el código de estado de la respuesta del método todos()
	 * del controlador.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosCodigoEstado() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = clienteRestController.todos();

		// Verificar el código de estado de la respuesta
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador no es nulo.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosCuerpoRespuestaNoNulo() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = clienteRestController.todos();

		// Verificar que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método todos() del
	 * controlador es una lista.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosRespuestaListaClientes() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = clienteRestController.todos();

		// Verificar que el cuerpo de la respuesta sea una lista de clientes
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de clientes devuelta por el método todos()
	 * del controlador no está vacía.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosListaNoVacia() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = clienteRestController.todos();

		// Verificar que la lista de clientes no esté vacía
		List<?> clientes = (List<?>) responseEntity.getBody();
		assertFalse(clientes.isEmpty(), "La lista de clientes no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de clientes contiene un cliente específico.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testTodosContieneClienteEspecifico() {
		// Llamar al método todos() del controlador
		ResponseEntity<?> responseEntity = clienteRestController.todos();

		// Verificar que la lista de clientes contenga un cliente específico
		List<Cliente> clientes = (List<Cliente>) responseEntity.getBody();
		boolean contieneClienteEspecifico = false;
		for (Cliente cliente : clientes) {
			if (cliente.getIdCliente() == 4 || cliente.getNombre().equals("Laura")) {
				contieneClienteEspecifico = true;
				break;
			}
		}
		assertTrue(contieneClienteEspecifico, "La lista debe contener clientes específicos");
	}
}
