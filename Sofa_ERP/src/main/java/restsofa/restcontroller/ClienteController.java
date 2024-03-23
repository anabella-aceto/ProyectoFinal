package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/*
	* Método que da de alta un cliente
	*/
	
	@PostMapping("/alta")
	public Cliente alta(@RequestBody Cliente cliente) {
		return clienteService.altaCliente(cliente);
	}
	
	/*
	* Método que modifica un cliente
	*/
	
	@PutMapping("/modificar")
	public Cliente modificar(@RequestBody Cliente cliente) {
		return clienteService.modifCliente(cliente);
	}
	
	/*
	* Método que borra un cliente
	*/
	
	@DeleteMapping("/borrar/{idCliente}")
	public String borrar (@PathVariable int idCliente) {
		if(clienteService.borrarCliente(idCliente))
			return "Cliente eliminado correctamente";
		else
			return "Cliente no se ha podido eliminar";
	}
	
	

}
