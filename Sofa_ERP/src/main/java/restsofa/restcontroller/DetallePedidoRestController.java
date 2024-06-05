package restsofa.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
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
			return ResponseEntity.ok(new ArrayList<>());
		}
	}

	/**
	 * Método que obtiene un detalle de pedido por su identificador único.
	 *
	 * @param idDetalle El identificador único del detalle de pedido a buscar.
	 * 
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

				return ResponseEntity.status(HttpStatus.OK).body(detalleDto);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("El detalle de pedido con ID " + idDePed + " no se encontró.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Crea un nuevo detalle de pedido a partir de los datos proporcionados en el
	 * DTO.
	 * 
	 * @param detalleDto El DTO del detalle de pedido a crear.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         creación del detalle de pedido.
	 *         **/
@PostMapping("/alta")
public ResponseEntity<?> alta(@RequestBody DetallePedidoDto detalleDto) {
    Map<String, Object> response = new HashMap<>();
    try {
        // Buscar el sofá
        Sofa sofa = sofaService.buscarSofa(detalleDto.getIdSofa());
        if (sofa == null) {
            response.put("error", "Sofá no encontrado con ID: " + detalleDto.getIdSofa());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        int cantidadSofas = detalleDto.getCantidad();
        if (cantidadSofas <= 0) {
            response.put("error", "La cantidad de sofás debe ser mayor a cero");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        List<SofaMaterial> sofaMateriales = sofaMaterialService.buscarPorSofa(sofa.getIdSofa());

        List<String> mensajesInsuficientes = new ArrayList<>();

        for (SofaMaterial sofaMaterial : sofaMateriales) {
            Material material = sofaMaterial.getMaterial();
            double cantidadDisponible = material.getCantidad();
            double cantidadUtilizada = sofaMaterial.getCantidadUtilizada() * cantidadSofas;

            if (cantidadDisponible < cantidadUtilizada) {
                double cantidadFaltante = cantidadUtilizada - cantidadDisponible;
                mensajesInsuficientes.add("Stock insuficiente de " + material.getDescripcion() +
                        "- Cantidad faltante: " + cantidadFaltante + " " + material.getUnidadMedida());
            }
        }

        if (!mensajesInsuficientes.isEmpty()) {
            response.put("errors", mensajesInsuficientes);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Descontar materiales utilizados
        for (SofaMaterial sofaMaterial : sofaMateriales) {
            Material material = sofaMaterial.getMaterial();
            double cantidadUtilizada = sofaMaterial.getCantidadUtilizada() * cantidadSofas;
            double cantidadDisponible = material.getCantidad();
            material.setCantidad(cantidadDisponible - cantidadUtilizada);
            materialService.updateOne(material);
        }

        // Crear el detalle de pedido y las tareas correspondientes
        Pedido pedido = pedidoService.buscarPedido(detalleDto.getIdPedido());
        if (pedido == null) {
            response.put("error", "Pedido no encontrado con ID: " + detalleDto.getIdPedido());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        for (int i = 0; i < cantidadSofas; i++) {
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

                for (Departamento departamento : departamentos) {
                    if (departamento != null) {
                        Tarea tarea = new Tarea();
                        tarea.setDetalle(detPedService.buscarDetPed(detalleGuardado.getIdDePed()));
                        tarea.setDepartamento(departamento);
                        tarea.setEstado(estadoService.buscarEstado(5));
                        tarea.setEmpleado(null);
                        tarea.setFecha(detalleDto.getFecha());
                        tareaService.altaTarea(tarea);
                    }
                }
            }
        }

        response.put("message", "Detalle de pedido creado exitosamente.");
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        response.put("error", "Error al procesar la solicitud.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
	/**
	 * Elimina un detalle de pedido con el identificador proporcionado.
	 *
	 * @param idDetalle El identificador único del detalle de pedido a eliminar.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/eliminar/{idDePed}")
	public ResponseEntity<?> borrar(@PathVariable int idDePed) {
	    try {
	        DetallePedido detalle = detPedService.buscarDetPed(idDePed);

	        if (detalle != null) {	         
	            List<Tarea> lista = tareaService.buscarPorDetalle(idDePed);
	            for (Tarea tarea : lista) {	            
	                tareaService.borrarTarea(tarea.getIdTarea()); 	             
	            }

	            List<SofaMaterial> sofaMaterial = sofaMaterialService.buscarPorSofa(detalle.getSofa().getIdSofa());
	            for (SofaMaterial sm : sofaMaterial) {
	                Material material = sm.getMaterial();
	                double stock = material.getCantidad();
	                double cantidadUtilizada = sm.getCantidadUtilizada();
	                double restaurar = stock + cantidadUtilizada;
	                material.setCantidad(restaurar);
	                materialService.updateOne(material);
	            }

	            if (detPedService.borrarDetPed(idDePed)) {
	                return ResponseEntity.status(HttpStatus.OK).body("Detalle de pedido eliminado correctamente");
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el detalle de pedido");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle de pedido no encontrado");
	        }
	    } catch (Exception e) {           
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al eliminar el detalle de pedido: " + e.getMessage());
	    }
	}


	/**
	 * Filtra los detalles de pedido por el identificador de pedido proporcionado.
	 *
	 * @param idPedido El identificador único del pedido para filtrar los detalles
	 *                 de pedido.
	 *                 
	 * @return ResponseEntity con el detalle de pedido encontrado si existe, o un
	 *         mensaje de error si no.
	 */
	@GetMapping("/porPedido/{idPedido}") 
	public ResponseEntity<?> filtrarPorPedido(@PathVariable(name = "idPedido") int idPedido) {
		try {
			List <DetallePedido> detallePedido = detPedService.buscarPorIdPedido(idPedido);

			if (detallePedido != null) {
				return ResponseEntity.status(HttpStatus.OK).body(detallePedido);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontró ningún detalle de pedido para el pedido con ID: " + idPedido);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar detalles de pedido: " + e.getMessage());
		}
	}

	/**
	 * Modifica un detalle de pedido con los datos proporcionados en el DTO.
	 *
	 * @param detalleDto El DTO del detalle de pedido a modificar.
	 * 
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

	        if (detallePedidoDto.getIdSofa() != detalle.getSofa().getIdSofa()) {
	            List<SofaMaterial> sofaMaterialAntiguo = sofaMaterialService.buscarPorSofa(detalle.getSofa().getIdSofa());
	            for (SofaMaterial sm : sofaMaterialAntiguo) {
	                Material material = sm.getMaterial();
	                material.setCantidad(material.getCantidad() + sm.getCantidadUtilizada());
	                materialService.updateOne(material);
	            }

	            List<SofaMaterial> sofaMaterialNuevo = sofaMaterialService.buscarPorSofa(detallePedidoDto.getIdSofa());
	            for (SofaMaterial sm : sofaMaterialNuevo) {
	                Material material = sm.getMaterial();
	                material.setCantidad(material.getCantidad() - sm.getCantidadUtilizada());
	                materialService.updateOne(material);
	            }

	            Sofa nuevoSofa = sofaService.buscarSofa(detallePedidoDto.getIdSofa());

	            if (nuevoSofa == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El nuevo sofá con el ID " + detallePedidoDto.getIdSofa() + " no existe");
	            }

	            detalle.setSofa(nuevoSofa);
	        }

	        detalle.setFecha(detallePedidoDto.getFecha());
	        detalle.setPlazas(detallePedidoDto.getPlazas());
	        detalle.setPrecio(detallePedidoDto.getPrecio());
	        detalle.setDensCojin(detallePedidoDto.getDensCojin());

	        detPedService.modifDetPed(detalle);

	        int cantidad = detallePedidoDto.getCantidad();
	        for (int i = 1; i < cantidad; i++) {
	            DetallePedido nuevoDetalle = new DetallePedido();
	            nuevoDetalle.setPedido(detalle.getPedido());
	            nuevoDetalle.setSofa(detalle.getSofa());
	            nuevoDetalle.setFecha(detalle.getFecha());
	            nuevoDetalle.setCantidad(1);
	            nuevoDetalle.setPlazas(detalle.getPlazas());
	            nuevoDetalle.setPrecio(detalle.getPrecio());
	            nuevoDetalle.setDensCojin(detalle.getDensCojin());

	            detPedService.altaDetPed(nuevoDetalle);
	        }

	        return ResponseEntity.status(HttpStatus.OK).body("Detalle de pedido modificado correctamente");
	    } catch (NoResultException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El detalle de pedido con el ID " + detallePedidoDto.getIdDePed() + " no existe");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el detalle de pedido: " + e.getMessage());
	    }
	}






}
