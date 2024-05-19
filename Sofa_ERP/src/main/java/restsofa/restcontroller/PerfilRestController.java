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

import restsofa.modelo.entities.Perfil;
import restsofa.service.PerfilService;

/**
 * Controlador para la gestión de los perfiles.
 */

@RestController
@RequestMapping("/perfiles")
@CrossOrigin(origins = "*")
public class PerfilRestController {

	@Autowired
	private PerfilService perfilService;

	/**
	 * Método que devuelve todos los perfiles.
	 *
	 * @return ResponseEntity con la lista de perfiles si se pudo cargar
	 *         correctamente, o un mensaje de error si no se cargó.
	 */
	@GetMapping("/todos") // probado y funcionando
	public ResponseEntity<?> todos() {
		try {
			List<Perfil> lista = perfilService.buscarTodos();

			if (!lista.isEmpty())
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			else
				return ResponseEntity.status(HttpStatus.OK).body("La lista está vacía");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al cargar la lista: " + e.getMessage());
		}
	}

	/**
	 * Método que permite obtener un perfil por su identificador.
	 *
	 * @param idPerfil El identificador único del perfil a buscar.
	 * @return ResponseEntity con el perfil encontrado si existe, o un mensaje de
	 *         error si no existe.
	 */
	@GetMapping("/uno/{idPerfil}") // probado y funcionando
	public ResponseEntity<?> uno(@PathVariable("idPerfil") int idPerfil) {
		try {
			Perfil perfil = perfilService.buscarUno(idPerfil);

			if (perfil != null)
				return ResponseEntity.status(HttpStatus.OK).body(perfil);
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontró ningún perfil con el identificador: " + idPerfil);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener el perfil: " + e.getMessage());
		}
	}

	/**
	 * Método que permite crear un perfil.
	 * 
	 * @param perfil El perfil a dar de alta.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         alta.
	 */
	@PostMapping("/alta") // probado y funcionando
	public ResponseEntity<?> alta(@RequestBody Perfil perfil) {
		try {
			Perfil resultado = perfilService.insertOne(perfil);

			if (resultado != null)
				return ResponseEntity.status(HttpStatus.OK).body(perfil);
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al insertar perfil");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al dar de alta el perfil: " + e.getMessage());
		}
	}

	/**
	 * Método que modifica un perfil.
	 * 
	 * @param perfil El perfil con la información actualizada.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         modificación.
	 */
	@PutMapping("/modificar") // probado y funcionando
	public ResponseEntity<?> modificar(@RequestBody Perfil perfil) {
		try {
			// Verifica si el perfil existe antes de intentar modificarlo
			Perfil perfilExistente = perfilService.buscarUno(perfil.getIdPerfil());
			if (perfilExistente != null) {
				// Actualiza el perfil existente con la nueva información
				perfilService.modificarPerfil(perfilExistente);
				return ResponseEntity.status(HttpStatus.OK).body("Modificación realizada correctamente " + perfil);
			} else {
				// Si el perfil no existe, devuelve un error
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil no encontrado");
			}
		} catch (Exception e) {
			// Manejo de excepciones
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al modificar el perfil: " + e.getMessage());
		}
	}

	/**
	 * Método que elimina un perfil.
	 * 
	 * @param idPerfil El identificador único del perfil.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/eliminar/{idPerfil}") // probado y funcionando
	public ResponseEntity<?> borrar(@PathVariable("idPerfil") int idPerfil) {
		try {
			if (perfilService.buscarUno(idPerfil) != null) {
				perfilService.deleteOne(idPerfil);
				return ResponseEntity.status(HttpStatus.OK).body("Perfil eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El perfil no se pudo encontrar");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el perfil: " + e.getMessage());
		}
	}
}