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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restsofa.modelo.DTO.MaterialDto;
import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.Material;
import restsofa.modelo.entities.Pedido;
import restsofa.modelo.entities.Tarea;
import restsofa.service.DetallePedidoService;
import restsofa.service.MaterialService;
import restsofa.service.PedidoService;
import restsofa.service.ProveedorService;
import restsofa.service.TareaService;

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
	private DetallePedidoService detallePedidoService;
	
	@Autowired
	private TareaService tareaService;

	/*
	 * Método que devuelve todos los materiales.
	 *
	 * @return ResponseEntity con la lista de materiales si se pudo cargar
	 * correctamente, o un mensaje de error si no.
	 */

	@GetMapping("/todos") // Probado y funcionando
	public ResponseEntity<?> listarTodos() {
		try {
			List<Material> material = materialService.findAll();

			if (!material.isEmpty()) {
				return ResponseEntity.ok().body(material);
			} else {
				return ResponseEntity.ok().body("No hay materiales en la lista");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al cargar los materiales: " + e.getMessage());
		}
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
		try {
			Material material = materialService.findById(idMaterial);
			if (material != null) {
				return ResponseEntity.ok().body(material);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El identificador del material no existe");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el material: " + e.getMessage());
		}
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
		try {
			List<Material> material = materialService.buscarPorNombre(nombre);
			if (material != null && !material.isEmpty()) {
				return ResponseEntity.ok().body(material);
			} else {
				return ResponseEntity.ok().body("No se encuentran materiales con el nombre proporcionado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el material: " + e.getMessage());
		}
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
	public ResponseEntity<?> buscarPorRefProveedor(@PathVariable("refMaterialProveedor") int refMaterialProveedor) {
		try {
			Material material = materialService.findByProveedor(refMaterialProveedor);
			if (material != null) {
				return ResponseEntity.ok().body(material);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el material");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar el material: " + e.getMessage());
		}
	}

	/*
	 * Método que permite crear un material.
	 * 
	 * @param materialDto El objeto MaterialDto que contiene los detalles del
	 * material a dar de alta.
	 * 
	 * @return ResponseEntity con el material creado en caso de éxito, o un mensaje
	 * de error en caso de fallo.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> altaMaterial(@RequestBody MaterialDto materialDto) {
		try {
			// Intentar dar de alta el material
			Material material = new Material();
			material.setNombre(materialDto.getNombre());
			material.setDescripcion(materialDto.getDescripcion());
			material.setCantidad(materialDto.getCantidad());
			material.setRefMaterialProveedor(materialDto.getRefMaterialProveedor());
			material.setCategoria(materialDto.getCategoria());
			material.setUnidadMedida(materialDto.getUnidadMedida());
			material.setProveedor(proveedorService.buscarUno(materialDto.getIdProveedor()));

			Material materialNuevo = materialService.insertOne(material);

			// Verificar si se creó el material correctamente
			if (materialNuevo != null) {
				return ResponseEntity.status(HttpStatus.OK).body(materialNuevo);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al insertar datos de material");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/*
	 * Método que elimina un material por identificador.
	 * 
	 * @param idMaterial El identificador único del material a eliminar.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * eliminación.
	 */

	@DeleteMapping("/eliminar/{idMaterial}") // Probado y funcionando
	public ResponseEntity<?> eliminarMaterial(@PathVariable("idMaterial") int idMaterial) {
		try {
			Material material = materialService.findById(idMaterial);
			if (material != null) {
				materialService.deleteOne(idMaterial);
				return ResponseEntity.status(HttpStatus.OK).body("Material eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El material no existe");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el material: " + e.getMessage());
		}
	}

	/*
	 * Método que modifica un material.
	 * 
	 * @param materialDto El material con la información actualizada.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * modificación.
	 */

	@PutMapping("/modificar") // Probado y funcionando
	public ResponseEntity<?> modificarMaterial(@RequestBody MaterialDto materialDto) {
		try {
			Material material = materialService.findById(materialDto.getIdMaterial());

			if (material != null) {
				material.setCantidad(materialDto.getCantidad());
				material.setDescripcion(materialDto.getDescripcion());
				material.setNombre(materialDto.getNombre());
				material.setProveedor(proveedorService.buscarUno(materialDto.getIdProveedor()));
				material.setRefMaterialProveedor(materialDto.getRefMaterialProveedor());
				material.setUnidadMedida(materialDto.getUnidadMedida());
				material.setCategoria(materialDto.getCategoria());
				materialService.updateOne(material);
				return ResponseEntity.status(HttpStatus.OK).body("Material modificado exitosamente: " + material);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el material");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al modificar el material: " + e.getMessage());
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
	@GetMapping("/porProveedor/{idProveedor}") // Probado y funcionando
	public ResponseEntity<?> buscarMaterialPorProveedor(@PathVariable("idProveedor") int idProveedor) {
		try {
			List<Material> material = materialService.buscarPorProveedor(idProveedor);

			if (material != null && !material.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(material);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontraron materiales para el proveedor proporcionado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar los materiales: " + e.getMessage());
		}
	}

	/**
	 * Método que filtra materiales por categoría.
	 *
	 * @param categoria La categoría de los materiales a buscar.
	 * @return ResponseEntity con la lista de materiales encontrados si existen, o
	 *         un mensaje de error si no.
	 */
	@GetMapping("/porCategoria") // Probado y funcionando
	public ResponseEntity<?> buscarMaterialPorCategoria(@RequestParam(name = "categoria") String categoria) {
		try {
			List<Material> material = materialService.buscarPorCategoria(categoria);

			if (material != null && !material.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(material);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontraron materiales para la categoría proporcionada");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar los materiales: " + e.getMessage());
		}
	}

	/**
	 * Método para restaurar materiales de un pedido.
	 *
	 * @param idPedido ID del pedido a restaurar.
	 * @param idSofa   ID del sofá a restaurar.
	 * @return ResponseEntity con el estado de la operación y un mensaje.
	 */
	@PutMapping("restaurar/{idPedido}/{idSofa}")
	public ResponseEntity<?> restaurarMateriales(@PathVariable int idPedido, @PathVariable int idSofa) {
		try {
			// Buscar el pedido en la base de datos
			DetallePedido detallePedido = detallePedidoService.buscarPorPedido(idPedido);

			// Verificar si el pedido existe y está en un estado adecuado para restaurar
			// materiales
			if (detallePedido != null && tareaService.buscarPorEstado(1) !=null) {
				// Intentar restaurar los materiales
				if (materialService.restaurarMateriales(idPedido, idSofa) == 1) {
					// Si la restauración fue exitosa, devolver un ResponseEntity con estado 200 y
					// un mensaje
					return ResponseEntity.status(HttpStatus.OK).body("Pedido restaurado");
				} else {
					// Si ocurrió un error al restaurar los materiales, devolver un ResponseEntity
					// con estado 404 y un mensaje de error
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al restaurar los materiales");
				}
			} else {
				// Si el pedido no existe o no está en el estado adecuado para restaurar
				// materiales, devolver un ResponseEntity con estado 404 y un mensaje de error
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("El pedido no existe o no está en el estado adecuado para restaurar los materiales");
			}
		} catch (Exception e) {
			// Si ocurre una excepción, devolver un ResponseEntity con estado 500 y el
			// mensaje de la excepción
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al restaurar los materiales: " + e.getMessage());
		}
	}

}