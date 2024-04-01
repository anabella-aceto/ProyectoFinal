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

import restsofa.modelo.DTO.SofaMaterialDto;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.service.MaterialService;
import restsofa.service.SofaMaterialService;
import restsofa.service.SofaService;

@RestController
@RequestMapping("/sofaMaterial")
@CrossOrigin(origins="*")
public class SofaMaterialRestController {
	
	@Autowired
	private SofaMaterialService sofaMaterialService;
	
	@Autowired
	private SofaService sofaService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private ModelMapper modelMapper;
	
//---------------------------------------------------------------------------------------------------------------
	@GetMapping("/todos")//probado y funcionando
	public ResponseEntity<?> listarTodos(){
		
		List<SofaMaterial> lista = sofaMaterialService.todos();
		
		if(lista != null)
			return ResponseEntity.status(200).body(lista);
		
		else
			return ResponseEntity.status(400).body("Error al cargar la lista");
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/porSofa/{idSofa}")//probado y funcionando
	
	public ResponseEntity<?> buscarPorSofa(@PathVariable ("idSofa") int idSofa){
		
		List<SofaMaterial> sofaMaterial = sofaMaterialService.buscarPorSofa(idSofa);
		
		if(sofaMaterial != null)
			return ResponseEntity.status(200).body(sofaMaterial);
		
		else
			return ResponseEntity.status(400).body("Error al cargar sofá");	
	
	}	
	
//-----------------------------------------------------------------------------------------------------------------
	@PostMapping("/alta")//probado y funcionando
	
	public ResponseEntity<?> altaSofaMaterial(@RequestBody SofaMaterialDto sofaMaterialDto){
		
		SofaMaterial sofaMaterial = new SofaMaterial();
		
		modelMapper.map(sofaMaterialDto, sofaMaterial);
		
		sofaMaterial.setSofa(sofaService.buscarSofa(sofaMaterialDto.getIdSofa()));
		sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
		sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());
		
		if(sofaMaterialService.insertOne(sofaMaterial)!=null)
			return ResponseEntity.status(200).body("Material ingresado correctamente " +sofaMaterial);
		
		else 
			return ResponseEntity.status(400).body("Error al cargar sofá");	
	
	
	}	
	
//-----------------------------------------------------------------------------------------------------------------	
	
	@PutMapping("/modificar")//probado y funcionando
	
	public ResponseEntity<?> modificarSofaMaterial(@RequestBody SofaMaterialDto sofaMaterialDto){
		
		SofaMaterial sofaMaterial = (SofaMaterial) sofaMaterialService.buscarPorSofaAndmaterial(sofaMaterialDto.getIdSofa(), sofaMaterialDto.getIdMaterial());
		
		
		if (sofaMaterial != null) {
			sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
			sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());
			sofaMaterialService.updateOne(sofaMaterial);
			
			return ResponseEntity.status(200).body("Material modificado correctamente " +sofaMaterial);
			
		}
		
		else
			return ResponseEntity.status(400).body("No se ha podido modificar el material");	
		
	}
//--------------------------------------------------------------------------------------------------------------		
	@DeleteMapping("/eliminar/{idSofa}/{idMaterial}")//probado y funcionando
	
	public ResponseEntity<?> eliminarSofaMaterial(@PathVariable ("idSofa") int idSofa, @PathVariable ("idMaterial") int idMaterial){
		
		SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(idSofa, idMaterial);
		
		
		 if (sofaMaterial != null) {
		        sofaMaterialService.deleteOne(sofaMaterial) ;
		        return ResponseEntity.status(200).body("Eliminación exitosa");
		    } 
		else 
			return ResponseEntity.status(400).body("No se puede eliminar el material");	
	}
	
	
	
	
	
	


}
