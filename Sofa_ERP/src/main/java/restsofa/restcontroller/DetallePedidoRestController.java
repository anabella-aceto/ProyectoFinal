package restsofa.restcontroller;

import java.util.ArrayList;
import java.util.List;

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

import restsofa.modelo.DTO.DetallePedidoDto;
import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.Material;
import restsofa.modelo.entities.Pedido;
import restsofa.modelo.entities.Sofa;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.service.DetallePedidoService;
import restsofa.service.EstadoService;
import restsofa.service.MaterialService;
import restsofa.service.PedidoService;
import restsofa.service.SofaMaterialService;
import restsofa.service.SofaService;

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
	private EstadoService estadoService;

	@Autowired
	private SofaMaterialService sofaMaterialService;
	
	@Autowired
	private MaterialService materialService;	


	/**
	 * Método que obtiene todos los detalles de pedido.
	 *
	 * @return ResponseEntity con la lista de todos los detalles de pedido si se
	 *         obtienen correctamente, o un mensaje de error si no hay elementos en
	 *         la lista.
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> todos() {
	    try {
	        List<DetallePedido> lista = detPedService.buscarTodosDetPed();

	        List<DetallePedidoDto> listaDto = new ArrayList<>();

	        for (DetallePedido detalle : lista) {
	            DetallePedidoDto detalleDto = new DetallePedidoDto();

	            detalleDto.setIdDePed(detalle.getIdDePed());
	            detalleDto.setIdPedido(detalle.getPedido().getIdPedido());
	            detalleDto.setIdSofa(detalle.getSofa().getIdSofa());
	            detalleDto.setCantidad(detalle.getCantidad());
	            detalleDto.setPlazas(detalle.getPlazas());
	            detalleDto.setDensCojin(detalle.getDensCojin());
	            detalleDto.setFecha(detalle.getFecha());
	            detalleDto.setPrecio(detalle.getPrecio());
	            detalleDto.setIdEstado(detalle.getEstado().getIdEstado());

	            listaDto.add(detalleDto);
	        }

	        return ResponseEntity.ok(listaDto.isEmpty() ? "No hay detalles de pedido disponibles" : listaDto);
	    } catch (Exception e) {
	        // Captura cualquier excepción y devuelve un estado 500 (INTERNAL_SERVER_ERROR)
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al cargar la lista de detalle de pedido: " + e.getMessage());
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
	            detalleDto.setIdEstado(detalle.getEstado().getIdEstado());

	            // Devolver el detalle de pedido mapeado en el DTO con estado OK
	            return ResponseEntity.status(HttpStatus.OK).body(detalleDto);
	        } else {
	            // Si el detalle de pedido no se encuentra, devolver un mensaje con estado NOT FOUND
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El detalle de pedido con ID " + idDePed + " no se encontró.");
	        }
	    } catch (Exception e) {
	        // Capturar cualquier excepción y devolver un error interno del servidor
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
	    }
	}


	/**
	 * Crea un nuevo detalle de pedido a partir de los datos proporcionados en el DTO.
	 * 
	 * @param detalleDto El DTO del detalle de pedido a crear.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         creación del detalle de pedido.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody DetallePedidoDto detalleDto) {
	    try {
	        Pedido pedido = pedidoService.buscarPedido(detalleDto.getIdPedido());
	        Sofa sofa = sofaService.buscarSofa(detalleDto.getIdSofa());

	        // Obtener la lista de materiales del sofá
	        List<SofaMaterial> sofaMateriales = sofaMaterialService.buscarPorSofa(sofa.getIdSofa());

	        for (SofaMaterial sofaMaterial : sofaMateriales) {
	            Material material = sofaMaterial.getMaterial();
	            int cantidadUtilizada = sofaMaterial.getCantidadUtilizada();
	            int cantidadDisponible = (int) material.getCantidad();

	            if (cantidadDisponible < cantidadUtilizada) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body("Stock insuficiente para el material: " + material.getNombre());
	            }
	            // Actualizar la cantidad disponible del material en el almacén
	            material.setCantidad(cantidadDisponible - cantidadUtilizada);
	            materialService.updateOne(material);
	        }

	        // Crear el detalle de pedido y dar de alta
	        DetallePedido detallePedido = new DetallePedido();
	        detallePedido.setEstado(pedido.getEstado());
	        detallePedido.setPedido(pedido);
	        detallePedido.setSofa(sofa);
	        detallePedido.setFecha(pedido.getFecha());
	        detallePedido.setCantidad(detalleDto.getCantidad());
	        detallePedido.setPlazas(detalleDto.getPlazas());
	        detallePedido.setPrecio(detalleDto.getPrecio());
	        detallePedido.setDensCojin(detalleDto.getDensCojin());

	        detPedService.altaDetPed(detallePedido);

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body("Detalle de pedido procesado correctamente " + detallePedido);
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
	public ResponseEntity<?> modificar(@RequestBody DetallePedidoDto detalleDto) {
	    try {
	        // Buscar el detalle de pedido por su ID
	        DetallePedido detalle = detPedService.buscarDetPed(detalleDto.getIdDePed());

	        // Verificar si el detalle de pedido existe
	        if (detalle != null) {
	            // Actualizar los datos del detalle de pedido con los datos del DTO
	            detalle.setEstado(estadoService.buscarEstado(detalleDto.getIdEstado()));
	            detalle.setPedido(pedidoService.buscarPedido(detalleDto.getIdPedido()));
	            detalle.setSofa(sofaService.buscarSofa(detalleDto.getIdSofa()));
	            detalle.setFecha(detalleDto.getFecha());
	            detalle.setCantidad(detalleDto.getCantidad());
	            detalle.setPlazas(detalleDto.getPlazas());
	            detalle.setPrecio(detalleDto.getPrecio());
	            detalle.setDensCojin(detalleDto.getDensCojin());

	            // Guardar los cambios en el detalle de pedido
	            detPedService.modifDetPed(detalle);

	            // Devolver una respuesta exitosa con un mensaje indicando el éxito de la modificación
	            return ResponseEntity.status(HttpStatus.OK)
	                    .body("Detalle de pedido modificado correctamente");
	        } else {
	            // Si el detalle de pedido no existe, devolver un error con un mensaje correspondiente
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("El detalle de pedido con el ID " + detalleDto.getIdDePed() + " no existe");
	        }
	    } catch (Exception e) {
	        // Capturar cualquier excepción y devolver un error interno del servidor
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al modificar el detalle de pedido: " + e.getMessage());
	    }
	}


	/**
	 * Elimina un detalle de pedido con el identificador proporcionado.
	 *
	 * @param idDetalle El identificador único del detalle de pedido a eliminar.
	 * @return ResponseEntity con un mensaje indicando el resultado de la eliminación.
	 */
	@DeleteMapping("/eliminar/{idDePed}")
	public ResponseEntity<?> borrar(@PathVariable int idDePed) {
	    try {
	        // Buscar el detalle de pedido por su ID
	        DetallePedido detalle = detPedService.buscarDetPed(idDePed);

	        // Verificar si el detalle de pedido existe
	        if (detalle != null) {
	            // Eliminar el detalle de pedido
	            detPedService.borrarDetPed(idDePed);
	            // Devolver una respuesta exitosa con un mensaje indicando el éxito de la eliminación
	            return ResponseEntity.status(HttpStatus.OK)
	                    .body("Detalle de pedido eliminado correctamente");
	        } else {
	            // Si el detalle de pedido no existe, devolver un error con un mensaje correspondiente
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Detalle de pedido no se ha podido eliminar");
	        }
	    } catch (Exception e) {
	        // Capturar cualquier excepción y devolver un error interno del servidor
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al eliminar el detalle de pedido: " + e.getMessage());
	    }
	}


	/**
	 * Filtra los detalles de pedido por el identificador de pedido proporcionado.
	 *
	 * @param idPedido El identificador único del pedido para filtrar los detalles de pedido.
	 * @return ResponseEntity con el detalle de pedido encontrado si existe, o un mensaje de error si no.
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
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún detalle de pedido para el pedido con ID: " + idPedido);
	        }
	    } catch (Exception e) {
	        // Capturar cualquier excepción y devolver un error interno del servidor
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar detalles de pedido: " + e.getMessage());
	    }
	}

}
