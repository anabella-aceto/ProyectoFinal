package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.restcontroller.ClienteRestController;

@SpringBootTest
public class ClienteRestControllerTestVerUno {

	@Autowired
	private ClienteRestController clienteRestController;

	@Test
	public void testUno() {
		int clientId = 3;
		ResponseEntity<?> responseEntity = clienteRestController.uno(clientId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Cliente cliente = (Cliente) responseEntity.getBody();

		// Verifica que el cliente no sea nulo
		assertNotNull(cliente, "El cliente no debería ser nulo");

		// Verifica que el cliente tenga el idCliente correcto
		assertEquals(clientId, cliente.getIdCliente(), "El idCliente no coincide");

		// Verifica si el nombre del cliente es correcto
		assertEquals("Carlos", cliente.getNombre(), "El nombre del cliente no coincide");
	}

}
