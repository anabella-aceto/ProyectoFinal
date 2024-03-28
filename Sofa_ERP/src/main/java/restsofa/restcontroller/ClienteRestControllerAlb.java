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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")

public class ClienteRestControllerAlb {

	@Autowired
	private ClienteService clienteService;

	/*
	 * Método que devuelve todos los clientes
	 */

	@GetMapping({ "", "/" })
	public ResponseEntity<?> buscaTodosClientes() {

		List<Cliente> lista = clienteService.buscarTodosClientes();

		return ResponseEntity.status(200).body(lista);
	}

	/*
	 * Método que devuelve un cliente
	 */

	@GetMapping("/{idCliente}")
	public ResponseEntity<?> buscaUnCliente(@PathVariable int idCliente) {

		Cliente cliente = clienteService.buscarCliente(idCliente);

		if (cliente != null) {
			return ResponseEntity.status(200).body(cliente);
		} else
			return ResponseEntity.status(200).body("No hay clientes");

	}

	/*
	 * Método que da de alta un cliente
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente) {

		Cliente altaNueva = clienteService.altaCliente(cliente);

		if (altaNueva != null) {
			return ResponseEntity.status(200).body(altaNueva);
		} else
			return ResponseEntity.status(400).body("No se puede dar de alta el cliente");
	}

	/*
	 * Método que modifica un cliente
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificarCliente(@RequestBody Cliente cliente) {

		if (cliente != null) {
			clienteService.modifCliente(cliente);
			return ResponseEntity.status(200).body("Modificación de cliente realizada correctamente");
		} else
			return ResponseEntity.status(400).body("No se ha podido modificar el cliente");
	}

	/*
	 * Método que borra un cliente
	 */

	@DeleteMapping("/borrar/{idCliente}")
	public ResponseEntity<?> borrarCliente(@PathVariable int idCliente) {

		Cliente cliente = clienteService.buscarCliente(idCliente);

		if (cliente != null) {
			clienteService.borrarCliente(idCliente);
			return ResponseEntity.status(200).body("Cliente eliminado correctamente");
		} else
			return ResponseEntity.status(400).body("Cliente no se ha podido eliminar");
	}

}
