package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	/**
	 * Devuelve todos los clientes disponibles.
	 *
	 * @return ResponseEntity con la lista de todos los clientes si se obtienen
	 *         correctamente, o un mensaje si la lista está vacía.
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> todos() {
		try {
			// Buscar todos los clientes
			List<Cliente> lista = clienteService.buscarTodosClientes();

			// Verificar si la lista de clientes no está vacía
			if (!lista.isEmpty()) {
				// Si la lista no está vacía, devolver la lista de clientes con un estado OK
				return ResponseEntity.ok(lista);
			} else {
				// Si la lista está vacía, devolver un mensaje indicando que no se encontraron
				// clientes
				return ResponseEntity.ok("No se encontraron clientes");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener la lista de clientes: " + e.getMessage());
		}
	}

	/**
	 * Devuelve un cliente por su identificador único.
	 *
	 * @param idCliente El identificador único del cliente.
	 * @return ResponseEntity con el cliente si se obtiene correctamente, o un
	 *         mensaje de error si no se encuentra el cliente.
	 */
	@GetMapping("/uno/{idCliente}")
	public ResponseEntity<?> uno(@PathVariable int idCliente) {
		try {
			// Buscar el cliente por su identificador
			Cliente cliente = clienteService.buscarCliente(idCliente);

			// Verificar si se encontró el cliente
			if (cliente != null) {
				// Si se encontró el cliente, devolver el cliente con un estado OK
				return ResponseEntity.ok(cliente);
			} else {
				// Si no se encontró el cliente, devolver un mensaje de cliente no encontrado
				// con un estado NOT FOUND
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Cliente no encontrado con el ID: " + idCliente);
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener el cliente: " + e.getMessage());
		}
	}

	/**
	 * Da de alta un cliente con los datos proporcionados.
	 *
	 * @param cliente Los datos del cliente a dar de alta.
	 * @return ResponseEntity con el cliente creado si se realizó correctamente, o
	 *         un mensaje de error si no se ha realizado el alta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Cliente cliente) {
		try {
			// Intentar dar de alta el cliente
			Cliente clienteCreado = clienteService.altaCliente(cliente);

			// Verificar si se creó el cliente correctamente
			if (clienteCreado != null) {
				// Si se creó correctamente, devolver el cliente con un estado OK
				return ResponseEntity.ok(clienteCreado);
			} else {
				// Si no se pudo crear el cliente, devolver un mensaje de error con un estado
				// BAD REQUEST
				return ResponseEntity.badRequest().body("Error al cargar cliente en la base de datos");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Modifica los datos de un cliente existente.
	 *
	 * @param cliente Los nuevos datos del cliente.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody Cliente cliente) {
		try {
			// Buscar el cliente existente por su ID
			Cliente clienteExistente = clienteService.buscarCliente(cliente.getIdCliente());

			// Verificar si el cliente existe
			if (clienteExistente != null) {
				// Si el cliente existe, modificarlo
				clienteService.modifCliente(cliente);
				// Devolver un mensaje con el cliente modificado y un estado OK
				return ResponseEntity.status(HttpStatus.OK).body("Modificación realizada correctamente: " + cliente);
			} else {
				// Si el cliente no existe, devolver un mensaje de error con un estado NOT FOUND
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("El cliente con el ID " + cliente.getIdCliente() + " no existe");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Elimina un cliente existente.
	 *
	 * @param idCliente El identificador único del cliente a eliminar.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/eliminar/{idCliente}")
	public ResponseEntity<?> borrar(@PathVariable int idCliente) {
		try {
			// Buscar el cliente por su ID
			Cliente cliente = clienteService.buscarCliente(idCliente);

			// Verificar si el cliente existe
			if (cliente != null) {
				// Si el cliente existe, borrarlo
				clienteService.borrarCliente(idCliente);
				// Devolver un mensaje con un estado OK indicando que la eliminación se realizó
				// correctamente
				return ResponseEntity.status(HttpStatus.OK).body("Eliminación realizada correctamente");
			} else {
				// Si el cliente no existe, devolver un mensaje de error con un estado NOT FOUND
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("El cliente con el ID " + idCliente + " no existe");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

}
