package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Cliente;

public interface ClienteService {
	
	Cliente buscarCliente (int idCliente);
	List<Cliente> buscarTodosClientes ();

}
