package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Cliente;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad Cliente.
 */

public interface ClienteService {
	
	Cliente buscarCliente (int idCliente);
	List<Cliente> buscarTodosClientes ();
	Cliente altaCliente (Cliente cliente);
	Cliente modifCliente (Cliente cliente);
	boolean borrarCliente (int idCliente);
}
