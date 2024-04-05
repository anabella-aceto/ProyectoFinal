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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.DTO.TareaDto;
import restsofa.modelo.entities.Tarea;
import restsofa.service.DepartamentoService;
import restsofa.service.EmpleadoService;
import restsofa.service.EstadoService;
import restsofa.service.PedidoService;
import restsofa.service.TareaService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tarea")

public class TareaRestController {

	@Autowired
	private TareaService tareaService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private DepartamentoService depService;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EstadoService estadoService;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * Método que devuelve todos las tareas
	 */

	@GetMapping({ "", "/" })
	public ResponseEntity<?> todos() {

		try {
			List<Tarea> lista = tareaService.buscarTodasTareas();

			List<TareaDto> listaDto = new ArrayList<>();
			for (Tarea tarea : lista) {
				listaDto.add(modelMapper.map(tarea, TareaDto.class));
			}

			return ResponseEntity.ok(lista.isEmpty() ? "No hay tareas disponibles" : lista);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Error al cargar la lista de tareas");
		}
	}

	/*
	 * Método que devuelve una tarea
	 */

	@GetMapping("/{idTarea}")
	public ResponseEntity<?> uno(@PathVariable int idTarea) {

		Tarea tarea = tareaService.buscarTarea(idTarea);

		if (tarea != null) {

			TareaDto tareaDto = modelMapper.map(tarea, TareaDto.class);

			return ResponseEntity.status(200).body(tareaDto);
		} else
			return ResponseEntity.status(400).body("Error, no se encuentra la tarea");
	}

	/*
	 * Método que da de alta una tarea
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody TareaDto tareaDto) {

		Tarea tarea = new Tarea();
		modelMapper.map(tareaDto, tarea);

		tarea.setEmpleado(empleadoService.buscarUno(tareaDto.getIdEmpleado()));
		tarea.setDepartamento(depService.buscarUno(tareaDto.getIdDepartamento()));
		tarea.setPedido(pedidoService.buscarPedido(tareaDto.getIdPedido()));
		tarea.setEstado(estadoService.buscarEstado(tareaDto.getIdEstado()));

		if (tareaService.altaTarea(tarea) != null) {
			tareaDto.setIdTarea(tarea.getIdTarea());
			
			return ResponseEntity.status(200).body("Tarea procesada correctamente " + tarea);
		} else
			return ResponseEntity.status(400).body("Error al procesar la tarea");
	}

	/*
	 * Método que modifica una tarea
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody TareaDto tareaDto) {

		Tarea tarea = tareaService.buscarTarea(tareaDto.getIdTarea());

		if (tarea != null) {
			modelMapper.map(tareaDto, TareaDto.class);
			tarea.setEmpleado(empleadoService.buscarUno(tareaDto.getIdEmpleado()));
			tarea.setDepartamento(depService.buscarUno(tareaDto.getIdDepartamento()));
			tarea.setPedido(pedidoService.buscarPedido(tareaDto.getIdPedido()));
			tarea.setEstado(estadoService.buscarEstado(tareaDto.getIdEstado()));

			tareaService.modifTarea(tarea);
			return ResponseEntity.status(200).body("Tarea modificada correctamente");
		} else
			return ResponseEntity.status(400).body("No se puede modificar la tarea");
	}

	/*
	 * Método que borra una tarea
	 */

	@DeleteMapping("/borrar/{idTarea}")
	public ResponseEntity<?> borrar(@PathVariable int idTarea) {

		Tarea tarea = tareaService.buscarTarea(idTarea);

		if (tarea != null) {
			tareaService.borrarTarea(idTarea);
			return ResponseEntity.status(200).body("Tarea eliminada correctamente");
		} else
			return ResponseEntity.status(400).body("Tarea no se ha podido eliminar");
	}
	
	/*
	 * Método que lista las tareas de un empleado
	 */
	
	@GetMapping("/empleado/{idEmpleado}")
	public ResponseEntity<?> filtrarPorEmpleado(@PathVariable (name="idEmpleado") int idEmpleado) {
		 
		List<Tarea> lista = tareaService.buscarPorIdEmpleado(idEmpleado);
		
		if(!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);
		 
	 	return  ResponseEntity.status(400).body("No se encuentran tareas para el empleado ingresado");
	 }
	 
	
	
	
	
	
	

}
