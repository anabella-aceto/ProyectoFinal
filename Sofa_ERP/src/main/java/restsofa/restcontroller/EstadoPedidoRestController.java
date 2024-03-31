package restsofa.restcontroller;

import java.time.LocalDate;
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

import restsofa.modelo.DTO.EstadoPedidoDto;
import restsofa.modelo.DTO.PedidoDto;
import restsofa.modelo.entities.EstadoPedido;
import restsofa.modelo.entities.Pedido;
import restsofa.service.EstadoPedidoService;
import restsofa.service.EstadoService;
import restsofa.service.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estadopedido")

public class EstadoPedidoRestController {

	@Autowired
	private EstadoPedidoService estadopedService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * Método que devuelve todos los estados de pedido
	 */

	@GetMapping({ "", "/" })
	public ResponseEntity<?> todos() {

		try {
			List<EstadoPedido> lista = estadopedService.buscarEstados();

			List<EstadoPedidoDto> listaDto = new ArrayList<>();
			for (EstadoPedido estado : lista) {
				listaDto.add(modelMapper.map(estado, EstadoPedidoDto.class));
			}

			return ResponseEntity.ok(listaDto.isEmpty() ? "No hay estados de pedido disponibles" : listaDto);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Error al cargar la lista de estados de pedido");
		}
	}
	
	/*
	 * Método que devuelve un estado de pedido
	 */

	@GetMapping("/{idEstado}")
	public ResponseEntity<?> uno(@PathVariable int idEstado) {

		EstadoPedido estado = estadopedService.buscarEstado(idEstado);

		if (estado != null) {

			EstadoPedidoDto estadoDto = modelMapper.map(estado, EstadoPedidoDto.class);

			return ResponseEntity.status(200).body(estadoDto);
		} else
			return ResponseEntity.status(400).body("Error, no se encuentra el estado de pedido");
	}
	
	/*
	 * Método que da de alta un estado de pedido
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody EstadoPedidoDto estadoDto) {

		EstadoPedido estado = new EstadoPedido();
		modelMapper.map(estadoDto, estado);

		estado.setEstado(estadoService.buscarEstado(estadoDto.getIdEstado()));
		estado.setFecha(new Date());
		estado.setPedido(pedidoService.buscarPedido(estadoDto.getIdPedido()));

		if (estadopedService.altaEstado(estado) != null) {
			estadoDto.setIdEstado(estado.getIdEp());
			return ResponseEntity.status(200).body("Estado de pedido procesado correctamente " + estado);
		} else
			return ResponseEntity.status(400).body("Error al procesar el estado de pedido");
	}
	
	/*
	 * Método que modifica un estado de pedido
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody EstadoPedidoDto estadoDto) {

		EstadoPedido estado = estadopedService.buscarEstado(estadoDto.getIdEstado());

		if (estado != null) {
			modelMapper.map(estadoDto, EstadoPedidoDto.class);
			estado.setEstado(estadoService.buscarEstado(estadoDto.getIdEstado()));
			estado.setFecha(new Date());
			estado.setPedido(pedidoService.buscarPedido(estadoDto.getIdPedido()));
			
			estadopedService.modifEstado(estado);
			return ResponseEntity.status(200).body("Estado de pedido modificado correctamente");
		} else
			return ResponseEntity.status(400).body("No se puede modificar el estado de pedido");
	}
	
	/*
	 * Método que borra un pedido
	 */

	@DeleteMapping("/borrar/{idEstado}")
	public ResponseEntity<?> borrar(@PathVariable int idEstado) {

		EstadoPedido estado = estadopedService.buscarEstado(idEstado);

		if (estado != null) {
			estadopedService.borrarEstado(idEstado);
			return ResponseEntity.status(200).body("Estado de pedido eliminado correctamente");
		} else
			return ResponseEntity.status(400).body("Estado de pedido no se ha podido eliminar");
	}

}
