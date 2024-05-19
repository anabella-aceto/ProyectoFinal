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

import restsofa.modelo.DTO.SofaMaterialDto;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.service.MaterialService;
import restsofa.service.SofaMaterialService;
import restsofa.service.SofaService;

/**
 * Controlador para la gestión de los materiales de un sofá.
 */

@RestController
@RequestMapping("/sofaMaterial")
@CrossOrigin(origins = "*")
public class SofaMaterialRestController {

	@Autowired
	private SofaMaterialService sofaMaterialService;

	@Autowired
	private SofaService sofaService;

	@Autowired
	private MaterialService materialService;

	/**
	 * Método que devuelve todos los sofás con todos sus materiales.
	 *
	 * @return ResponseEntity con la lista de sofás con todos sus materiales si se
	 *         pudo cargar correctamente, o un mensaje de error si no se cargó.
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> listarTodos() {
		try {
			List<SofaMaterial> lista = sofaMaterialService.todos();
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al cargar la lista: " + e.getMessage());
		}
	}

	/**
	 * Método que obtiene un material de sofá por su identificador único.
	 *
	 * @param idMaterialSofa El identificador único del material de sofá a buscar.
	 * @return ResponseEntity con el material de sofá encontrado si existe, o un
	 *         mensaje de error si no existe.
	 */
	@GetMapping("/uno/{idMaterialSofa}")
	public ResponseEntity<?> buscarPorIdMaterialSofa(@PathVariable("idMaterialSofa") int idMaterialSofa) {
		try {
			SofaMaterial sofaMaterial = sofaMaterialService.buscarUno(idMaterialSofa);
			if (sofaMaterial != null) {
				return ResponseEntity.status(HttpStatus.OK).body(sofaMaterial);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra el material de sofá");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el material de sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que busca materiales por sofá.
	 *
	 * @param idSofa El identificador único del sofá del cual se desean buscar los
	 *               materiales asociados.
	 * @return ResponseEntity con la lista de materiales asociados al sofá si
	 *         existen, o un mensaje de error si no.
	 */
	@GetMapping("/porSofa/{idSofa}")
	public ResponseEntity<?> buscarPorSofa(@PathVariable("idSofa") int idSofa) {
		try {
			List<SofaMaterial> lista = sofaMaterialService.buscarPorSofa(idSofa);
			if (!lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista está vacía");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar los materiales del sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que permite dar de alta los materiales de un sofá.
	 * 
	 * @param sofaMaterialDto El DTO con la información de los materiales del sofá a
	 *                        dar de alta.
	 * @return ResponseEntity con el material de sofá guardado o un mensaje de error
	 *         si no se pudo dar de alta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> altaSofaMaterial(@RequestBody SofaMaterialDto sofaMaterialDto) {
		try {
			SofaMaterial sofaMaterial = new SofaMaterial();
			sofaMaterial.setSofa(sofaService.buscarSofa(sofaMaterialDto.getIdSofa()));
			sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
			sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());

			SofaMaterial materialGuardado = sofaMaterialService.insertOne(sofaMaterial);
			if (materialGuardado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(materialGuardado);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al dar de alta el material de sofá");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al dar de alta el material de sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que modifica los datos de un material de sofá.
	 *
	 * @param sofaMaterialDto El DTO del material de sofá a modificar.
	 * @return ResponseEntity con el mensaje del resultado de la modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody SofaMaterialDto sofaMaterialDto) {
		try {
			SofaMaterial sofaMaterial = sofaMaterialService.buscarUno(sofaMaterialDto.getIdSofaMateriales());
			if (sofaMaterial != null) {
				sofaMaterial.setSofa(sofaService.buscarSofa(sofaMaterialDto.getIdSofa()));
				sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
				sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());
				sofaMaterialService.updateOne(sofaMaterial);
				return ResponseEntity.status(HttpStatus.OK)
						.body("Modificación realizada correctamente " + sofaMaterial);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar el material de sofá");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al modificar el material de sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que modifica un material asociado a un sofá.
	 *
	 * @param sofaMaterialDto Los datos del material asociado al sofá a modificar.
	 * @return ResponseEntity con un mensaje de éxito si se realiza la modificación
	 *         correctamente, o un mensaje de error si no.
	 */
	@PutMapping("/modificarSofaMaterial/{idSofa}/{idMaterial}")
	public ResponseEntity<?> modificarSofaMaterial(@RequestBody SofaMaterialDto sofaMaterialDto) {
		try {
			SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(sofaMaterialDto.getIdSofa(),
					sofaMaterialDto.getIdMaterial());
			if (sofaMaterial != null) {
				sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
				sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());
				sofaMaterialService.updateOne(sofaMaterial);
				return ResponseEntity.status(HttpStatus.OK)
						.body("Modificación realizada correctamente " + sofaMaterial);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido modificar el material");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al modificar el material del sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que elimina un material de sofá.
	 * 
	 * @param idSofaMateriales El identificador único del material de sofá a
	 *                         eliminar.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/eliminar/{idSofaMateriales}")
	public ResponseEntity<?> borrarUno(@PathVariable("idSofaMateriales") int idSofaMateriales) {
		try {
			SofaMaterial sofaMaterial = sofaMaterialService.buscarUno(idSofaMateriales);
			if (sofaMaterial != null) {
				sofaMaterialService.deleteOne(sofaMaterial);
				return ResponseEntity.status(HttpStatus.OK).body("Material de sofá eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al borrar material de sofá");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el material de sofá: " + e.getMessage());
		}
	}

	/**
	 * Método que elimina un material asociado a un sofá.
	 *
	 * @param idSofa     El identificador único del sofá del cual se desea eliminar
	 *                   el material.
	 * @param idMaterial El identificador único del material a eliminar.
	 * @return ResponseEntity con un mensaje de éxito si se realiza la eliminación
	 *         correctamente, o un mensaje de error si no.
	 */
	@DeleteMapping("/eliminar/{idSofa}/{idMaterial}")
	public ResponseEntity<?> eliminarSofaMaterial(@PathVariable("idSofa") int idSofa,
			@PathVariable("idMaterial") int idMaterial) {
		try {
			SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(idSofa, idMaterial);
			if (sofaMaterial != null) {
				sofaMaterialService.deleteOne(sofaMaterial);
				return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede eliminar el material");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el material de sofá: " + e.getMessage());
		}
	}
}
