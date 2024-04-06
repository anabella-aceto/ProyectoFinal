package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.restcontroller.ClienteRestController;

@SpringBootTest
public class ClienteRestControllerTestModificar {

	@Autowired
	private ClienteRestController clienteRestController;

	@Test
	public void testModificar() {
		// Crea un cliente de ejemplo
		Cliente clienteExistente = new Cliente();
		clienteExistente.setIdCliente(9); // Establece un idCliente existente
		clienteExistente.setNombre("Carmelo"); // Establece el nombre del cliente

		ResponseEntity<?> responseEntity = clienteRestController.modificar(clienteExistente);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Modificación realizada correctamente"), "La modificación debería ser correcta");
	}
}
