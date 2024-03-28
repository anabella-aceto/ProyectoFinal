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
	public ResponseEntity<?> todos() {

		List<Sofa> lista = sofaService.buscarTodosSofas();

		if (lista != null)

			return ResponseEntity.status(200).body(lista);
		else
			return ResponseEntity.status(400).body("Error al cargar la lista de sofás");
	}

	/*
	 * Método que devuelve un sofa
	 */

	@GetMapping("/{idSofa}")
	public ResponseEntity<?> uno(@PathVariable int idSofa) {

		Sofa sofa = sofaService.buscarSofa(idSofa);

		if (sofa != null)
			return ResponseEntity.status(200).body(sofa);

		else
			return ResponseEntity.status(400).body("No se encuentra el sofá");

	}

	/*
	 * Método que da de alta un sofa
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Sofa sofa) {

		if (sofaService.altaSofa(sofa) != null)
			return ResponseEntity.status(200).body(sofa);
		else
			return ResponseEntity.status(400).body("Error al cargar el sofá en la BBDD");
	}

	/*
	 * Método que modifica un sofa
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody Sofa sofa) {

		if (sofaService.buscarSofa(sofa.getIdSofa()) != null) {
			sofaService.modifSofa(sofa);
			return ResponseEntity.status(200).body("Modificación de sofá realizada correctamente");
		} else
			return ResponseEntity.status(200).body("Error al modificar el sofá en la BBDD");
	}

	/*
	 * Método que borra un sofa
	 */

	@DeleteMapping("/borrar/{idSofa}")
	public ResponseEntity<?> borrar(@PathVariable int idSofa) {

		Sofa sofa = sofaService.buscarSofa(idSofa);

		if (sofa != null) {
			sofaService.borrarSofa(idSofa);
			return ResponseEntity.status(200).body("Sofá eliminado correctamente");
		} else
			return ResponseEntity.status(400).body("Error al eliminar el sofá en la BBDD");
	}

}
