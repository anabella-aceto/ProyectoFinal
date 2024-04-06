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

/**
 * Controlador para la gestión de un sofá.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sofa")

public class SofaRestController {

	@Autowired
	private SofaService sofaService;

	/*
	 * Método que devuelve todos los sofás .
	 *
	 * @return ResponseEntity con la lista de sofás si se pudo cargar correctamente,
	 * o un mensaje de error si no se cargó.
	 */

	@GetMapping({ "", "/" })
	public ResponseEntity<?> todos() {

		List<Sofa> lista = sofaService.buscarTodosSofas();

		if (lista != null)

			return ResponseEntity.status(200).body(lista);
		else
			return ResponseEntity.status(400).body("Error al cargar la lista de sofás");
	}

	/**
	 * Método que busca un sofá por su identificador.
	 *
	 * @param idSofa El identificador único del sofá que se desea buscar.
	 * @return ResponseEntity con el sofá si existe, o un mensaje de error si no
	 *         existe.
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
	 * Método que permite dar de alta un sofá.
	 * 
	 * @param sofa El sofa a dar de alta.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * alta.
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Sofa sofa) {

		if (sofaService.altaSofa(sofa) != null)
			return ResponseEntity.status(200).body(sofa);
		else
			return ResponseEntity.status(400).body("Error al cargar el sofá en la BBDD");
	}

	/*
	 * Método que modifica los datos de un sofa.
	 * 
	 * @param sofa El sofa con la información actualizada.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * modificación.
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
	 * Método que elimina un sofá.
	 * 
	 * @param idSofa. El identificador único del sofá.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 * eliminación.
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
