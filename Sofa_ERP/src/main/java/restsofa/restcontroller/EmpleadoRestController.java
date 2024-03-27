package restsofa.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.DTO.EmpleadoDto;
import restsofa.modelo.entities.Empleado;
import restsofa.service.EmpleadoService;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins="*")
public class EmpleadoRestController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	//private DepartamentoService departamentoService;
	
	//@Autowired
	// PerfilService perfilService;
	
	
	@GetMapping("/uno/{idEmpleado}")//probado y funcionando
	public ResponseEntity<?> buscarPorId(@PathVariable ("idEmpleado") int idEmpleado){
		
		Empleado empleado = empleadoService.buscarUno(idEmpleado);
		if ( empleado != null)
			return ResponseEntity.status(200).body(empleado);
		
		else
			return ResponseEntity.status(400).body("No se encuentra el empleado");
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@PostMapping("/alta")	

	public ResponseEntity<?> buscarPorId(@RequestBody EmpleadoDto empleadoDto){
		
		Empleado empleado = new Empleado();
		modelMapper.map(empleadoDto, empleado);
		
		empleado.setDepartamento(null);
		
		return ResponseEntity.status(400).body("Error al insertar datos de empleado");
	
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/eliminar/{idEmpleado}")//probado y funcionando
	
	public ResponseEntity<?> borrarUno(@PathVariable ("idEmpleado") int idEmpleado){
		
		Empleado empleado = empleadoService.buscarUno(idEmpleado);
		
		if (empleado != null) {
			empleadoService.deleteOne(idEmpleado);
			return ResponseEntity.status(200).body("Empleado eliminado correctamente");
		}
			
		return ResponseEntity.status(400).body("Error al borrar empleado");
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/todos")//probado y funcionando
	public ResponseEntity<?> listarEmpleados(){
		
		List<Empleado> lista = empleadoService.buscarTodos();
		
		
		if (!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);
		
		else
		return ResponseEntity.status(400).body("La lista está vacía");	
	
	
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/porDepto/{idDepartamento}")//probado y funcionando
	public ResponseEntity<?> buscarUno(@PathVariable ("idDepartamento") int idDepartamento){
		
		List<Empleado> lista = empleadoService.buscarPorDepto(idDepartamento);
		
		
		if (!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);
		
		else
		return ResponseEntity.status(400).body("La lista está vacía");	
	
	
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	}
	
	
	
	
