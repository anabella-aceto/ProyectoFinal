package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

public class EstadoPedidoController {

	@Autowired
	private EstadoPedidoService estadoPedidoService;

	/*
	 * Método que devuelve todos los estados pedidos
	 */

	@GetMapping({ "", "/" })
	public List<EstadoPedido> todos() {
		return estadoPedidoService.buscarTodosEstadoPedidos();
	}

	/*
	 * Método que devuelve un estado pedido
	 */

	@GetMapping("/{idEstado}")
	public EstadoPedido uno(@PathVariable int idEstado) {
		return estadoPedidoService.buscarEstadoPedido(idEstado);
	}

	/*
	 * Método que da de alta un estado pedido
	 */

	@PostMapping("/alta")
	public EstadoPedido alta(@RequestBody EstadoPedido estado) {
		return estadoPedidoService.altaEstadoPedido(estado);
	}

	/*
	 * Método que modifica un estado pedido
	 */

	@PutMapping("/modificar")
	public EstadoPedido modificar(@RequestBody EstadoPedido estado) {
		return estadoPedidoService.modifEstadoPedido(estado);
	}

	/*
	 * Método que borra un estado pedido
	 */

	@DeleteMapping("/borrar/{idEstado}")
	public String borrar(@PathVariable int idEstado) {
		if (estadoPedidoService.borrarEstadoPedido(idEstado))
			return "Estado Pedido eliminado correctamente";
		else
			return "Estado Pedido no se ha podido eliminar";
	}

}
