package restsofa.restcontroller;

import java.util.ArrayList;
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
import restsofa.modelo.entities.Empleado;
import restsofa.modelo.entities.Pedido;
import restsofa.service.ClienteService;
import restsofa.service.EmpleadoService;
import restsofa.service.EstadoService;
import restsofa.service.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")

public class PedidoRestController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EstadoService estadoService;

	
	/*
	 * Método que devuelve todos los pedidos
	 */

	@GetMapping({ "", "/" })
	public ResponseEntity<?> todos() {

		try {
			List<Pedido> lista = pedidoService.buscarTodosPedidos();

			List<PedidoDto> listaDto = new ArrayList<>();
			
			for (Pedido pedido : lista) {
				
	            PedidoDto pedidoDto = new PedidoDto();
				
				pedidoDto.setIdPedido(pedido.getIdPedido());
				pedidoDto.setIdCliente(pedido.getCliente().getIdCliente());
				pedidoDto.setEstado(pedido.getEstado().getNombre());
				pedidoDto.setFecha(pedido.getFecha());
				pedidoDto.setVendedor(pedido.getVendedor().getIdEmpleado());
				
				listaDto.add(pedidoDto);
				
			}

			return ResponseEntity.ok(listaDto.isEmpty() ? "No hay pedidos disponibles" : listaDto);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Error al cargar la lista de pedidos");
		}
	}

	/*
	 * Método que devuelve un pedido
	 */

	@GetMapping("/{idPedido}")
	public ResponseEntity<?> uno(@PathVariable int idPedido) {

		Pedido pedido = pedidoService.buscarPedido(idPedido);

		if (pedido != null) {
			
		    PedidoDto pedidoDto = new PedidoDto();		    

			pedidoDto.setIdPedido(pedido.getIdPedido());
			pedidoDto.setIdCliente(pedido.getCliente().getIdCliente());
			pedidoDto.setEstado(pedido.getEstado().getNombre());
			pedidoDto.setFecha(pedido.getFecha());
			pedidoDto.setVendedor(pedido.getVendedor().getIdEmpleado());

			return ResponseEntity.status(200).body(pedidoDto);
		} else
			return ResponseEntity.status(400).body("Error, no se encuentra el pedido");
	}

	/*
	 * Método que da de alta un pedido
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody PedidoDto pedidoDto) {

		Pedido pedido = new Pedido();
		modelMapper.map(pedidoDto, pedido);
		
		Empleado vendedor = new Empleado();
		vendedor.setIdEmpleado(pedidoDto.getVendedor());
		pedido.setVendedor(vendedor);
		
		
		pedido.setCliente(clienteService.buscarCliente(pedidoDto.getIdCliente()));			
        pedido.setEstado(estadoService.buscarEstado(pedidoDto.getIdEstado()));
        pedido.setFecha(pedidoDto.getFecha());
      
        if (pedidoService.altaPedido(pedido) != null) {
			
			return ResponseEntity.status(200).body("Pedido procesado correctamente " + pedido);
		} else
			return ResponseEntity.status(400).body("Error al procesar el pedido");
	}

	/*
	 * Método que modifica un pedido
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody PedidoDto pedidoDto) {

		Pedido pedido = pedidoService.buscarPedido(pedidoDto.getIdPedido());

		if (pedido != null) {
			modelMapper.map(pedidoDto, PedidoDto.class);
			pedido.setCliente(clienteService.buscarCliente(pedidoDto.getIdCliente()));
			pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getVendedor()));
			
			pedidoService.modifPedido(pedido);
			return ResponseEntity.status(200).body("Pedido modificado correctamente");
		} else
			return ResponseEntity.status(400).body("No se puede modificar el pedido");
	}

	/*
	 * Método que borra un pedido
	 */

	@DeleteMapping("/borrar/{idPedido}")
	public ResponseEntity<?> borrar(@PathVariable int idPedido) {

		Pedido pedido = pedidoService.buscarPedido(idPedido);

		if (pedido != null) {
			pedidoService.borrarPedido(idPedido);
			return ResponseEntity.status(200).body("Pedido eliminado correctamente");
		} else
			return ResponseEntity.status(400).body("Pedido no se ha podido eliminar");
	}

}
