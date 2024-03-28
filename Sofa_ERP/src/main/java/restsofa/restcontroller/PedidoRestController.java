package restsofa.restcontroller;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import restsofa.modelo.DTO.PedidoDto;
import restsofa.modelo.entities.Pedido;
import restsofa.service.ClienteService;
import restsofa.service.EmpleadoService;
import restsofa.service.PedidoService;
import restsofa.service.SofaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")

public class PedidoRestController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SofaService sofaService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
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
	public ResponseEntity<?> alta(@RequestBody PedidoDto pedidoDto) {
		
		Pedido pedido = new Pedido();
		modelMapper.map(pedidoDto, pedido);
		
		pedido.setSofa(sofaService.buscarSofa(pedidoDto.getIdSofa()));
		pedido.setCliente(clienteService.buscarCliente(pedidoDto.getIdCliente()));
		pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getIdEmpleado()));
		
		if(pedidoService.altaPedido(pedido) != null) {
			pedidoDto.setIdPedido(pedido.getIdPedido());
			return ResponseEntity.status(200).body("Pedido procesado correctamente "+pedido);
		}
		else
			return ResponseEntity.status(400).body("Error al procesar el pedido");
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
