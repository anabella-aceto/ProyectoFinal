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

import restsofa.modelo.DTO.EnfundadoDto;
import restsofa.modelo.DTO.TapizadoDto;
import restsofa.modelo.entities.Enfundado;
import restsofa.modelo.entities.Tapizado;
import restsofa.service.EstadoPedidoService;
import restsofa.service.PedidoService;
import restsofa.service.TapizadoService;

@RestController
@RequestMapping("/tapizado")
@CrossOrigin(origins="*")
public class TapizadoRestController {
	
	@Autowired
	private TapizadoService tapizadoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@PostMapping("/alta")//probado y funcionando
	
	public ResponseEntity<?> ingresarTapizado(@RequestBody TapizadoDto tapizadoDto){
		
		Tapizado tapizado = new Tapizado();
		modelMapper.map(tapizadoDto, tapizado);
		
		tapizado.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(tapizadoDto.getIdEstado()));
		tapizado.setPedido(pedidoService.buscarPedido(tapizadoDto.getIdPedido()));
		
		if(tapizadoService.insertOne(tapizado) != null) 			
			return ResponseEntity.status(200).body(tapizado);
			
		else 
			return ResponseEntity.status(400).body("Error al grabar datos");
		
	}
//-------------------------------------------------------------------------------------------------------------------------------	
	@PutMapping("/modificar")//probado y funcionando
	public ResponseEntity<?> modificarTapizado(@RequestBody TapizadoDto tapizadoDto){
		
		Tapizado tapizado = tapizadoService.buscarUno(tapizadoDto.getIdTapizado());
		
		if(tapizado != null) {
			tapizadoDto.setIdTapizado(tapizado.getIdTapizado());
			tapizado.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(tapizadoDto.getIdEstado()));
			tapizado.setPedido(pedidoService.buscarPedido(tapizadoDto.getIdPedido()));
			tapizado.setFecha(tapizadoDto.getFecha());
			tapizadoService.updateOne(tapizado);
			
			return ResponseEntity.status(200).body("Modificación exitosa: " +tapizado);
			
		}
		else
			return ResponseEntity.status(400).body("Modificación no procesada");		
		
	}
	
//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/eliminar/{idTapizado}")//probado y funcionando
	public ResponseEntity<?> eliminarUno (@PathVariable ("idTapizado") int idTapizado){
	
	Tapizado tapizado = tapizadoService.buscarUno(idTapizado);
	
	if(tapizado != null) {
		tapizadoService.deleteOne(idTapizado);
		return ResponseEntity.status(200).body("Eliminación exitosa");
	}
	
	else
		return ResponseEntity.status(400).body("No se puede elemiminar el objeto");
}
//-----------------------------------------------------------------------------------------------------------------------------	
	@GetMapping("/porPedido/{idPedido}")//probado y funcionando
	public ResponseEntity<?> buscarPorPedido(@PathVariable ("idPedido") int idPedido ){
		
		if(pedidoService.buscarPedido(idPedido) != null) {
			List<Tapizado> lista = tapizadoService.buscarPorPedido(idPedido);
			return ResponseEntity.status(200).body(lista);
		}
		
		else
			return ResponseEntity.status(400).body("No existen objetos para el número de pedidos ingresado");
	}
	
//------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("porEstado/{idEstado}")//probado y funcionando
	public ResponseEntity<?> buscarPorestado (@PathVariable ("idEstado") int idEstado){
		
		if(estadoPedidoService.buscarEstadoPedido(idEstado) != null) {
			List<Tapizado> lista = tapizadoService.buscarPorEstado(idEstado);
			return ResponseEntity.status(200).body(lista);
		}
		
		else
			return ResponseEntity.status(400).body("No existen pedidos con el estado seleccionado");
	}
	
//-----------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/todos")//probado y funcionando
	public ResponseEntity<?> buscarTodos (){
		
		List<Tapizado> lista = tapizadoService.listarTodos();
		
		if (lista != null)
			return ResponseEntity.status(200).body(lista);
		
		else
			return ResponseEntity.status(400).body("Error al cargar la lista");
	}
	
	
	
	
	
	
	
	
	
	}

