package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Cliente;
import restsofa.service.ClienteService;

/**
 * Controlador para la gestión de clientes.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")

public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	/*
	 * Método que devuelve todos los clientes.
	 * 
	 * @return ResponseEntity con la lista de todos los clientes si se obtienen
	 * correctamente, o un mensaje de error si la lista está vacía.
	 */

	@GetMapping({ "/todos" }) // probado y funcionando
	public ResponseEntity<?> todos() {

		List<Cliente> lista = clienteService.buscarTodosClientes();

		if (lista != null)
			return ResponseEntity.status(200).body(lista);

		else
			return ResponseEntity.status(400).body("Error al cargar la lista");
	}

	/*
	 * Método que devuelve un cliente.
	 * 
	 * @param idCliente. El identificador único del cliente.
	 * 
	 * @return ResponseEntity con el cliente si se obtiene correctamente, o un
	 * mensaje de error si no existe.
	 */

	@GetMapping("/uno/{idCliente}") // probado y funcionando
	public ResponseEntity<?> uno(@PathVariable int idCliente) {

		Cliente cliente = clienteService.buscarCliente(idCliente);

		if (cliente != null)
			return ResponseEntity.status(200).body(cliente);

		else
			return ResponseEntity.status(400).body("No se encuentra el cliente");

	}

	/*
	 * Método que da de alta un cliente.
	 * 
	 * @param Cliente. Los datos del cliente.
	 * 
	 * @return ResponseEntity con el cliente creado si se realizó correctamente, o
	 * un mensaje de error si no se ha relaizado el alta.
	 */

	@PostMapping("/alta") // probado y funcionando
	public ResponseEntity<?> alta(@RequestBody Cliente cliente) {

		if (clienteService.altaCliente(cliente) != null)
			return ResponseEntity.status(200).body(cliente);

		else
			return ResponseEntity.status(400).body("Error al cargar cliente en la base de datos");
	}

	/*
	 * Método que modifica un cliente.
	 * 
	 * @param Cliente. Los datos del cliente.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 * modificación.
	 */

	@PutMapping("/modificar") // probado y funcionando
	public ResponseEntity<?> modificar(@RequestBody Cliente cliente) {

		if (clienteService.buscarCliente(cliente.getIdCliente()) != null) {
			clienteService.modifCliente(cliente);
			return ResponseEntity.status(200).body("Modificación realizada correctamente " + cliente);
		} else
			return ResponseEntity.status(400).body("Error al modificar cliente en la base de datos");
	}

	/*
	 * Método que elimina un cliente.
	 * 
	 * @param idCliente. El identificador único del cliente.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 * eliminación.
	 */

	@DeleteMapping("/eliminar/{idCliente}") // probado y funcionando
	public ResponseEntity<?> borrar(@PathVariable int idCliente) {

		Cliente cliente = clienteService.buscarCliente(idCliente);

		if (cliente != null) {
			clienteService.borrarCliente(idCliente);
			return ResponseEntity.status(200).body("Eliminación realizada correctamente");
		} else
			return ResponseEntity.status(400).body("Error al eliminar cliente en la base de datos");
	}

}
