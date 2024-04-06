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

import restsofa.modelo.entities.Proveedor;
import restsofa.service.ProveedorService;

/**
 * Controlador para la gestión de los proveedores.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proveedores")
public class ProveedorRestController {

	@Autowired
	private ProveedorService proveedorService;

	/*
	 * Método que permite dar de alta un proveedor.
	 * 
	 * @param proveedor El proveedor a dar de alta.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * alta.
	 */

	@PostMapping("/alta")
	ResponseEntity<Proveedor> altaproveedor(@RequestBody Proveedor proveedor) {

		return ResponseEntity.status(200).body(proveedorService.insertOne(proveedor));

	}

	/*
	 * Método que devuelve todos los proveedores.
	 *
	 * @return ResponseEntity con la lista de proveedores si se pudo cargar
	 * correctamente, o un mensaje de error si no se cargó.
	 */

	@GetMapping("/todos")
	ResponseEntity<List<Proveedor>> mostrartodos() {
		return ResponseEntity.status(200).body(proveedorService.mostrarTodos());
	}

	/*
	 * Método que modifica los datos de un proveedor.
	 * 
	 * @param preoveedor El proveedor con la información actualizada.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * modificación.
	 */

	@PutMapping("/modificar/{id}")
	ResponseEntity<?> modificarproveedor(@RequestBody Proveedor proveedor, @PathVariable("id") int idProveedor) {

		if (proveedorService.modificarUno(proveedor) == null) {
			return ResponseEntity.status(500).body("ERROR");
		}
		return ResponseEntity.status(200).body(proveedor);
	}

	/*
	 * Método que elimina un proveedor.
	 * 
	 * @param idProveedor. El identificador único del proveedor.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 * eliminación.
	 */
	
	@DeleteMapping("/eliminar/{id}")
	ResponseEntity<?> eliminarproveedor(@PathVariable("id") int idProveedor) {

		if (proveedorService.eliminarUno(idProveedor) == 1) {
			return ResponseEntity.status(200).body("Proveedor eliminado");
		}
		return ResponseEntity.status(500).body("Proveedor no eliminado");
	}
}
