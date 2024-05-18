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

import restsofa.modelo.entities.Proveedor;
import restsofa.service.ProveedorService;

/**
 * Controlador para la gestión de los proveedores.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proveedor")
public class ProveedorRestController {

	@Autowired
	private ProveedorService proveedorService;

	/**
	 * Método que permite dar de alta un proveedor.
	 * 
	 * @param proveedor El proveedor a dar de alta.
	 * @return ResponseEntity con el proveedor dado de alta o un mensaje de error si
	 *         no se pudo dar de alta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Proveedor proveedor) {
		try {
			Proveedor nuevoProveedor = proveedorService.insertOne(proveedor);
			return ResponseEntity.status(HttpStatus.OK).body(nuevoProveedor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al dar de alta el proveedor: " + e.getMessage());
		}
	}

	/**
	 * Método que devuelve todos los proveedores.
	 *
	 * @return ResponseEntity con la lista de proveedores si se pudo cargar
	 *         correctamente, o un mensaje de error con estado 500
	 *         (INTERNAL_SERVER_ERROR) si no se cargó.
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> mostrartodos() {
	    try {
	        List<Proveedor> lista = proveedorService.mostrarTodos();

	        if (!lista.isEmpty()) {
	            // Devolver ResponseEntity<List<Proveedor>>
	            return ResponseEntity.ok().body(lista);
	        } else {
	            // Devolver mensaje si la lista está vacía
	            return ResponseEntity.ok().body("No se encontraron proveedores");
	        }
	    } catch (Exception e) {
	        // Devolver ResponseEntity con mensaje de error
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al obtener la lista de proveedores: " + e.getMessage());
	    }
	}

	/**
	 * Método que devuelve un proveedor.
	 * 
	 * @param idProveedor El identificador único del proveedor.
	 * @return ResponseEntity con el proveedor si se obtiene correctamente, o un
	 *         mensaje de error si no existe.
	 */
	@GetMapping("/uno/{idProveedor}") // probado y funcionando
	public ResponseEntity<?> uno(@PathVariable int idProveedor) {
		try {
			Proveedor proveedor = proveedorService.buscarUno(idProveedor);
			if (proveedor != null)
				return ResponseEntity.status(HttpStatus.OK).body(proveedor);
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra el proveedor");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener el proveedor: " + e.getMessage());
		}
	}

	/**
	 * Método que modifica los datos de un proveedor.
	 * 
	 * @param proveedor El proveedor con la información actualizada.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         modificación.
	 */
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificar(@RequestBody Proveedor proveedor) {
		try {
			if (proveedorService.buscarUno(proveedor.getIdProveedor()) != null) {
				proveedorService.modificarUno(proveedor);
				return ResponseEntity.status(HttpStatus.OK).body("Modificación realizada correctamente " + proveedor);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Error al modificar proveedor en la base de datos");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al modificar el proveedor: " + e.getMessage());
		}
	}

	/**
	 * Método que elimina un proveedor.
	 * 
	 * @param idProveedor El identificador único del proveedor.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int idProveedor) {
		try {
			if (proveedorService.eliminarUno(idProveedor) == 1) {
				return ResponseEntity.status(HttpStatus.OK).body("Proveedor eliminado");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Proveedor no eliminado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar el proveedor: " + e.getMessage());
		}
	}
}