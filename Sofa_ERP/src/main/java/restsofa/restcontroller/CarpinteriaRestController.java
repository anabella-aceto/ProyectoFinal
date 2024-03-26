package restsofa.restcontroller;

import java.util.Date;

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

import restsofa.modelo.DTO.CarpDto;
import restsofa.modelo.DTO.CarpinteriaDto;
import restsofa.modelo.entities.Carpinteria;
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
	
		
	@PostMapping("/alta")
	public ResponseEntity<?> ingresarPedido(@RequestBody CarpinteriaDto carpinteriaDto){
		
		    Carpinteria carpinteria = new Carpinteria();
		    modelMapper.map(carpinteriaDto, carpinteria);
		    		    
	    	carpinteria.setPedido(pedidoService.buscarPedido(carpinteriaDto.getIdPedido()));
	        carpinteria.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(carpinteriaDto.getIdEstadoPedido()));
	        carpinteria.setFecha(new Date());   
	        
	        if(carpinteriaService.insertOne(carpinteria)!=null)
	        	return ResponseEntity.status(200).body("Alta correcta: " + carpinteria);
		    
	
		    else {
		        return ResponseEntity.status(400).body("No se puede dar de alta");
		    }
		}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/borrar/{idCarpinteria}")
	public ResponseEntity<?> eleminarCarpinteria(@PathVariable ("idCarpinteria") int idcarpinteria){
		
		Carpinteria carpinteria = carpinteriaService.buscarUno(idcarpinteria);
		
		if(carpinteria != null) {
			carpinteriaService.deleteOne(idcarpinteria);
			return ResponseEntity.status(200).body("Eliminado correctamente");
		}
		
		return ResponseEntity.status(400).body("No se puede eliminar");
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	@PutMapping("/modificar")
	public ResponseEntity<?> modificarCarpinteria(@RequestBody CarpDto carpDto){
		
		Carpinteria carpinteria = carpinteriaService.buscarUno(carpDto.getIdCarpinteria());
		
		if(carpinteria != null) {
			modelMapper.map(carpDto, carpinteria);
			carpinteriaService.updateOne(carpinteria);
			return ResponseEntity.status(200).body("Modificación exitosa " +carpinteria);
		}
		
		return ResponseEntity.status(400).body("No se puede modificar");	
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/buscarUno")	
	
	public ResponseEntity<?> buscarPorIdCarpinteria(@PathVariable ("idCarpinteria") int idCarpinteria){
		
		Carpinteria carpinteria = carpinteriaService.buscarUno(idCarpinteria);
		if(carpinteria != null)
			return ResponseEntity.status(200).body(carpinteria);
		
		return ResponseEntity.status(400).body("No se encuentra el objeto con el identificador ingresado");
	}
	
	
	
	
	
	
	
	
	
	
	
}
