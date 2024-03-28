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

import restsofa.modelo.DTO.CosturaDto;
import restsofa.modelo.entities.Costura;
import restsofa.modelo.entities.EstadoPedido;
import restsofa.modelo.entities.Pedido;
import restsofa.service.CosturaService;
import restsofa.service.EstadoPedidoService;
import restsofa.service.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/costura")

public class CosturaRestController {

	@Autowired
	private CosturaService costuraService;

	@Autowired
	private EstadoPedidoService estadoPedidoService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * Método que da de alta una costura
	 */

	@PostMapping("/alta")
	public ResponseEntity<?> altaCostura(@RequestBody CosturaDto costuraDto) {

		Costura costura = modelMapper.map(costuraDto, Costura.class);

		Costura altaNueva = costuraService.altaCostura(costura);

		if (altaNueva != null) {
			costuraDto.setIdCostura(costura.getIdCostura());
			costura.setPedido(pedidoService.buscarPedido(costuraDto.getIdPedido()));
			costura.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(costuraDto.getIdEstado()));
			costura.setFecha(costuraDto.getFecha());
			return ResponseEntity.status(200).body(altaNueva);
		} else
			return ResponseEntity.status(400).body("No se puede dar de alta la costura");
	}

	/*
	 * Método que modifica una costura
	 */

	@PutMapping("/modificar")
	public ResponseEntity<?> modificarCostura(@RequestBody CosturaDto costuraDto) {

		Costura costura = costuraService.buscarCostura(costuraDto.getIdCostura());

		if (costura != null) {
			modelMapper.map(costuraDto, Costura.class);
			costura.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(costuraDto.getIdEstado()));
			costura.setPedido(pedidoService.buscarPedido(costuraDto.getIdPedido()));
			costura.setFecha(costuraDto.getFecha());

			costuraService.modifCostura(costura);
			return ResponseEntity.status(200).body("Costura modificada correctamente" + costura);
		} else
			return ResponseEntity.status(400).body("La costura no se ha podido modificar");

	}

	/*
	 * Método que borra una costura
	 */

	@DeleteMapping("/borrar/{idCostura}")
	public ResponseEntity<?> borrarCostura(@PathVariable int idCostura) {

		Costura costura = costuraService.buscarCostura(idCostura);

		if (costura != null) {
			costuraService.borrarCostura(idCostura);
			return ResponseEntity.status(200).body("Costura eliminada correctamente");
		} else
			return ResponseEntity.status(400).body("La costura no se ha podido eliminar");
	}

	/*
	 * Método que devuelve la costura de un pedido
	 */

	@GetMapping("/porPedido/{idPedido}")

	public ResponseEntity<?> buscarCosturaPorIdPedido(@PathVariable("idPedido") int idPedido) {

		Pedido pedido = pedidoService.buscarPedido(idPedido);

		List<CosturaDto> listaDto = new ArrayList<>();

		if (pedido != null) {
			List<Costura> lista = costuraService.buscarCosturaPorPedido(idPedido);
			for (Costura costura : lista) {
				listaDto.add(modelMapper.map(costura, CosturaDto.class));
			}

			return ResponseEntity.status(200).body(listaDto);
		}

		else
			return ResponseEntity.status(400).body("No se encuentra el objeto con el identificador ingresado");
	}

	/*
	 * Método que devuelve las costuras de un estado
	 */

	@GetMapping("/porEstado/{idEstado}") // probado y funcionando

	public ResponseEntity<?> buscarCosturasPorEstado(@PathVariable("idEstado") int idEstado) {

		EstadoPedido estadoPedido = estadoPedidoService.buscarEstadoPedido(idEstado);

		List<CosturaDto> listaDto = new ArrayList<>();

		if (estadoPedido != null) {
			List<Costura> lista = costuraService.buscarCosturaPorEstado(idEstado);

			for (Costura costura : lista) {
				listaDto.add(modelMapper.map(costura, CosturaDto.class));
			}

			return ResponseEntity.status(200).body(listaDto);
		}

		else
			return ResponseEntity.status(400).body("No se encuentra el objeto con el identificador ingresado");

	}

}
