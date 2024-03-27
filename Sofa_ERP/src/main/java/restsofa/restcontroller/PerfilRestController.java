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

import restsofa.modelo.entities.Perfil;
import restsofa.service.PerfilService;

@RestController
@RequestMapping("/perfiles")
@CrossOrigin(origins="*")
public class PerfilRestController {
	
	@Autowired
	private PerfilService perfilService;
	
	
	@GetMapping("/todos")//probado y funcionando
	public ResponseEntity<?> listarTodos(){
		
		List<Perfil> lista = perfilService.buscarTodos();
		
		if(!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);
		
		else if (lista.isEmpty())
			return ResponseEntity.status(200).body("La lista está vacía");
		
		else
			return ResponseEntity.status(400).body("Error al cargar la lista");
		
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	@GetMapping("/uno/{idPerfil}")//probado y funcionando
	public ResponseEntity<?> mostrarUno(@PathVariable ("idPerfil") int idPerfil){
		
		Perfil perfil = perfilService.buscarUno(idPerfil);
		
		if(perfil != null)
			return ResponseEntity.status(200).body(perfil);
		
		else
			return ResponseEntity.status(400).body("Error al cargar el empleado");
		
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	@PostMapping("/alta")//probado y funcionando
	public ResponseEntity<?> agregarPerfil(@RequestBody Perfil perfil){
		
		if(perfilService.insertOne(perfil)!= null)
			return ResponseEntity.status(200).body(perfil);
		
		
		return ResponseEntity.status(400).body("Error al insertar perfil");
		
	}
//--------------------------------------------------------------------------------------------------------------------------------
	@PutMapping("/modificar")//probado y funcionando
	public ResponseEntity<?> modificarPerfil(@RequestBody Perfil perfil){
		
		if(perfilService.buscarUno(perfil.getIdPerfil())!= null) {
			perfilService.insertOne(perfil);
			return ResponseEntity.status(200).body(perfil);
		}
		
		
		return ResponseEntity.status(400).body("Error al cargar el empleado");
	}
//-------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/eliminar/{idPerfil}")//probado y funcionando
	
	public ResponseEntity<?> eliminarPerfil(@PathVariable ("idPerfil") int idPerfil){

		if(perfilService.buscarUno(idPerfil) != null) {
			perfilService.deleteOne(idPerfil);
			return ResponseEntity.status(200).body("Perfil eliminado correctamente");		
		}
		return ResponseEntity.status(400).body("No se puede eliminar el perfil");
		}


//-------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	

	

}
