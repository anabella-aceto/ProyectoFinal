package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Sofa;
import restsofa.service.SofaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sofa")

public class SofaController {
	
	@Autowired
	private SofaService sofaService;
	
	/*
	* Método que devuelve todos los sofas
	*/
	
	@GetMapping({ "", "/" })
	public List<Sofa> todos(){
		return sofaService.buscarTodosSofas();
	}
	
	/*
	* Método que devuelve un sofa
	*/
	
	@GetMapping("/{idSofa}")
	public Sofa uno(@PathVariable int idSofa) {
		return sofaService.buscarSofa(idSofa);
	}
	
	/*
	* Método que da de alta un sofa
	*/
	
	@PostMapping("/alta")
	public Sofa alta(@RequestBody Sofa sofa) {
		return sofaService.altaSofa(sofa);
	}
	
	/*
	* Método que modifica un sofa
	*/
	
	@PutMapping("/modificar")
	public Sofa modificar(@RequestBody Sofa sofa) {
		return sofaService.modifSofa(sofa);
	}
	
	/*
	 * Método que borra un sofa
	 */

	@DeleteMapping("/borrar/{idSofa}")
	public String borrar(@PathVariable int idSofa) {
		if (sofaService.borrarSofa(idSofa))
			return "Sofa eliminado correctamente";
		else
			return "Sofa no se ha podido eliminar";
	}	

}
