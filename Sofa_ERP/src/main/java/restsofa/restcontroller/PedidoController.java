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

import restsofa.modelo.entities.Pedido;
import restsofa.service.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")

public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	/*
	* Método que devuelve todos los pedidos
	*/
	
	@GetMapping({ "", "/" })
	public List<Pedido> todos(){
		return pedidoService.buscarTodosPedidos();
	}
	
	/*
	* Método que devuelve un pedido
	*/
	
	@GetMapping("/{idPedido}")
	public Pedido uno(@PathVariable int idPedido) {
		return pedidoService.buscarPedido(idPedido);
	}
	
	/*
	* Método que da de alta un pedido
	*/
	
	@PostMapping("/alta")
	public Pedido alta(@RequestBody Pedido pedido) {
		return pedidoService.altaPedido(pedido);
	}
	
	/*
	* Método que modifica un pedido
	*/
	
	@PutMapping("/modificar")
	public Pedido modificar(@RequestBody Pedido pedido) {
		return pedidoService.modifPedido(pedido);
	}
	
	/*
	* Método que borra un pedido
	*/
	
	@DeleteMapping("/borrar/{idPedido}")
	public String borrar (@PathVariable int idPedido) {
		if(pedidoService.borrarPedido(idPedido))
			return "Pedido eliminado correctamente";
		else
			return "Pedido no se ha podido eliminar";
	}

}
