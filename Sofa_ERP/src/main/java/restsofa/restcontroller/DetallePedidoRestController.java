package restsofa.restcontroller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Método que obtiene todos los detalles de pedido.
	 *
	 * @return ResponseEntity con la lista de todos los detalles de pedido si se
	 *         obtienen correctamente, o un mensaje de error si no hay elementos en
	 *         la lista.
	 */

	@GetMapping({ "/todos" })
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

		DetallePedido detalle = detPedService.buscarDetPed(idDePed);

		if (detalle != null) {

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

			return ResponseEntity.status(200).body(detalleDto);
		} else
			return ResponseEntity.status(400).body("Error, no se encuentra el detalle de pedido");
	}

	/*
	 * Método que permite crear un detalle de pedido
	 *
	 * @param detalleDto El DTO del detalle de pedido a dar de alta.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * alta.
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody DetallePedidoDto detalleDto) {
		
		try {
		
		// modelMapper.map(detalleDto, detalle);

		Pedido pedido = pedidoService.buscarPedido(detalleDto.getIdPedido());

		Sofa sofa = sofaService.buscarSofa(detalleDto.getIdSofa());
		
		//sacar la lista de materiales de un sofá
		
		List<SofaMaterial> sofaMateriales = sofaMaterialService.buscarPorSofa(sofa.getIdSofa());
		
		for (SofaMaterial sofaMaterial : sofaMateriales) {
		    Material material = sofaMaterial.getMaterial();
            int cantidadUtilizada = sofaMaterial.getCantidadUtilizada();
            int cantidadDisponible = (int) material.getCantidad();
         
            if (cantidadDisponible < cantidadUtilizada) {
            	return ResponseEntity.status(200).body("Stock insuficiente para el material: " + material.getNombre());
            }
            //Si hay suficientes unidades para descontar del almacén, procedemos a descontarlas y a guardarlo en la base de datos
            material.setCantidad(cantidadDisponible - cantidadUtilizada);
            materialService.updateOne(material);
		}
		
		DetallePedido detallePedido = new DetallePedido();
		//Una vez descontados los materiales del almacén, damos de alta el detalle del pedido.
		
		detallePedido.setEstado(pedido.getEstado());
		detallePedido.setPedido(pedido);
		detallePedido.setSofa(sofaService.buscarSofa(detalleDto.getIdSofa()));
		detallePedido.setFecha(pedido.getFecha());
		detallePedido.setCantidad(detalleDto.getCantidad());
		detallePedido.setPlazas(detalleDto.getPlazas());
		detallePedido.setPrecio(detalleDto.getPrecio());
		detallePedido.setDensCojin(detalleDto.getDensCojin());

		detPedService.altaDetPed(detallePedido);
		return ResponseEntity.status(200).body("Detalle de pedido procesado correctamente " + detallePedido);
			
		
		
		}
		catch (Exception e){
			return ResponseEntity.status(500).body(e);
		}
		/*if (detPedService.altaDetPed(detalle) != null) {
			detalleDto.setIdDePed(detalle.getIdDePed());
			return ResponseEntity.status(200).body("Detalle de pedido procesado correctamente " + detalle);
		} else
			return ResponseEntity.status(400).body("Error al procesar el detalle de pedido"); */
	}

	/*
	 * Método que modifica un estado de pedido.
	 * 
	 * @param detalleDto El DTO del detalle de pedido a modificar. *
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * modificación.
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody DetallePedidoDto detalleDto) {

		DetallePedido detalle = detPedService.buscarDetPed(detalleDto.getIdDePed());

		if (detalle != null) {
			modelMapper.map(detalleDto, DetallePedidoDto.class);
			detalle.setEstado(estadoService.buscarEstado(detalleDto.getIdEstado()));
			detalle.setPedido(pedidoService.buscarPedido(detalleDto.getIdPedido()));
			detalle.setSofa(sofaService.buscarSofa(detalleDto.getIdSofa()));
			detalle.setFecha(new Date());
			detalle.setCantidad(detalleDto.getCantidad());
			detalle.setPlazas(detalleDto.getPlazas());
			detalle.setPrecio(detalleDto.getPrecio());
			detalle.setDensCojin(detalleDto.getDensCojin());

			detPedService.modifDetPed(detalle);
			return ResponseEntity.status(200).body("Detalle de pedido modificado correctamente");
		} else
			return ResponseEntity.status(400).body("No se puede modificar el detalle de pedido");
	}

	/*
	 * Método que borra un detalle de pedido.
	 * 
	 * @param idDetalle El identificador único del detalle de pedido a eliminar. *
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 * eliminación.
	 */

	@DeleteMapping("/eliminar/{idDePed}")
	public ResponseEntity<?> borrar(@PathVariable int idDetalle) {

		DetallePedido detalle = detPedService.buscarDetPed(idDetalle);

		if (detalle != null) {
			detPedService.borrarDetPed(idDetalle);
			return ResponseEntity.status(200).body("Detalle de pedido eliminado correctamente");
		} else
			return ResponseEntity.status(400).body("Detalle de pedido no se ha podido eliminar");
	}

	/**
	 * Método que filtra los detalles de pedido por identificador de pedido.
	 *
	 * @param idPedido El identificador único del pedido para filtrar los detalles
	 *                 de pedido.
	 * @return ResponseEntity con el detalle de pedido encontrado si existe, o un
	 *         mensaje de error si no.
	 */
	@GetMapping("/porPedido/{idPedido}") // probado y funcionando
	public ResponseEntity<?> filtrarPorPedido(@PathVariable(name = "idPedido") int idPedido) {

		DetallePedido detallePedido = detPedService.buscarPorPedido(idPedido);

		if (detallePedido != null)
			return ResponseEntity.status(200).body(detallePedido);

		else
			return ResponseEntity.status(400).body("Pedido no encontrado");
	}

}
