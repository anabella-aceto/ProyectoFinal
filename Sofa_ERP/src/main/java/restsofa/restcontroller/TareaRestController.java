package restsofa.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import restsofa.modelo.DTO.TareaAltaDto;
import restsofa.modelo.DTO.TareaDto;
import restsofa.modelo.entities.Departamento;
import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.Empleado;
import restsofa.modelo.entities.Estado;
import restsofa.modelo.entities.Tarea;
import restsofa.service.DepartamentoService;
import restsofa.service.DetallePedidoService;
import restsofa.service.EmpleadoService;
import restsofa.service.EstadoService;
import restsofa.service.TareaService;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
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
	private DetallePedidoService detallePedidoService;
	
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
	 * 
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
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al buscar la tarea: " + e.getMessage());
	    }
	}

	/**
	 * Método que permite crear la tarea para cada departamento.
	 *
	 * Este método recibe un objeto DTO con los detalles de la tarea a crear. Luego, para cada 
	 * departamento, asigna la tarea a un empleado seleccionado aleatoriamente dentro del 
	 * departamento y guarda la tarea en la base de datos.
	 *
	 * @param tareaAltaDto El DTO de la tarea a dar de alta.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de alta, incluyendo
	 *         las tareas creadas o un mensaje de error si ocurre algún problema.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody TareaAltaDto tareaAltaDto) {
	    List<Departamento> departamentos = depService.listarTodos();
	    Random random = new Random();
	    List<Tarea> tareasCreadas = new ArrayList<>();

	    for (Departamento departamento : departamentos) {
	        try {
	            if (departamento != null) {
	                List<Empleado> empleados = empleadoService.buscarPorDepto(departamento.getIdDepartamento());
	                if (!empleados.isEmpty()) {
	                    Empleado empleadoAleatorio = empleados.get(random.nextInt(empleados.size()));
	                    Tarea tarea = new Tarea();
	                    tarea.setDetalle(detallePedidoService.buscarDetPed(tareaAltaDto.getIdDetalle()));
	                    tarea.setDepartamento(departamento);
	                    tarea.setEstado(estadoService.buscarEstado(0));
	                    tarea.setEmpleado(empleadoAleatorio);
	                    tarea.setFecha(tareaAltaDto.getFecha());
	                    Tarea tareaGuardada = tareaService.altaTarea(tarea);
	                    tareasCreadas.add(tareaGuardada);
	                }
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error al dar de alta la tarea para el departamento: " + e.getMessage());
	        }
	    }

	    return ResponseEntity.status(HttpStatus.OK).body("Tareas creadas correctamente: " + tareasCreadas);
	}

	/**
	 * Modifica una tarea existente en el sistema.
	 * 
	 * Este método recibe un DTO con la información actualizada de una tarea y
	 * actualiza la tarea correspondiente en la base de datos. Se validan las 
	 * relaciones con empleado, departamento, detalle de pedido y estado.
	 * 
	 * @param tareaDto El DTO de la tarea con la información actualizada. Debe 
	 *                 contener el ID de la tarea a modificar y los nuevos datos.
	 *                 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso 
	 *         de modificación. Retorna:
	 *         - 200 OK si la modificación se realizó correctamente.
	 *         - 400 BAD REQUEST si no se encontró la tarea a modificar.
	 *         - 500 INTERNAL SERVER ERROR si ocurrió un error en el servidor durante la modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody TareaDto tareaDto) {
		try {
			Tarea tarea = tareaService.buscarTarea(tareaDto.getIdTarea());
			if (tarea != null) {
				modelMapper.map(tareaDto, tarea);
				tarea.setEmpleado(empleadoService.buscarUno(tareaDto.getIdEmpleado()));
				tarea.setDepartamento(depService.buscarUno(tareaDto.getIdDepartamento()));
				tarea.setDetalle(detallePedidoService.buscarDetPed(tareaDto.getIdDetalle()));
				tarea.setEstado(estadoService.buscarEstado(tareaDto.getIdEstado()));

				tareaService.modifTarea(tarea);
				return ResponseEntity.status(HttpStatus.OK).body("Modificación realizada correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
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
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la eliminación.
	 */
	@DeleteMapping("/borrar/{idTarea}")
	public ResponseEntity<?> borrar(@PathVariable int idTarea) {
	    try {
	        Tarea tarea = tareaService.buscarTarea(idTarea);
	        if (tarea != null) {
	            tareaService.borrarTarea(idTarea);
	            return ResponseEntity.status(HttpStatus.OK).body("Tarea eliminada correctamente");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la tarea especificada");
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
	 *                   
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
	 * Método que filtra las tareas asociadas a un departamento por su identificador.
	 *
	 * @param idDepartamento El identificador único del departamento del cual se desean
	 *                   filtrar las tareas.
	 *                   
	 * @return ResponseEntity con una lista de tareas si se encuentran tareas para
	 *         el departamento especificado, o un mensaje de error si no.
	 *         
	 * @return ResponseEntity donde se muestra la información del estado del detalle solicitado.
	 */
	@GetMapping("/departamento/{idDepartamento}")
	public ResponseEntity<?> filtrarPorDepartamento(@PathVariable(name = "idDepartamento") int idDepartamento) {
		try {
			List<Tarea> lista = tareaService.buscarPorIdDepartamento(idDepartamento);
			if (!lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("No se encuentran tareas para el departamento ingresado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al filtrar las tareas: " + e.getMessage());
		}
	}
	
	
	/**
	 * Método que toma el ID de un detalle de pedido, recupera las tareas asociadas
	 * y determina su estado basado en los estados de las tareas en diferentes departamentos. El estado
	 * se determina usando reglas de prioridad.
	 * 
	 * @param idDeped El identificador único del detalle de pedido.
	 *           
	 * @return ResponseEntity donde se muestra la información del detalle solicitado del estado de cada 
	 * uno de los departamento.               
	 */
	@GetMapping("/porEstadoYDetalle/{idDeped}")
	public ResponseEntity<?> listarporDetalle(@PathVariable int idDeped) {
	    DetallePedido detalle = detallePedidoService.buscarDetPed(idDeped);
	    if (detalle == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DetallePedido no encontrado");
	    }
	    
	    List<Estado> estados = estadoService.buscarTodosEstado();
	    List<Departamento> deptos = depService.listarTodos();
	    List<Tarea> tareas = tareaService.buscarTodasTareas();
	    
	    List<Map<String, Object>> resultado = new ArrayList<>();

	    for (Departamento depto : deptos) {
	        Map<String, Object> tareaInfo = new HashMap<>();
	        tareaInfo.put("departamentoId", depto.getIdDepartamento());
	        tareaInfo.put("departamentoNombre", depto.getNombre());
	        
	        boolean pendiente = false;
	        boolean procesando = false;
	        boolean sinAsignar = false;
	        boolean finalizada = false;

	        for (Tarea tarea : tareas) {
	            if (tarea.getDetalle().getIdDePed() == detalle.getIdDePed() &&
	                tarea.getDepartamento().getIdDepartamento() == depto.getIdDepartamento()) {
	                
	                Estado estado = tarea.getEstado();
	                switch (estado.getIdEstado()) {
	                    case 1:
	                        pendiente = true;
	                        break;
	                    case 2:
	                        procesando = true;
	                        break;
	                    case 3:
	                        finalizada = true;
	                        break;
	                    case 5:
	                        sinAsignar = true;
	                        break;
	                }
	            }
	        }

	        if (finalizada) {
	            tareaInfo.put("estado", "finalizada");
	        } else if (procesando) {
	            tareaInfo.put("estado", "procesando");
	        } else if (sinAsignar && pendiente) {
	            tareaInfo.put("estado", "pendiente");
	        } else if (pendiente) {
	            tareaInfo.put("estado", "pendiente");
	        } else if (sinAsignar) {
	            tareaInfo.put("estado", "sin asignar");
	        } else {
	            tareaInfo.put("estado", "desconocido");
	        }

	        resultado.add(tareaInfo);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}

	
	
	/**
	 * Método que toma el ID de un pedido, recupera las tareas asociadas
	 * y determina el estado general del pedido. El estado se determina usando reglas de prioridad.
	 * 
	 * @param idDeped El identificador único del detalle de pedido.
	 *           
	 * @return ResponseEntity donde se muestra la información del del pedido.               
	 */
	@GetMapping("/estadoPorPedido/{idPedido}")
	public ResponseEntity<?> mostrarEstadoPedido(@PathVariable int idPedido) {
	    List<DetallePedido> lista = detallePedidoService.buscarPorIdPedido(idPedido);

	    if (lista == null || lista.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DetallePedido not found for pedido ID: " + idPedido);
	    }

	    boolean todasTareasFinalizadas = true;
	    boolean todasTareasSinAsignar = true;
	    boolean pendiente = false;
	    boolean procesando = false;
	    boolean cancelada = false;

	    for (DetallePedido detalle : lista) {
	        List<Tarea> tareas = tareaService.buscarPorDetalle(detalle.getIdDePed());

	        if (tareas == null || tareas.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tasks found for detallePedido ID: " + detalle.getIdDePed());
	        }

	        for (Tarea tarea : tareas) {
	            int estado = tarea.getEstado().getIdEstado();

	            if (estado == 1) {
	                pendiente = true;
	                todasTareasSinAsignar = false;
	                todasTareasFinalizadas = false;
	            } else if (estado == 2) {
	                procesando = true;
	                todasTareasSinAsignar = false;
	                todasTareasFinalizadas = false;
	            } else if (estado != 3) {
	                todasTareasFinalizadas = false;
	            } else if (estado == 4) {
	                cancelada = true;
	                todasTareasFinalizadas = false;
	            } else if (estado != 5) {
	                todasTareasSinAsignar = false;
	            }
	        }
	    }

	    if (todasTareasSinAsignar) {
	        return ResponseEntity.ok("Sin asignar");
	    } else if (cancelada) {
	        return ResponseEntity.ok("Cancelado");
	    } else if (procesando) {
	        return ResponseEntity.ok("Procesando");
	    } else if (pendiente) {
	        return ResponseEntity.ok("Pendiente");
	    } else if (todasTareasFinalizadas) {
	        return ResponseEntity.ok("Finalizado");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
	    }
	}

		  
	

	
	/**
	 * Asigna un empleado a una tarea en un departamento específico.
	 *
	 * @param idDeped     El ID del detalle del pedido.
	 * 
	 * @param idTarea     El ID de la tarea.
	 * 
	 * @param idEmpleado  El ID del empleado a asignar.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la asignación.
	 *         Devuelve un ResponseEntity.ok si la asignación fue exitosa.
	 *         Devuelve un ResponseEntity con HttpStatus.INTERNAL_SERVER_ERROR si ocurre un error durante el proceso.
	 */
	@PutMapping("/asignarEmpleado/{idTarea}")
	public ResponseEntity<?> asignarEmpleado(@PathVariable(name = "idTarea") int idTarea, @RequestParam(name = "idEmpleado") int idEmpleado){ 
			
		try {
		
			Tarea tarea = tareaService.buscarTarea(idTarea);
			DetallePedido detallePedido = detallePedidoService.buscarDetPed(tarea.getDetalle().getIdDePed());
			//Comprobar si la tarea existe y tiene detalle
			if (detallePedido != null && tarea != null) {
				
				Empleado empleado = empleadoService.buscarUno(idEmpleado);
				
				//Comprobar si el empleado existe
				if (empleado !=null) {
					tarea.setEmpleado(empleado);
					tarea.setEstado(estadoService.buscarEstado(1));
					tareaService.modifTarea(tarea);
				}
				else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("El empleado no existe");
				}
			
			}	
			return ResponseEntity.status(HttpStatus.OK).body(tarea);
				
			}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al asignar un empleado a la tarea: " + e.getMessage());
		}
	}

	/**
	 * Método que cambia el estado de una tarea en orden ascendente.
	 * 
	 * @param idPedido       El identificador del pedido a actualizar.
	 * 
	 * @param idEmpleado     El identificador del empleado asociado con la tarea.
	 * 
	 * @param idDepartamento El identificador del departamento asociado con la
	 *                       tarea.
	 * 
	 * @param idDeped     El identificador del detalle de pedido asociado con la tarea.
	 *                   
	 * @return ResponseEntity con un mensaje indicando si el cambio de estado se
	 *         realizó correctamente o si hubo algún error.
	 */
	@PutMapping("/estadoTarea/{idTarea}/{idEmpleado}/{idDepto}/{idDeped}")
	public ResponseEntity<?> cambiarEstado(@PathVariable(name = "idTarea") int idTarea,
	        @PathVariable(name = "idEmpleado") int idEmpleado, @PathVariable(name = "idDepto") int idDepto,
	        @PathVariable(name = "idDeped") int idDeped) {
	    try {
	        int tarea = tareaService.altaEstadoTarea(idTarea, idEmpleado, idDepto, idDeped);

	        switch (tarea) {
	            case 1:
	                return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el pedido a 'procesando'");
	            case 2:
	                return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el pedido a 'procesando'");
	            case 3:
	                return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el pedido a 'finalizado'");
	            default:
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado de tarea no válido");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al cambiar el estado de la tarea: " + e.getMessage());
	    }
	}


	/**
	 * Método que cambia el estado de una tarea en orden descendente.
	 * 
	 * @param idTarea       El identificador del pedido a actualizar.
	 * 
	 * @param idEmpleado     El identificador del empleado asociado con la tarea.
	 * 
	 * @param idDepartamento El identificador del departamento asociado con la
	 *                       tarea.
	 *                       
	 * @param idDeped     El identificador del detalle de pedido asociado con la tarea.
	 *                       
	 * @return ResponseEntity con un mensaje indicando si el cambio de estado se
	 *         realizó correctamente o si hubo algún error.
	 */
	@PutMapping("/revocarEstadoTarea/{idTarea}/{idEmpleado}/{idDepto}/{idDeped}")
	public ResponseEntity<?> revocarEstado(@PathVariable(name = "idTarea") int idTarea,
	        @PathVariable(name = "idEmpleado") int idEmpleado, @PathVariable(name = "idDepto") int idDepto,
	        @PathVariable(name = "idDeped") int idDeped) {
	    try {	    
	        int tarea = tareaService.revocarEstadoTarea(idTarea, idEmpleado, idDepto, idDeped);

	        switch (tarea) {
	            case 1:
	                return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el pedido a 'sin asignar'");
	            case 2:
	                return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado el pedido a 'pendiente'");
	            default:
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado de tarea no válido");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al cambiar el estado de la tarea: " + e.getMessage());
	    }
	}


}
	

