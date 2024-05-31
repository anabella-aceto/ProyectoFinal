package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import restsofa.modelo.entities.SofaMaterial;
import restsofa.service.SofaMaterialService;
import restsofa.service.SofaService;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Controlador para la gestión de un sofá.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sofa")

public class SofaRestController {

	@Autowired
	private SofaService sofaService;

	@Autowired
	private SofaMaterialService sofaMaterialService;

	
	/**
	 * Método que devuelve todos los sofás.
	 *
	 * @return ResponseEntity con la lista de sofás si se pudo cargar correctamente,
	 *         o un mensaje de error si no se cargó.
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> todos() {
		try {
			List<Sofa> lista = sofaService.buscarTodosSofas();
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al cargar la lista de sofás: " + e.getMessage());
		}
	}

	/**
	 * Método que busca un sofá por su identificador.
	 *
	 * @param idSofa El identificador único del sofá que se desea buscar.
	 * 
	 * @return ResponseEntity con el sofá si existe, o un mensaje de error si no
	 *         existe.
	 */
	@GetMapping("/uno/{idSofa}")
	public ResponseEntity<?> uno(@PathVariable int idSofa) {
		try {
			Sofa sofa = sofaService.buscarSofa(idSofa);
			if (sofa != null) {
				return ResponseEntity.status(HttpStatus.OK).body(sofa);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el sofá");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que permite dar de alta un sofá.
	 * 
	 * @param sofa El sofá a dar de alta.
	 * 
	 * @return ResponseEntity con el sofá dado de alta si el proceso fue exitoso, o
	 *         un mensaje de error si no.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Sofa sofa) {
		try {
			Sofa sofaGuardado = sofaService.altaSofa(sofa);
			if (sofaGuardado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(sofaGuardado);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al cargar el sofá en la BBDD");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al dar de alta el sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que modifica los datos de un sofá.
	 * 
	 * @param sofa El sofá con la información actualizada.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody Sofa sofa) {
		try {
			if (sofaService.buscarSofa(sofa.getIdSofa()) != null) {
				sofaService.modifSofa(sofa);
				return ResponseEntity.status(HttpStatus.OK).body("Modificación de sofá realizada correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar el sofá en la BBDD");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al modificar el sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que elimina un sofá.
	 * 
	 * @param idSofa El identificador único del sofá.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/borrar/{idSofa}")
	public ResponseEntity<?> borrar(@PathVariable int idSofa) {
		try {
			Sofa sofa = sofaService.buscarSofa(idSofa);
			List<SofaMaterial> lista = sofaMaterialService.buscarPorSofa(idSofa);
			
			if (sofa != null) {
				for (SofaMaterial sm : lista) {
					sofaMaterialService.deleteOne(sm);
					}
				sofaService.borrarSofa(idSofa);
				return ResponseEntity.status(HttpStatus.OK).body("Sofá eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El sofá a eliminar no existe");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el sofá: " + e.getMessage());
		}
	}
}