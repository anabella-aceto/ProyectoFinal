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

import restsofa.modelo.DTO.PedidoDto;
import restsofa.modelo.entities.Pedido;
import restsofa.service.ClienteService;
import restsofa.service.EmpleadoService;
import restsofa.service.EstadoService;
import restsofa.service.PedidoService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para la gestión de los pedidos.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")

public class PedidoRestController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EstadoService estadoService;

	/*
	 * Método que devuelve todos los pedidos.
	 *
	 * @return ResponseEntity con la lista de pedidos si se pudo cargar
	 * correctamente, o un mensaje de error si no se cargó.
	 */

	@GetMapping({ "/todos" })
	public ResponseEntity<?> todos() {

		try {
			List<Pedido> lista = pedidoService.buscarTodosPedidos();

			List<PedidoDto> listaDto = new ArrayList<>();

			for (Pedido pedido : lista) {

				PedidoDto pedidoDto = new PedidoDto();

				pedidoDto.setIdPedido(pedido.getIdPedido());
				pedidoDto.setIdCliente(pedido.getCliente().getIdCliente());
				pedidoDto.setIdEstado(pedido.getEstado().getIdEstado());
				pedidoDto.setFecha(pedido.getFecha());
				pedidoDto.setVendedor(pedido.getVendedor().getIdEmpleado());

				listaDto.add(pedidoDto);

			}

			return ResponseEntity.ok(listaDto.isEmpty() ? "No hay pedidos disponibles" : listaDto);
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Error al cargar la lista de pedidos");
		}
	}

	/**
	 * Método que permite obtener un pedido por su identififcador.
	 *
	 * @param idPedido El identificador único del pedido a buscar.
	 * @return ResponseEntity con el pedido encontrado si existe, o un mensaje de
	 *         error si no existe.
	 */

	@GetMapping("/uno/{idPedido}")
	public ResponseEntity<?> uno(@PathVariable int idPedido) {

		Pedido pedido = pedidoService.buscarPedido(idPedido);

		if (pedido != null) {

			PedidoDto pedidoDto = new PedidoDto();

			pedidoDto.setIdPedido(pedido.getIdPedido());
			pedidoDto.setIdCliente(pedido.getCliente().getIdCliente());
			pedidoDto.setIdEstado(pedido.getEstado().getIdEstado());
			pedidoDto.setFecha(pedido.getFecha());
			pedidoDto.setVendedor(pedido.getVendedor().getIdEmpleado());

			return ResponseEntity.status(200).body(pedidoDto);
		} else
			return ResponseEntity.status(400).body("Error, no se encuentra el pedido");
	}

	/*
	 * Método que permite crear un pedido.
	 * 
	 * @param pedido El pedido a dar de alta.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * alta.
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody PedidoDto pedidoDto) {

		Pedido pedido = new Pedido();
		

		modelMapper.map(pedidoDto, pedido);

		pedido.setIdPedido(pedidoDto.getIdPedido());
		pedido.setCliente(clienteService.buscarCliente(pedidoDto.getIdCliente()));
		pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getVendedor()));
		pedido.setEstado(estadoService.porDefecto("pendiente"));
		pedido.setFecha(pedidoDto.getFecha());

		if (pedidoService.altaPedido(pedido) != null) {

			return ResponseEntity.status(200).body("Pedido procesado correctamente " + pedido);
		} else
			return ResponseEntity.status(400).body("Error al procesar el pedido");
	}

	/*
	 * Método que modifica un pedido.
	 * 
	 * @param pedido El pedido con la información actualizada.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * modificación.
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody PedidoDto pedidoDto) {

		Pedido pedido = pedidoService.buscarPedido(pedidoDto.getIdPedido());

		if (pedido != null) {

			pedido.setCliente(clienteService.buscarCliente(pedidoDto.getIdCliente()));
			pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getVendedor()));
			pedido.setEstado(estadoService.buscarEstado(pedidoDto.getIdEstado()));
			pedido.setFecha(pedidoDto.getFecha());
			pedido.setVendedor(empleadoService.buscarUno(pedidoDto.getIdCliente()));

			pedidoService.modifPedido(pedido);
			return ResponseEntity.status(200).body("Pedido modificado correctamente");
		} else
			return ResponseEntity.status(400).body("No se puede modificar el pedido");
	}

	/*
	 * Método que modifica el estado de un pedido a cancelado, por identificador.
	 * 
	 * @param idPedido El identificador único del pedido a cancelar.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 * cancelación.
	 */
	
	@PutMapping("/cancelar/{idPedido}") // probado y funcionando

	public ResponseEntity<?> cancelarPedido(@PathVariable int idPedido) {

		if (pedidoService.cancelarPedido(idPedido)) {

			return ResponseEntity.status(200).body("Pedido cancelado correctamente");
		} else {
			return ResponseEntity.status(400).body("No se ha podido cancelar el pedido");
		}
	}
	
	/*
	 * Método que elimina un pedido.
	 * 
	 * @param idPedido El identificador único del pedido a eliminar. *
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 * eliminación.
	 */
	@DeleteMapping("/eliminar/{idPedido}") // probado y funcionando

	public ResponseEntity<?> borrar(@PathVariable("idPedido") int idPedido) {

		Pedido pedido = pedidoService.buscarPedido(idPedido);

		if (pedido != null) {
			pedidoService.borrarPedido(idPedido);
			return ResponseEntity.status(200).body("Pedido eliminado correctamente");
		}

		return ResponseEntity.status(400).body("Error al borrar pedido");
	}

	/**
	 * Método que lista los pedidos por estado.
	 *
	 * @param idEstado El identificador único del estado de los pedidos a listar.
	 * @return ResponseEntity con la lista de pedidos encontrados si existen, o un
	 *         mensaje de error si no existen.
	 */

	@GetMapping("/porEstado/{idEstado}") // probado y funcionando
	public ResponseEntity<?> listarPorEstado(@PathVariable(name = "idEstado") int idEstado) {
		List<Pedido> lista = pedidoService.buscarPorEstado(idEstado);

		if (!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);

		else
			return ResponseEntity.status(400).body("No se encuentran elementos con el estado indicado");
	}

	/**
	 * Método que filtra pedidos por fecha.
	 *
	 * @param fechaInicio La fecha de inicio del período a filtrar.
	 * @param fechaFin    La fecha de fin del período a filtrar.
	 * @return ResponseEntity con la lista de pedidos encontrados si existen en el
	 *         rango de fechas, o un mensaje de error si no existen.
	 */
	@GetMapping("/porFecha")
	public ResponseEntity<?> filtrarPorFecha(@RequestParam(name = "fechaInicio") Date fechaInicio,
			@RequestParam(name = "fechaFin") Date fechaFin) {

		List<Pedido> lista = pedidoService.filtrarPorFecha(fechaInicio, fechaFin);

		if (!lista.isEmpty())
			return ResponseEntity.status(200).body(lista);
		else
			return ResponseEntity.status(400).body("No se encuentran elementos en el intervalod de fechas indicado");
	}

}
