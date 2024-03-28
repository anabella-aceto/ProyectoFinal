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

import restsofa.modelo.entities.EstadoPedido;
import restsofa.service.EstadoPedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estadopedido")

public class EstadoPedidoRestController {

	@Autowired
	private EstadoPedidoService estadoService;

	/*
	 * Método que devuelve todos los estados pedidos
	 */

	@GetMapping({ "", "/" })
	public ResponseEntity<?> todos() {
		try {
			List<EstadoPedido> lista = estadoService.buscarTodosEstadoPedidos();
			return ResponseEntity.status(200).body(lista);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Error al cargar la lista de estados de pedido");
		}
	}

	/*
	 * Método que devuelve un estado pedido
	 */

	@GetMapping("/{idEstado}")
	public ResponseEntity<?> uno(@PathVariable int idEstado) {

		EstadoPedido estado = estadoService.buscarEstadoPedido(idEstado);

		if (estado != null) {

			return ResponseEntity.status(200).body(estado);
		} else
			return ResponseEntity.status(400).body("Error, no se ha podido encontrar el estado del pedido");
	}

	/*
	 * Método que da de alta un estado pedido
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody EstadoPedido estado) {

		EstadoPedido estadoAlta = new EstadoPedido();

		estadoAlta.setNombre(estado.getNombre());

		if (estadoService.altaEstadoPedido(estado) != null) {
			return ResponseEntity.status(200).body("Estado de pedido procesado correctamente" + estadoAlta);
		} else
			return ResponseEntity.status(400).body("Error al procesar el estado de pedido");
	}

	/*
	 * Método que modifica un estado pedido
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody EstadoPedido estado) {

		EstadoPedido estadoModif = estadoService.buscarEstadoPedido(estado.getIdEstado());

		if (estadoModif != null) {
			estadoModif.setNombre(estado.getNombre());

			estadoService.modifEstadoPedido(estado);
			return ResponseEntity.status(200).body("Estado de pedido modificado correctamente");
		} else
			return ResponseEntity.status(400).body("No se puede modificar el estado de pedido");

	}

	/*
	 * Método que borra un estado pedido
	 */

	@DeleteMapping("/borrar/{idEstado}")
	public ResponseEntity<?> borrar(@PathVariable int idEstado) {

		EstadoPedido estadoBorrar = estadoService.buscarEstadoPedido(idEstado);

		if (estadoBorrar != null) {
			estadoService.borrarEstadoPedido(idEstado);
			return ResponseEntity.status(200).body("Estado de pedido eliminado correctamente");
		} else
			return ResponseEntity.status(400).body("Estado Pedido no se ha podido eliminar");
	}

}
