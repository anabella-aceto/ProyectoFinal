package restsofa.restcontroller;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> 43a49da23f32ecab9e7cdcab686487398cfbda61
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Proveedor;
import restsofa.service.ProveedorService;

@RestController
<<<<<<< HEAD
@CrossOrigin(origins = "*")
@RequestMapping("/proveedores")
public class ProveedorRestController {

	@Autowired
	private ProveedorService proveedorService;


	
	//Alta proveedor
	@PostMapping ("/alta")
	ResponseEntity<Proveedor> altaproveedor (@RequestBody Proveedor proveedor) {
		
		return ResponseEntity.status(200).body(proveedorService.insertOne(proveedor));
		
	}
	
	//Mostrar todos los proveedores
	@GetMapping ("/todos")
	ResponseEntity<List<Proveedor>> mostrartodos (){
		return ResponseEntity.status(200).body(proveedorService.mostrarTodos());
	}
	
	//Modificar un proveedor
	@PutMapping ("/modificar/{id}")
	ResponseEntity<?> modificarproveedor (@RequestBody Proveedor proveedor, @PathVariable ("id") int idProveedor){
		
			if (proveedorService.modificarUno(proveedor)==null) {
				return ResponseEntity.status(500).body("ERROR");
			}
			return ResponseEntity.status(200).body(proveedor);		
	}
	
	//Eliminar un proveedor
	@DeleteMapping ("/eliminar/{id}")
	ResponseEntity<?> eliminarproveedor (@PathVariable ("id") int idProveedor){
			
		if(proveedorService.eliminarUno(idProveedor)==1){
			return ResponseEntity.status(200).body("Proveedor eliminado");
		}
			return ResponseEntity.status(500).body("Proveedor no eliminado");
	}
}











=======
@RequestMapping("proveedores")
@CrossOrigin(origins="*")
public class ProveedorRestController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@PostMapping("/alta")//Probado y funcionando
	public ResponseEntity<?> altaProveedor(@RequestBody Proveedor proveedor){
		
		if(proveedorService.insertOne(proveedor)!=null)
			return ResponseEntity.status(200).body("Alta exitosa" +proveedor);
		
		else
			return ResponseEntity.status(400).body("Error al cargar propveedor");
	}
	

}
>>>>>>> 43a49da23f32ecab9e7cdcab686487398cfbda61
