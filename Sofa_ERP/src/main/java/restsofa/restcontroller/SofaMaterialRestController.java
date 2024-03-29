package restsofa.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.SofaMaterial;
import restsofa.service.SofaMaterialService;

@RestController
@RequestMapping("/sofaMaterial")
@CrossOrigin(origins="*")
public class SofaMaterialRestController {
	
	@Autowired
	private SofaMaterialService sofaMaterialService;
	
	@GetMapping("/todos")
	public ResponseEntity<?> listarTodos(){
		
		List<SofaMaterial> lista = sofaMaterialService.todos();
		
		if(lista != null)
			return ResponseEntity.status(200).body(lista);
		
		else
			return ResponseEntity.status(400).body("Error al cargar la lista");
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/porSofa")
	
	public ResponseEntity<?> buscarPorSofa(@PathVariable ("idSofa") int idSofa){
		
		SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofa(idSofa);
		
		if(sofaMaterial != null)
			return ResponseEntity.status(200).body(sofaMaterial);
		
		else
			return ResponseEntity.status(400).body("Error al cargar sofá");	
	
	}	
	
	
	
	
	
	
	
	
	
	
	
}


