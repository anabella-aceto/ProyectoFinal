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

import restsofa.modelo.DTO.CarpinteriaDto;
import restsofa.modelo.DTO.EnfundadoDto;
import restsofa.modelo.entities.Carpinteria;
import restsofa.modelo.entities.Enfundado;
import restsofa.modelo.entities.EstadoPedido;
import restsofa.modelo.entities.Pedido;
import restsofa.service.EnfundadoService;
import restsofa.service.EstadoPedidoService;
import restsofa.service.PedidoService;

@RestController
@RequestMapping("/enfundado")
@CrossOrigin(origins="*")
public class EnfundadoRestController {
	
	@Autowired
	private EnfundadoService enfundadoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@PostMapping("/alta")//probado y funcionando
	public ResponseEntity<?> ingresarEnfundado(@RequestBody EnfundadoDto enfundadoDto){
		
		Enfundado enfundado = modelMapper.map(enfundadoDto, Enfundado.class);
		
		Enfundado enfNuevo = enfundadoService.insertOne(enfundado);
		
		if (enfNuevo != null) {
			enfundadoDto.setIdEnfundado(enfundado.getIdEnfundado());
			enfundado.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(enfundadoDto.getIdEstadoPedido()));
			enfundado.setPedido(pedidoService.buscarPedido(enfundadoDto.getIdPedido()));
			enfundado.setFecha(enfundadoDto.getFecha());
			
			return ResponseEntity.status(200).body(enfNuevo);
			
		}
		else 
			return ResponseEntity.status(400).body("Error al grabar datos");
		
	}
	
//------------------------------------------------------------------------------------------------------------------------------
	@PutMapping("modificar")//probado y funcionando
	
	public ResponseEntity<?> modificarEnfundado (@RequestBody EnfundadoDto enfundadoDto){
		
		Enfundado enfundado = enfundadoService.buscarUno(enfundadoDto.getIdEnfundado());
		
		if (enfundado != null) {
			modelMapper.map(enfundadoDto, Enfundado.class);
			enfundadoDto.setIdEnfundado(enfundado.getIdEnfundado());
			enfundado.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(enfundadoDto.getIdEstadoPedido()));
			enfundado.setPedido(pedidoService.buscarPedido(enfundadoDto.getIdPedido()));
			enfundado.setFecha(enfundadoDto.getFecha());
			enfundadoService.updateOne(enfundado);
			
			return ResponseEntity.status(200).body("Modificación exitosa " +enfundado);
		}
		
		return ResponseEntity.status(400).body("No se han podido registrar las modificaciones");
		
	}
//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/eliminar/{idEnfundado}")
	public ResponseEntity<?> eleminarCarpinteria(@PathVariable ("idEnfundado") int idEnfundado){
		
		Enfundado enfundado = enfundadoService.buscarUno(idEnfundado);
		
		if(enfundado != null) {
			enfundadoService.deleteOne(idEnfundado);
			return ResponseEntity.status(200).body("Eliminación exitosa");
		}
		
		return ResponseEntity.status(400).body("No se puede eliminar");
	}
	
//-----------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/porPedido/{idPedido}")//probado y funcionando
	
	public ResponseEntity<?> buscarPorIdPedido(@PathVariable ("idPedido") int idPedido){
		
		Pedido pedido = pedidoService.buscarPedido(idPedido);
		List<EnfundadoDto> listaDto = new ArrayList<>();
		
		if (pedido != null){
			List<Enfundado> lista = enfundadoService.buscarPorPedido(idPedido);
			
			for(Enfundado enfundado : lista) {
				listaDto.add(modelMapper.map(enfundado, EnfundadoDto.class));
			}
			return ResponseEntity.status(200).body(listaDto);
		}
		
		else		
		return ResponseEntity.status(400).body("No se encuentra el objeto con el identificador ingresado");
	}
	
//-------------------------------------------------------------------------------------------------------------------------------	
	
@GetMapping("/porEstado/{idEstado}")//probado y funcionando
	
	public ResponseEntity<?> buscarPorEstado(@PathVariable("idEstado") int idEstado){
	
		EstadoPedido estadoPedido = estadoPedidoService.buscarEstadoPedido(idEstado);
		
		List<EnfundadoDto> listaDto= new ArrayList<>();
		
		if(estadoPedido != null) {
			List<Enfundado> lista= enfundadoService.buscarPorEstado(idEstado);	
			
			for (Enfundado enfundado : lista) {
				listaDto.add(modelMapper.map(enfundado, EnfundadoDto.class));
			}	
			
			return ResponseEntity.status(200).body(listaDto);
		}
		
		else
			return ResponseEntity.status(400).body("No se encuentra el objeto con el identificador ingresado");
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
