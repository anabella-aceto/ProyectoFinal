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
import org.springframework.web.bind.annotation.RestController;
import restsofa.modelo.DTO.MaterialDto;
import restsofa.modelo.entities.Material;

import restsofa.service.MaterialService;
import restsofa.service.ProveedorService;

@RestController
@RequestMapping("materiales")
@CrossOrigin(origins="*")
public class MaterialRestController {
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private ModelMapper modelMapper;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/todos")//Probado y funcionando
	
	public ResponseEntity<?> listarTodos(){
		
		List<Material> material = materialService.findAll();
		
		if(!material.isEmpty())
			return ResponseEntity.ok().body(material);
		
		else if (material.isEmpty())
			return ResponseEntity.ok().body("No hay materiales en la lista");
		
		else
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar los materiales");				
	
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping("/uno/{idMaterial}")//Probado y funcionando
	public ResponseEntity<?> buscarUno(@PathVariable ("idMaterial") int idMaterial){
		
		Material material = materialService.findById(idMaterial);
		if(material!=null) 
			return ResponseEntity.ok().body(material);
			
		else
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El identificador del material no existe");
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/porNombre/{nombre}")//Probado y funcionando
	public ResponseEntity<?> buscarPorNombre(@PathVariable ("nombre") String nombre){
		
		List<Material> material=  materialService.buscarPorNombre(nombre);
		if(material!=null)
			return ResponseEntity.ok().body(material);
		
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se encuentra el material");
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/porRefProveedor/{refMaterialProveedor}")//Probado y funcionando
	public ResponseEntity<?> buscarPorRefProveedor(@PathVariable ("refMaterialProveedor") String refMaterialProveedor){
		
		Material material = materialService.findByProveedor(refMaterialProveedor);
		if(material != null)
			return ResponseEntity.ok().body(material);
		
		else 
			return ResponseEntity.status(500).body("No se encuentra el material");
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/alta")//probado y funcionando
	public ResponseEntity<?> altaMaterial(@RequestBody MaterialDto materialDto){
		
		Material material = new Material();
		modelMapper.map(materialDto, material);
		material.setProveedor(proveedorService.buscarUno(materialDto.getIdProveedor()));
		
		if(materialService.insertOne(material)!=null)
			return ResponseEntity.status(200).body(material);
        
		else
			return ResponseEntity.status(500).body("Error al ingresar el material");
	}
	

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@DeleteMapping("/eliminar/{idMaterial}")//probado y funcionando
	public ResponseEntity<?> elimnarMaterial(@PathVariable ("idMaterial") int idMaterial){
		
		if(materialService.findById(idMaterial)!=null)
		{
			materialService.deleteOne(idMaterial);
			return ResponseEntity.status(200).body("Material eliminado correctamente");
		}
		else
			return ResponseEntity.status(500).body("No es posible eliminar el material");
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@PutMapping("/modificar")//probado y funcionando
	public ResponseEntity<?> modificarMaterial(@RequestBody MaterialDto materialDto){
		
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
}