package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.restcontroller.ClienteRestController;

@SpringBootTest
public class ClienteRestControllerTestVerTodos {

	@Autowired
	private ClienteRestController clienteRestController;

	@Test
	public void testTodos() {
		ResponseEntity<?> responseEntity = clienteRestController.todos();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		List<Cliente> clientes = (List<Cliente>) responseEntity.getBody();

		// Verifica que la lista no esté vacía
		assertFalse(clientes.isEmpty(), "La lista de clientes no debería estar vacía");

		// Verifica si contiene clientes específicos
		boolean containsSpecificClient = clientes.stream()
				.anyMatch(cliente -> cliente.getIdCliente() == 4 || cliente.getNombre().equals("Laura"));

		assertTrue(containsSpecificClient, "La lista debe contener clientes");
	}

}
