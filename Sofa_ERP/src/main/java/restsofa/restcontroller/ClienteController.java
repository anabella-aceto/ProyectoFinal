package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Cliente;
import restsofa.service.ClienteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")

public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	/*
	* Método que devuelve todos los clientes
	*/
	
	@GetMapping({ "", "/" })
	public List<Cliente> todos() {
		return clienteService.buscarTodosClientes();
	}
	
	/*
	* Método que devuelve un cliente
	*/
	
	@GetMapping("/{idCliente}")
	public Cliente uno(@PathVariable int idCliente) {
		return clienteService.buscarCliente(idCliente);
	}

}
