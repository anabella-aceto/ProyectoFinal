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

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * Método que devuelve todos los sofás con todos sus materiales.
	 *
	 * @return ResponseEntity con la lista de sofás con todos sus materiales si se
	 * pudo cargar correctamente, o un mensaje de error si no se cargó.
	 */

	@GetMapping("/todos") // probado y funcionando
	public ResponseEntity<?> listarTodos() {

		List<SofaMaterial> lista = sofaMaterialService.todos();

		if (lista != null)
			return ResponseEntity.status(200).body(lista);

		else
			return ResponseEntity.status(400).body("Error al cargar la lista");
	}

	/**
	 * Método que busca materiales por sofá.
	 *
	 * @param idSofa El identificador único del sofá del cual se desean buscar los
	 *               materiales asociados.
	 * @return ResponseEntity con la lista de materiales asociados al sofá si
	 *         existen, o un mensaje de error si no.
	 */
	@GetMapping("/porSofa/{idSofa}") // probado y funcionando

	public ResponseEntity<?> buscarPorSofa(@PathVariable("idSofa") int idSofa) {

		List<SofaMaterial> sofaMaterial = sofaMaterialService.buscarPorSofa(idSofa);

		if (sofaMaterial != null)
			return ResponseEntity.status(200).body(sofaMaterial);

		else
			return ResponseEntity.status(400).body("Error al cargar sofá");

	}

	/*
	 * Método que permite dar de alta un los materiales de un sofá.
	 * 
	 * @param sofaMaterial El sofaMaterial a dar de alta.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * alta.
	 */

	@PostMapping("/alta") // probado y funcionando

	public ResponseEntity<?> altaSofaMaterial(@RequestBody SofaMaterialDto sofaMaterialDto) {

		SofaMaterial sofaMaterial = new SofaMaterial();

		modelMapper.map(sofaMaterialDto, sofaMaterial);

		sofaMaterial.setSofa(sofaService.buscarSofa(sofaMaterialDto.getIdSofa()));
		sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
		sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());

		if (sofaMaterialService.insertOne(sofaMaterial) != null)
			return ResponseEntity.status(200).body("Material ingresado correctamente " + sofaMaterial);

		else
			return ResponseEntity.status(400).body("Error al cargar sofá");

	}

	/**
	 * Método que modifica un material asociado a un sofá.
	 *
	 * @param sofaMaterialDto Los datos del material asociado al sofá a modificar.
	 * @return ResponseEntity con un mensaje de éxito si se realiza la modificación
	 *         correctamente, o un mensaje de error si no.
	 */

	@PutMapping("/modificar") // probado y funcionando

	public ResponseEntity<?> modificarSofaMaterial(@RequestBody SofaMaterialDto sofaMaterialDto) {

		SofaMaterial sofaMaterial = (SofaMaterial) sofaMaterialService
				.buscarPorSofaAndmaterial(sofaMaterialDto.getIdSofa(), sofaMaterialDto.getIdMaterial());

		if (sofaMaterial != null) {
			sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
			sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());
			sofaMaterialService.updateOne(sofaMaterial);

			return ResponseEntity.status(200).body("Material modificado correctamente " + sofaMaterial);

		}

		else
			return ResponseEntity.status(400).body("No se ha podido modificar el material");

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
	@DeleteMapping("/eliminar/{idSofa}/{idMaterial}") // probado y funcionando

	public ResponseEntity<?> eliminarSofaMaterial(@PathVariable("idSofa") int idSofa,
			@PathVariable("idMaterial") int idMaterial) {

		SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(idSofa, idMaterial);

		if (sofaMaterial != null) {
			sofaMaterialService.deleteOne(sofaMaterial);
			return ResponseEntity.status(200).body("Eliminación exitosa");
		} else
			return ResponseEntity.status(400).body("No se puede eliminar el material");
	}

}
