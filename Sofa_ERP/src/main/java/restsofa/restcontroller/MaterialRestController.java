package restsofa.restcontroller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restsofa.modelo.DTO.MaterialDto;
import restsofa.modelo.entities.Material;
import restsofa.modelo.entities.Pedido;
import restsofa.service.MaterialService;
import restsofa.service.PedidoService;
import restsofa.service.ProveedorService;


/**
 * Controlador para la gestión de los materiales.
 */

@RestController
@RequestMapping("materiales")
@CrossOrigin(origins = "*")
public class MaterialRestController {

	@Autowired
	private MaterialService materialService;

	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ModelMapper modelMapper;
	
	
	/*
	 * Método que devuelve todos los materiales.
	 *
	 * @return ResponseEntity con la lista de materiales si se pudo cargar
	 * correctamente, o un mensaje de error si no.
	 */

	@GetMapping("/todos") // Probado y funcionando

	public ResponseEntity<?> listarTodos() {

		List<Material> material = materialService.findAll();

		if (!material.isEmpty())
			return ResponseEntity.ok().body(material);

		else if (material.isEmpty())
			return ResponseEntity.ok().body("No hay materiales en la lista");

		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar los materiales");

	}

	/**
	 * Método que permite obtener un material por su identififcador.
	 *
	 * @param idMaterial El identificador único del material a buscar.
	 * @return ResponseEntity con el material encontrado si existe, o un mensaje de
	 *         error si no existe.
	 */

	@GetMapping("/uno/{idMaterial}") // Probado y funcionando
	public ResponseEntity<?> buscarUno(@PathVariable("idMaterial") int idMaterial) {

		Material material = materialService.findById(idMaterial);
		if (material != null)
			return ResponseEntity.ok().body(material);

		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("El identificador del material no existe");
	}

	/**
	 * Método que permite buscar materiales por nombre.
	 *
	 * @param nombre El nombre del material a buscar.
	 * @return ResponseEntity con la lista de materiales encontrados si existen, o
	 *         un mensaje de error si no.
	 */

	@GetMapping("/porNombre/{nombre}") // Probado y funcionando
	public ResponseEntity<?> buscarPorNombre(@PathVariable("nombre") String nombre) {

		List<Material> material = materialService.buscarPorNombre(nombre);
		if (material != null)
			return ResponseEntity.ok().body(material);

		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se encuentra el material");
	}

	/**
	 * Busca materiales por referencia del proveedor.
	 *
	 * @param refMaterialProveedor La referencia del proveedor del material a
	 *                             buscar.
	 * @return ResponseEntity con el material encontrado si existe, o un mensaje de
	 *         error si no.
	 */

	@GetMapping("/porRefProveedor/{refMaterialProveedor}") // Probado y funcionando
	public ResponseEntity<?> buscarPorRefProveedor(@PathVariable("refMaterialProveedor") String refMaterialProveedor) {

		Material material = materialService.findByProveedor(refMaterialProveedor);
		if (material != null)
			return ResponseEntity.ok().body(material);

		else
			return ResponseEntity.status(500).body("No se encuentra el material");
	}

	/*
	 * Método que permite crear un material.
	 * 
	 * @param material El material a dar de alta.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * alta.
	 */

	@PostMapping("/alta") // probado y funcionando
	public ResponseEntity<?> altaMaterial(@RequestBody MaterialDto materialDto) {

		Material material = new Material();
		modelMapper.map(materialDto, material);
		material.setProveedor(proveedorService.buscarUno(materialDto.getIdProveedor()));

		if (materialService.insertOne(material) != null)
			return ResponseEntity.status(200).body(material);

		else
			return ResponseEntity.status(500).body("Error al ingresar el material");
	}

	/*
	 * Método que elimina un material por identificador.
	 * 
	 * @param idMaterial El identificador único del material a eliminar.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * eliminación.
	 */

	@DeleteMapping("/eliminar/{idMaterial}") // probado y funcionando
	public ResponseEntity<?> elimnarMaterial(@PathVariable("idMaterial") int idMaterial) {

		if (materialService.findById(idMaterial) != null) {
			materialService.deleteOne(idMaterial);
			return ResponseEntity.status(200).body("Material eliminado correctamente");
		} else
			return ResponseEntity.status(500).body("No es posible eliminar el material");
	}

	/*
	 * Método que modifica un material.
	 * 
	 * @param material El material con la información actualizada.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * modificación.
	 */

	@PutMapping("/modificar") // probado y funcionando
	public ResponseEntity<?> modificarMaterial(@RequestBody MaterialDto materialDto) {

		Material material = materialService.findById(materialDto.getIdMaterial());

		if (material != null) {
			material.setCantidad(materialDto.getCantidad());
			material.setDescripcion(materialDto.getDescripcion());
			material.setNombre(materialDto.getNombre());
			material.setProveedor(proveedorService.buscarUno(materialDto.getIdProveedor()));
			material.setRefMaterialProveedor(materialDto.getRefMaterialProveedor());
			materialService.updateOne(material);
			return ResponseEntity.status(200).body("Material modificado exitosamente: " + material);
		} else {
			return ResponseEntity.status(404).body("No se encuentra el material");
		}
	}

	/**
	 * Método que busca materiales por el identificador del proveedor.
	 *
	 * @param idProveedor El identificador único del proveedor cuyos materiales se
	 *                    desean buscar.
	 * @return ResponseEntity con la lista de materiales encontrados si existen, o
	 *         un mensaje de error si no existen.
	 */
	@GetMapping("/porProveedor/{idProveedor}") // probado y funcionando

	public ResponseEntity<?> buscarMaterialPorProveedor(@PathVariable("idProveedor") int idProveedor) {

		List<Material> material = materialService.buscarPorProveedor(idProveedor);

		if (material != null)
			return ResponseEntity.status(200).body(material);

		else
			return ResponseEntity.status(404).body("Provvedor no encontrado");

	}

	/**
	 * Método que filtra materiales por categoría.
	 *
	 * @param categoria La categoría de los materiales a buscar.
	 * @return ResponseEntity con la lista de materiales encontrados si existen, o
	 *         un mensaje de error si no.
	 */
	@GetMapping("/porCategoria")
	public ResponseEntity<?> buscarMaterialPorProveedor(@RequestParam(name = "categoria") String categoria) {

		List<Material> material = materialService.buscarPorCategoria(categoria);

		if (!material.isEmpty())
			return ResponseEntity.status(200).body(material);

		else
			return ResponseEntity.status(404).body("Categoría no encontrada");

	}
	
	@PutMapping("restaurar/{idPedido}/{idSofa}")
	public ResponseEntity<?> restaurarMateriales(@PathVariable int idPedido, @PathVariable int idSofa) {
	    Pedido pedido = pedidoService.buscarPedido(idPedido);
	    
	    if (pedido != null && pedido.getEstado().getIdEstado() == 1) {
	        if (materialService.restaurarMateriales(idPedido, idSofa) == 1) {
	            return ResponseEntity.status(200).body("Pedido restaurado");
	        } else {
	            return ResponseEntity.status(404).body("Error al restaurar los materiales");
	        }
	    } else {
	        return ResponseEntity.status(404).body("El pedido no existe o no está en el estado adecuado para restaurar los materiales");
	    }
	}
	
}