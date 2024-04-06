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
public class ClienteRestControllerTestAlta {

	@Autowired
	private ClienteRestController clienteRestController;

	@Test
	public void testAlta() {
		// Crea un cliente de ejemplo
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setIdCliente(50);
		// Establece un idCliente
		nuevoCliente.setNombre("Elisa"); // Establece el nombre del cliente

		ResponseEntity<?> responseEntity = clienteRestController.alta(nuevoCliente);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Cliente clienteGuardado = (Cliente) responseEntity.getBody();

		// Verifica que el cliente guardado no sea nulo
		assertNotNull(clienteGuardado, "El cliente guardado no debería ser nulo");

		// Verifica que el cliente guardado tenga el idCliente correcto
		assertEquals(nuevoCliente.getIdCliente(), clienteGuardado.getIdCliente(), "El idCliente guardado no coincide");

		// Verifica si el nombre del cliente es correcto
		assertEquals("Elisa", clienteGuardado.getNombre(), "El nombre del cliente guardado no coincide");
	}

}
