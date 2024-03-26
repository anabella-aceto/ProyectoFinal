package restsofa.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Proveedor;
import restsofa.service.ProveedorService;

@RestController
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
