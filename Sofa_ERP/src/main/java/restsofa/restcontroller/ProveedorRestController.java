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
@RequestMapping("/proveedor")
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
	public ResponseEntity<Proveedor> alta(@RequestBody Proveedor proveedor) {

		return ResponseEntity.status(200).body(proveedorService.insertOne(proveedor));

	}

	/*
	 * Método que devuelve todos los proveedores.
	 *
	 * @return ResponseEntity con la lista de proveedores si se pudo cargar
	 * correctamente, o un mensaje de error si no se cargó.
	 */

	@GetMapping("/todos")
	public ResponseEntity<List<Proveedor>> mostrartodos() {
		return ResponseEntity.status(200).body(proveedorService.mostrarTodos());
	}

	
	/*
	 * Método que devuelve un proveedor.
	 * 
	 * @param idProveedor. El identificador único del proveedor.
	 * 
	 * @return ResponseEntity con el proveedor si se obtiene correctamente, o un
	 * mensaje de error si no existe.
	 */

	@GetMapping("/uno/{idProveedor}") // probado y funcionando
	public ResponseEntity<?> uno(@PathVariable int idProveedor) {

		Proveedor proveedor = proveedorService.buscarUno(idProveedor);

		if (proveedor != null)
			return ResponseEntity.status(200).body(proveedor);

		else
			return ResponseEntity.status(400).body("No se encuentra el proveedor");

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
	public ResponseEntity<?> modificar(@RequestBody Proveedor proveedor) {

		if (proveedorService.buscarUno(proveedor.getIdProveedor()) != null) {
			proveedorService.modificarUno(proveedor);
			return ResponseEntity.status(200).body("Modificación realizada correctamente " + proveedor);
		} else
			return ResponseEntity.status(400).body("Error al modificar proveedor en la base de datos");
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
	public ResponseEntity<?> borrar(@PathVariable("id") int idProveedor) {

		if (proveedorService.eliminarUno(idProveedor) == 1) {
			return ResponseEntity.status(200).body("Proveedor eliminado");
		}
		return ResponseEntity.status(500).body("Proveedor no eliminado");
	}
}
