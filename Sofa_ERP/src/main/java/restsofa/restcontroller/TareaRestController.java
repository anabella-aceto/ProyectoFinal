package restsofa.restcontroller;

import java.util.ArrayList;
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

import restsofa.modelo.DTO.TareaDto;
import restsofa.modelo.entities.Pedido;
import restsofa.modelo.entities.Tarea;
import restsofa.service.DepartamentoService;
import restsofa.service.EmpleadoService;
import restsofa.service.EstadoService;
import restsofa.service.PedidoService;
import restsofa.service.TareaService;

/**
 * Controlador para la gestión de tareas.
 */

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

	/**
	 * Método que devuelve todas las tareas.
	 *
	 * @return ResponseEntity con la lista de tareas si se pudo cargar
	 *         correctamente, o un mensaje de error si no se cargó.
	 */
	@GetMapping({ "", "/todas" })
	public ResponseEntity<?> todos() {
		try {
			List<Tarea> lista = tareaService.buscarTodasTareas();
			List<TareaDto> listaDto = new ArrayList<>();
			for (Tarea tarea : lista) {
				listaDto.add(modelMapper.map(tarea, TareaDto.class));
			}
			return ResponseEntity.ok(listaDto.isEmpty() ? "No hay tareas disponibles" : listaDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al cargar la lista de tareas: " + e.getMessage());
		}
	}

	/**
	 * Método que permite obtener una tarea por su identificador.
	 *
	 * @param idTarea El identificador único de la tarea a buscar.
	 * @return ResponseEntity con la tarea encontrada si existe, o un mensaje de
	 *         error si no existe.
	 */
	@GetMapping("/una/{idTarea}")
	public ResponseEntity<?> una(@PathVariable int idTarea) {
		try {
			Tarea tarea = tareaService.buscarTarea(idTarea);
			if (tarea != null) {
				TareaDto tareaDto = modelMapper.map(tarea, TareaDto.class);
				return ResponseEntity.status(HttpStatus.OK).body(tareaDto);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra la tarea");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar la tarea: " + e.getMessage());
		}
	}

	/**
	 * Método que permite crear una tarea.
	 * 
	 * @param tareaDto El DTO de la tarea a dar de alta.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         alta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody TareaDto tareaDto) {
		try {
			Tarea tarea = new Tarea();
			modelMapper.map(tareaDto, tarea);
			tarea.setEmpleado(empleadoService.buscarUno(tareaDto.getIdEmpleado()));
			tarea.setDepartamento(depService.buscarUno(tareaDto.getIdDepartamento()));
			tarea.setPedido(pedidoService.buscarPedido(tareaDto.getIdPedido()));
			tarea.setEstado(estadoService.buscarEstado(tareaDto.getIdEstado()));

			Tarea tareaGuardada = tareaService.altaTarea(tarea);
			if (tareaGuardada != null) {
				tareaDto.setIdTarea(tareaGuardada.getIdTarea());
				return ResponseEntity.status(HttpStatus.OK).body("Tarea procesada correctamente " + tareaGuardada);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar la tarea");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al dar de alta la tarea: " + e.getMessage());
		}
	}

	/**
	 * Método que modifica una tarea.
	 * 
	 * @param tareaDto El DTO de la tarea con la información actualizada.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody TareaDto tareaDto) {
		try {
			Tarea tarea = tareaService.buscarTarea(tareaDto.getIdTarea());
			if (tarea != null) {
				modelMapper.map(tareaDto, tarea);
				tarea.setEmpleado(empleadoService.buscarUno(tareaDto.getIdEmpleado()));
				tarea.setDepartamento(depService.buscarUno(tareaDto.getIdDepartamento()));
				tarea.setPedido(pedidoService.buscarPedido(tareaDto.getIdPedido()));
				tarea.setEstado(estadoService.buscarEstado(tareaDto.getIdEstado()));

				tareaService.modifTarea(tarea);
				return ResponseEntity.status(HttpStatus.OK).body("Modificación realizada correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede modificar la tarea");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al modificar la tarea: " + e.getMessage());
		}
	}

	/**
	 * Método que elimina una tarea.
	 * 
	 * @param idTarea El identificador único de la tarea.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/borrar/{idTarea}")
	public ResponseEntity<?> borrar(@PathVariable int idTarea) {
		try {
			Tarea tarea = tareaService.buscarTarea(idTarea);
			if (tarea != null) {
				tareaService.borrarTarea(idTarea);
				return ResponseEntity.status(HttpStatus.OK).body("Tarea eliminada correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarea no se ha podido eliminar");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al eliminar la tarea: " + e.getMessage());
		}
	}

	/**
	 * Método que filtra las tareas asociadas a un empleado por su identificador.
	 *
	 * @param idEmpleado El identificador único del empleado del cual se desean
	 *                   filtrar las tareas.
	 * @return ResponseEntity con una lista de tareas si se encuentran tareas para
	 *         el empleado especificado, o un mensaje de error si no.
	 */
	@GetMapping("/empleado/{idEmpleado}")
	public ResponseEntity<?> filtrarPorEmpleado(@PathVariable(name = "idEmpleado") int idEmpleado) {
		try {
			List<Tarea> lista = tareaService.buscarPorIdEmpleado(idEmpleado);
			if (!lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("No se encuentran tareas para el empleado ingresado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al filtrar las tareas: " + e.getMessage());
		}
	}

	/**
	 * Método que cambia el estado de una tarea.
	 * 
	 * @param idPedido       El identificador del pedido a actualizar.
	 * @param idEmpleado     El identificador del empleado asociado con la tarea.
	 * @param idDepartamento El identificador del departamento asociado con la
	 *                       tarea.
	 * @return ResponseEntity con un mensaje indicando si el cambio de estado se
	 *         realizó correctamente o si hubo algún error.
	 */
	@PutMapping("/estadoTarea")
	public ResponseEntity<?> cambiarEstado(@RequestParam(name = "idPedido") int idPedido,
			@RequestParam(name = "idEmpleado") int idEmpleado,
			@RequestParam(name = "idDepartamento") int idDepartamento) {
		try {
			Pedido pedido = pedidoService.buscarPedido(idPedido);
			if (pedido != null) {
				int tarea = tareaService.altaEstadoTarea(idPedido, idEmpleado, idDepartamento);
				if (tarea == 1) {
					return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el pedido a 'procesando'");
				} else if (tarea == 2) {
					return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el pedido a 'finalizado'");
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al cambiar el estado de la tarea");
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al cambiar el estado de la tarea: " + e.getMessage());
		}
	}
}
