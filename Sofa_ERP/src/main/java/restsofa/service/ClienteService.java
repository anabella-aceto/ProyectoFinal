package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Cliente;

public interface ClienteService {
	
	Cliente buscarCliente (int idCliente);
	List<Cliente> buscarTodosClientes ();
	Cliente altaCliente (Cliente cliente);
	Cliente modifCliente (Cliente cliente);
	boolean borrarCliente (int idCliente);
}
