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
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.NoResultException;
import restsofa.modelo.DTO.DetallePedidoDto;
import restsofa.modelo.entities.Departamento;
import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.Estado;
import restsofa.modelo.entities.Material;
import restsofa.modelo.entities.Pedido;
import restsofa.modelo.entities.Sofa;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.modelo.entities.Tarea;
import restsofa.service.DepartamentoService;
import restsofa.service.DetallePedidoService;
import restsofa.service.EmpleadoService;
import restsofa.service.EstadoService;
import restsofa.service.MaterialService;
import restsofa.service.PedidoService;
import restsofa.service.SofaMaterialService;
import restsofa.service.SofaService;
import restsofa.service.TareaService;

/**
 * Controlador para la gestión de detallePedido.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/detallepedido")

public class DetallePedidoRestController {

	@Autowired
	private DetallePedidoService detPedService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private SofaService sofaService;

	@Autowired
	private SofaMaterialService sofaMaterialService;

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private TareaService tareaService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Método que obtiene todos los detalles de pedido.
	 *
	 * @return ResponseEntity con la lista de todos los detalles de pedido si se
	 *         obtienen correctamente, o una lista vacía si no hay elementos en la
	 *         lista.
	 */
	@GetMapping("/todos")
	public ResponseEntity<List<DetallePedidoDto>> todos() {
		try {
			List<DetallePedido> lista = detPedService.buscarTodosDetPed();

			List<DetallePedidoDto> listaDto = new ArrayList<>();

			for (DetallePedido detalle : lista) {				
				listaDto.add(modelMapper.map(detalle, DetallePedidoDto.class));
			}

			return ResponseEntity.ok(listaDto);
		} catch (Exception e) {
			// En caso de error, devuelve una lista vacía
			return ResponseEntity.ok(new ArrayList<>());
		}
	}

	/**
	 * Método que obtiene un detalle de pedido por su identificador único.
	 *
	 * @param idDetalle El identificador único del detalle de pedido a buscar.
	 * @return ResponseEntity con el detalle de pedido encontrado si existe, o un
	 *         mensaje de error si no existe.
	 */
	@GetMapping("/uno/{idDePed}")
	public ResponseEntity<?> uno(@PathVariable int idDePed) {
		try {
			// Buscar el detalle de pedido por su ID
			DetallePedido detalle = detPedService.buscarDetPed(idDePed);

			if (detalle != null) {
				// Si se encuentra el detalle, mapearlo a un DTO para enviar en la respuesta
				DetallePedidoDto detalleDto = new DetallePedidoDto();
				detalleDto.setIdDePed(detalle.getIdDePed());
				detalleDto.setIdPedido(detalle.getPedido().getIdPedido());
				detalleDto.setIdSofa(detalle.getSofa().getIdSofa());
				detalleDto.setCantidad(detalle.getCantidad());
				detalleDto.setPlazas(detalle.getPlazas());
				detalleDto.setDensCojin(detalle.getDensCojin());
				detalleDto.setFecha(detalle.getFecha());
				detalleDto.setPrecio(detalle.getPrecio());

				// Devolver el detalle de pedido mapeado en el DTO con estado OK
				return ResponseEntity.status(HttpStatus.OK).body(detalleDto);
			} else {
				// Si el detalle de pedido no se encuentra, devolver un mensaje con estado NOT
				// FOUND
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("El detalle de pedido con ID " + idDePed + " no se encontró.");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Crea un nuevo detalle de pedido a partir de los datos proporcionados en el
	 * DTO.
	 * 
	 * @param detalleDto El DTO del detalle de pedido a crear.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         creación del detalle de pedido.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody DetallePedidoDto detalleDto) {
	    try {
	        // Buscar el sofá
	        Sofa sofa = sofaService.buscarSofa(detalleDto.getIdSofa());
	        if (sofa == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Sofá no encontrado con ID: " + detalleDto.getIdSofa());
	        }

	        // Obtener la lista de materiales del sofá
	        List<SofaMaterial> sofaMateriales = sofaMaterialService.buscarPorSofa(sofa.getIdSofa());

	        for (SofaMaterial sofaMaterial : sofaMateriales) {
	            Material material = sofaMaterial.getMaterial();
	            double cantidadDisponible = material.getCantidad();
	            double cantidadUtilizada = sofaMaterial.getCantidadUtilizada();

	            if (cantidadDisponible < cantidadUtilizada) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body("Stock insuficiente para el material: " + material.getNombre());
	            }
	            // Actualizar la cantidad disponible del material en el almacén
	            material.setCantidad(cantidadDisponible - cantidadUtilizada);
	            materialService.updateOne(material);
	        }

	        // Buscar el pedido existente
	        Pedido pedido = pedidoService.buscarPedido(detalleDto.getIdPedido());
	        if (pedido == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Pedido no encontrado con ID: " + detalleDto.getIdPedido());
	        }

	        int cantidad = detalleDto.getCantidad(); // Obtener la cantidad del detalle
	        
	        // Crear tantos detalles de pedido como cantidad haya
	        for (int i = 0; i < cantidad; i++) {
	            DetallePedido detallePedido = new DetallePedido();
	            detallePedido.setPedido(pedido);
	            detallePedido.setSofa(sofa);
	            detallePedido.setFecha(detalleDto.getFecha());
	            detallePedido.setCantidad(1); // Fijar la cantidad en 1
	            detallePedido.setPlazas(detalleDto.getPlazas());
	            detallePedido.setPrecio(detalleDto.getPrecio());
	            detallePedido.setDensCojin(detalleDto.getDensCojin());

	            DetallePedido detalleGuardado = detPedService.altaDetPed(detallePedido);

	            if (detalleGuardado != null) {
	                List<Departamento> departamentos = departamentoService.listarTodos();
	                List<Tarea> tareasCreadas = new ArrayList<>();

	                for (Departamento departamento : departamentos) {
	                    if (departamento != null) {
	                        Tarea tarea = new Tarea();
	                        tarea.setDetalle(detPedService.buscarDetPed(detalleGuardado.getIdDePed()));
	                        tarea.setDepartamento(departamento);
	                        tarea.setEstado(estadoService.buscarEstado(5));
	                        tarea.setEmpleado(null);
	                        tarea.setFecha(detalleDto.getFecha());
	                        Tarea tareaGuardada = tareaService.altaTarea(tarea);
	                        tareasCreadas.add(tareaGuardada);
	                    }
	                }
	            }
	        }

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body("Detalle(s) de pedido procesado(s) correctamente.");
	    } catch (Exception e) {
	        // Capturar cualquier excepción y devolver un error interno del servidor
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al procesar el detalle de pedido: " + e.getMessage());
	    }
	}


	/**
	 * Modifica un detalle de pedido con los datos proporcionados en el DTO.
	 *
	 * @param detalleDto El DTO del detalle de pedido a modificar.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         modificación del detalle de pedido.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody DetallePedidoDto detallePedidoDto) {
	    try {
	        DetallePedido detalle = detPedService.findByDetalleYPedido(detallePedidoDto.getIdDePed(), detallePedidoDto.getIdPedido());
	        
	        if (detalle == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El detalle de pedido con el ID " + detallePedidoDto.getIdDePed() + " no existe");
	        }

	        List<SofaMaterial> sofaMateriales = sofaMaterialService.buscarPorSofa(detalle.getSofa().getIdSofa());
	        
	        for (SofaMaterial sofaMaterial : sofaMateriales) {
	            Material material = materialService.findById(sofaMaterial.getIdSofaMateriales());	 	        	            
	            double cantidadUtilizada = sofaMaterial.getCantidadUtilizada();
	            double stock = material.getCantidad();
	            material.setCantidad(stock + cantidadUtilizada);
	            materialService.updateOne(material);
	        }
	        
	        boolean restaurarMateriales = (detalle.getSofa().getIdSofa() != detallePedidoDto.getIdSofa());

	        if (restaurarMateriales) {
	            int materialRestaurado = materialService.restaurarMateriales(detallePedidoDto.getIdPedido(), detalle.getSofa().getIdSofa(), detalle.getIdDePed());

	            if (materialRestaurado != 1) {
	                List<Tarea> listaTareas = tareaService.buscarPorDetalle(detallePedidoDto.getIdDePed());

	                for (Tarea tarea : listaTareas) {
	                    tarea.setEstado(estadoService.buscarEstado(4));
	                }

	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al restaurar los materiales");
	            }
	        }      
	        
	        detPedService.modifDetPed(detalle);

	        return ResponseEntity.status(HttpStatus.OK).body("Detalle de pedido modificado correctamente");
	    } catch (NoResultException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El detalle de pedido con el ID " + detallePedidoDto.getIdDePed() + " no existe");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el detalle de pedido: " + e.getMessage());
	    }
	}


	/**
	 * Elimina un detalle de pedido con el identificador proporcionado.
	 *
	 * @param idDetalle El identificador único del detalle de pedido a eliminar.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
    @DeleteMapping("/eliminar/{idDePed}")
    public ResponseEntity<?> borrar(@PathVariable int idDePed) {
        try {
            // Buscar el detalle de pedido por su ID
            DetallePedido detalle = detPedService.buscarDetPed(idDePed);

            // Verificar si el detalle de pedido existe
            if (detalle != null) {
                List<Tarea> lista = tareaService.buscarPorDetalle(idDePed);

                // Eliminar todas las tareas asociadas
                for (Tarea tarea : lista) {
                    tareaService.borrarTarea(tarea.getIdTarea());
                }

                // Ahora intentamos eliminar el detalle de pedido después de eliminar todas las tareas asociadas
                if (detPedService.borrarDetPed(idDePed)) {
                    List<SofaMaterial> sofaMaterial = sofaMaterialService.buscarPorSofa(detalle.getSofa().getIdSofa());
                    for (SofaMaterial sm : sofaMaterial) {
                        materialService.restaurarMateriales(detalle.getIdDePed(), detalle.getPedido().getIdPedido(), detalle.getSofa().getIdSofa());
                    }
                    // Si se eliminó correctamente el detalle de pedido, retornar una respuesta exitosa
                    return ResponseEntity.status(HttpStatus.OK).body("Detalle de pedido eliminado correctamente");
                } else {
                    // Si no se pudo eliminar el detalle de pedido por alguna razón, retornar un error
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el detalle de pedido");
                }
            } else {
                // Si el detalle de pedido no existe, retornar un error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de pedido no encontrado");
            }
        } catch (Exception e) {
            // Capturar cualquier excepción y devolver un mensaje indicando la razón específica por la cual no se pudo eliminar el detalle de pedido
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el detalle de pedido: " + e.getMessage());
        }
    }




	/**
	 * Filtra los detalles de pedido por el identificador de pedido proporcionado.
	 *
	 * @param idPedido El identificador único del pedido para filtrar los detalles
	 *                 de pedido.
	 * @return ResponseEntity con el detalle de pedido encontrado si existe, o un
	 *         mensaje de error si no.
	 */
	@GetMapping("/porPedido/{idPedido}") // probado y funcionando
	public ResponseEntity<?> filtrarPorPedido(@PathVariable(name = "idPedido") int idPedido) {
		try {
			// Buscar el detalle de pedido por el ID del pedido
			DetallePedido detallePedido = detPedService.buscarPorPedido(idPedido);

			// Verificar si se encontró el detalle de pedido
			if (detallePedido != null) {
				// Si se encontró, devolver el detalle de pedido con un estado OK
				return ResponseEntity.status(HttpStatus.OK).body(detallePedido);
			} else {
				// Si no se encontró, devolver un error con un mensaje correspondiente
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontró ningún detalle de pedido para el pedido con ID: " + idPedido);
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar detalles de pedido: " + e.getMessage());
		}
	}

}
