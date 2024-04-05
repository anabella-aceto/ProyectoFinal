package restsofa.restcontroller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import restsofa.service.EstadoService;
import restsofa.service.PedidoService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedidos")

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

	@GetMapping({ "/todos" })
	public ResponseEntity<?> todos() {

		try {
			List<Pedido> lista = pedidoService.buscarTodosPedidos();

			List<PedidoDto> listaDto = new ArrayList<>();
			
			for (Pedido pedido : lista) {
				
	            PedidoDto pedidoDto = new PedidoDto();
				
				pedidoDto.setIdPedido(pedido.getIdPedido());
				pedidoDto.setIdCliente(pedido.getCliente().getIdCliente());
				pedidoDto.setIdEstado(pedido.getEstado().getIdEstado());
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

	@GetMapping("/uno/{idPedido}")
	public ResponseEntity<?> uno(@PathVariable int idPedido) {

		Pedido pedido = pedidoService.buscarPedido(idPedido);

		if (pedido != null) {
			
		    PedidoDto pedidoDto = new PedidoDto();		    

			pedidoDto.setIdPedido(pedido.getIdPedido());
			pedidoDto.setIdCliente(pedido.getCliente().getIdCliente());
			pedidoDto.setIdEstado(pedido.getEstado().getIdEstado());
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
		
		pedido.setIdPedido(pedidoDto.getIdPedido());
		pedido.setCliente(clienteService.buscarCliente(pedidoDto.getIdCliente()));
		pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getVendedor()));
		pedido.setEstado(estadoService.porDefecto("pendiente"));
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
			
			pedido.setCliente(clienteService.buscarCliente(pedidoDto.getIdCliente()));
			pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getVendedor()));
			pedido.setEstado(estadoService.buscarEstado(pedidoDto.getIdEstado()));
			pedido.setFecha(pedidoDto.getFecha());
			pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getIdCliente()));
			
			pedidoService.modifPedido(pedido);
			return ResponseEntity.status(200).body("Pedido modificado correctamente");
		} else
			return ResponseEntity.status(400).body("No se puede modificar el pedido");
	}

	/*
	 * Método que cancela un pedido
	 */

	@PutMapping("/cancelar/{idPedido}")//probado y funcionando
	
	public ResponseEntity<?> cancelarPedido(@PathVariable int idPedido) {
		
	    if (pedidoService.cancelarPedido(idPedido)) {
	    	
	    		    	
	        return ResponseEntity.status(200).body("Pedido cancelado");
	    } else {
	        return ResponseEntity.status(400).body("No se ha podido cancelar el pedido");
	    }
	}
		
	
	
	
	
	
	
	
	
	
	
//-------------------------------------------------------------------------------------------------------------
	@GetMapping("/porEstado/{idEstado}")//probado y funcionando
	public ResponseEntity<?> listarPorEstado(@PathVariable (name="idEstado") int idEstado){
		List<Pedido> lista = pedidoService.buscarPorEstado(idEstado);
		
		if(!lista.isEmpty()) 
			 return ResponseEntity.status(200).body(lista);
	    
		else 
	        return ResponseEntity.status(400).body("No se encuentran elementos con el estado indicado");
	    }
		
//-------------------------------------------------------------------------------------------------------------
	@GetMapping("/porFecha")
	public ResponseEntity<?> filtrarProFecha(@RequestParam (name="fechaInicio") Date fechaInicio,
											@RequestParam (name="fechaFin")  Date fechaFin) {
		
		List<Pedido> lista = pedidoService.filtrarPorFecha(fechaInicio, fechaFin);
		
		if(!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);
		else
			return ResponseEntity.status(400).body("No se encuentran elementos con el estado indicado");
	}
	
	
	
	
	
	
	
		
	}
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	


