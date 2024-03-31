package restsofa.restcontroller;

import java.util.ArrayList;
import java.util.Date;
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

import restsofa.modelo.DTO.DetallePedidoDto;
import restsofa.modelo.DTO.EstadoPedidoDto;
import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.EstadoPedido;
import restsofa.service.DetallePedidoService;
import restsofa.service.EstadoService;
import restsofa.service.PedidoService;
import restsofa.service.SofaService;

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
	private ModelMapper modelMapper;
	
	/*
	 * Método que devuelve todos los estados de pedido
	 */

	@GetMapping({ "", "/" })
	public ResponseEntity<?> todos() {

		try {
			List<DetallePedido> lista = detPedService.buscarTodosDetPed();

			List<DetallePedidoDto> listaDto = new ArrayList<>();
			for (DetallePedido detalle : lista) {
				listaDto.add(modelMapper.map(detalle, DetallePedidoDto.class));
			}

			return ResponseEntity.ok(listaDto.isEmpty() ? "No hay detalles de pedido disponibles" : listaDto);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Error al cargar la lista de detalle de pedido");
		}
	}
	
	/*
	 * Método que devuelve un detalle de pedido
	 */

	@GetMapping("/{idDePed}")
	public ResponseEntity<?> uno(@PathVariable int idDetalle) {

		DetallePedido detalle = detPedService.buscarDetPed(idDetalle);

		if (detalle != null) {

			DetallePedidoDto detalleDto = modelMapper.map(detalle, DetallePedidoDto.class);

			return ResponseEntity.status(200).body(detalleDto);
		} else
			return ResponseEntity.status(400).body("Error, no se encuentra el detalle de pedido");
	}
	
	/*
	 * Método que da de alta un detalle de pedido
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody DetallePedidoDto detalleDto) {

		DetallePedido detalle = new DetallePedido();
		modelMapper.map(detalleDto, detalle);
		
		detalle.setEstado(estadoService.buscarEstado(detalleDto.getIdEstado()));
		detalle.setPedido(pedidoService.buscarPedido(detalleDto.getIdPedido()));
		detalle.setSofa(sofaService.buscarSofa(detalleDto.getIdSofa()));
		detalle.setFecha(new Date());
		detalle.setCantidad(detalleDto.getCantidad());
		detalle.setPlazas(detalleDto.getPlazas());
		detalle.setPrecio(detalleDto.getPrecio());
		detalle.setDensCojin(detalleDto.getDensCojin());		

		if (detPedService.altaDetPed(detalle) != null) {
			detalleDto.setIdDePed(detalle.getIdDePed());
			return ResponseEntity.status(200).body("Detalle de pedido procesado correctamente " + detalle);
		} else
			return ResponseEntity.status(400).body("Error al procesar el detalle de pedido");
	}
	
	/*
	 * Método que modifica un estado de pedido
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
	 * Método que borra un detalle de pedido
	 */

	@DeleteMapping("/borrar/{idDePed}")
	public ResponseEntity<?> borrar(@PathVariable int idDetalle) {

		DetallePedido detalle = detPedService.buscarDetPed(idDetalle);

		if (detalle != null) {
			detPedService.borrarDetPed(idDetalle);
			return ResponseEntity.status(200).body("Detalle de pedido eliminado correctamente");
		} else
			return ResponseEntity.status(400).body("Detalle de pedido no se ha podido eliminar");
	}

}
