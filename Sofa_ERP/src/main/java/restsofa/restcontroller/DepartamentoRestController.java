package restsofa.restcontroller;

import java.util.List;

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

import restsofa.modelo.entities.Departamento;
import restsofa.service.DepartamentoService;

@RestController
@RequestMapping("/departamento")
@CrossOrigin(origins="*")
public class DepartamentoRestController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	
	@GetMapping("/todos")//probado y funcionando
	public ResponseEntity<?> listarTodos (){
		
		List<Departamento> lista =  departamentoService.listarTodos();
		
		if(!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);
		
		else
			return ResponseEntity.status(500).body("NO hay elementos en la lista");
	}
	
//------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/uno/{idDepartamento}")//probado y funcionando
	public ResponseEntity<?> buscarUno(@PathVariable ("idDepartamento") int idDepartamento){
		
		Departamento departamento = departamentoService.buscarUno(idDepartamento);
		if(departamento != null)
			return ResponseEntity.status(200).body(departamento);
		
		
		else
			return ResponseEntity.status(500).body("NO hay elementos en la lista");
	}
	
//------------------------------------------------------------------------------------------------------------------------	
	@PostMapping("/alta")//probado y funcionando
	public ResponseEntity<?> buscarUno(@RequestBody Departamento departamento){
		
		if(departamentoService.insertOne(departamento) != null)
			return ResponseEntity.status(200).body(departamento);
		
		else
			return ResponseEntity.status(500).body("NO se puede insertar el departamento");
	}
	
//--------------------------------------------------------------------------------------------------------------	
	@PutMapping("/modificar")//probado y funcionando
	public ResponseEntity<?> modificarDepto(@RequestBody Departamento departamento){
		
		if(departamentoService.buscarUno(departamento.getIdDepartamento())!=null) {
			departamentoService.updateOne(departamento);
			return ResponseEntity.status(200).body("Modificación exitosa " +departamento);
		}
		
		else
			return ResponseEntity.status(500).body("Error al modificar el departamento");
		
	}	
	
//--------------------------------------------------------------------------------------------------------------	
	@DeleteMapping("/eliminar/{idDepartamento}")//probado y funcionando	
	public ResponseEntity<?> eliminarDepto(@PathVariable ("idDepartamento") int idDepartamento){
		
		if(departamentoService.buscarUno(idDepartamento)!=null) {
			departamentoService.deleteOne(idDepartamento);
			return ResponseEntity.status(200).body("Departamento eliminado correctamente");
			
		}
		
		else
			return ResponseEntity.status(500).body("Error al modificar el departamento");
		
		
	}
}
	
	

