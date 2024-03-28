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
import restsofa.modelo.entities.Carpinteria;
import restsofa.modelo.entities.EstadoPedido;
import restsofa.modelo.entities.Pedido;
import restsofa.service.CarpinteriaService;
import restsofa.service.EstadoPedidoService;
import restsofa.service.PedidoService;


@RestController
@RequestMapping("/carpinteria")
@CrossOrigin(origins="*")
public class CarpinteriaRestController {
	
	@Autowired
	private CarpinteriaService carpinteriaService;
		
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
		
	@PostMapping("/alta")//probado y funcionando
	public ResponseEntity<?> ingresarPedido(@RequestBody CarpinteriaDto carpinteriaDto){
		
		    Carpinteria carpinteria = modelMapper.map(carpinteriaDto, Carpinteria.class); 
		   
		    Carpinteria estadoNuevo = carpinteriaService.insertOne(carpinteria);
		    
		    if(estadoNuevo != null) {
		    	carpinteriaDto.setIdCarpinteria(carpinteria.getIdCarpinteria());
		    	carpinteria.setPedido(pedidoService.buscarPedido(carpinteriaDto.getIdPedido()));
		    	carpinteria.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(carpinteriaDto.getIdEstado()));
		        carpinteria.setFecha(carpinteriaDto.getFecha());
		        return ResponseEntity.status(200).body(estadoNuevo);
		    }
	    	
		    else 
		        return ResponseEntity.status(400).body("No se puede dar de alta");
		    
		}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/eliminar/{idCarpinteria}")//probado y funcionnado
	public ResponseEntity<?> eleminarCarpinteria(@PathVariable ("idCarpinteria") int idcarpinteria){
		
		Carpinteria carpinteria = carpinteriaService.buscarUno(idcarpinteria);
		
		if(carpinteria != null) {
			carpinteriaService.deleteOne(idcarpinteria);
			return ResponseEntity.status(200).body("Eliminación exitosa");
		} else
		
		return ResponseEntity.status(400).body("No se puede eliminar");
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	@PutMapping("/modificar")//probado y funcionando
	public ResponseEntity<?> modificarCarpinteria(@RequestBody CarpinteriaDto carpinteriaDto){
		
		Carpinteria carpinteria = carpinteriaService.buscarUno(carpinteriaDto.getIdCarpinteria());
		
		if(carpinteria != null) {
			modelMapper.map(carpinteriaDto, Carpinteria.class);
			carpinteria.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(carpinteriaDto.getIdEstado()));
			carpinteria.setPedido(pedidoService.buscarPedido(carpinteriaDto.getIdPedido()));
			carpinteria.setFecha(carpinteriaDto.getFecha());
		
			carpinteriaService.updateOne(carpinteria);
			return ResponseEntity.status(200).body("Modificación exitosa " +carpinteria);
		} else
		
		return ResponseEntity.status(400).body("No se puede modificar");	
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/porPedido/{idPedido}")//probado y funcionando
	
	public ResponseEntity<?> buscarPorIdPedido(@PathVariable ("idPedido") int idPedido){
		
		Pedido pedido = pedidoService.buscarPedido(idPedido);
		
		List<CarpinteriaDto> listaDto= new ArrayList<>();
		
		if (pedido != null){
			List<Carpinteria> lista = carpinteriaService.buscarPorPedido(idPedido);
			for (Carpinteria carpinteria : lista) {
				listaDto.add(modelMapper.map(carpinteria, CarpinteriaDto.class));
			}	
			
			return ResponseEntity.status(200).body(listaDto);
		
		}
		
		else		
		return ResponseEntity.status(400).body("No se encuentra el objeto con el identificador ingresado");
	}
	
//----------------------------------------------------------------------------------------------------------------------
	@GetMapping("/porEstado/{idEstado}")//probado y funcionando
	
	public ResponseEntity<?> buscarPorEstado(@PathVariable("idEstado") int idEstado){
	
		EstadoPedido estadoPedido = estadoPedidoService.buscarEstadoPedido(idEstado);
		
		List<CarpinteriaDto> listaDto= new ArrayList<>();
		
		if(estadoPedido != null) {
			List<Carpinteria> lista= carpinteriaService.buscarPorEstado(idEstado);	
			
			for (Carpinteria carpinteria : lista) {
				listaDto.add(modelMapper.map(carpinteria, CarpinteriaDto.class));
			}	
			
			return ResponseEntity.status(200).body(listaDto);
		}
		
		else
			return ResponseEntity.status(400).body("No se encuentra el objeto con el identificador ingresado");
	
}
	
}
