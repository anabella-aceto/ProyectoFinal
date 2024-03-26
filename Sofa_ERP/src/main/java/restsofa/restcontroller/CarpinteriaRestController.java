package restsofa.restcontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Carpinteria;
import restsofa.modelo.entities.Pedido;
import restsofa.service.CarpinteriaService;
import restsofa.service.EstadoPedidoService;
import restsofa.service.PedidoService;

@RestController
@RequestMapping("/carpinteria")
@CrossOrigin(origins="*")
public class CarpinteriaRestController {
	
	@Autowired
	private CarpinteriaService carpinteriaService;
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	
		
	@PostMapping("/alta")
	public ResponseEntity<?> ingresarPedido(@RequestBody Carpinteria carpinteria){
		Pedido pedido = pedidoService.buscarPedido(carpinteria.getPedido().getIdPedido());
				
		if (pedido != null) {
			carpinteria.setFecha(new Date());
			carpinteria.setEstadoPedido(estadoPedidoService.buscarEstadoPedido(1));
			carpinteriaService.insertOne(carpinteria);
			
			return ResponseEntity.status(200).body("Alta correcta" + carpinteria);
		}
		
		else		
		return ResponseEntity.status(400).body("Error al ingresar pedido");
	}
	
	

}
