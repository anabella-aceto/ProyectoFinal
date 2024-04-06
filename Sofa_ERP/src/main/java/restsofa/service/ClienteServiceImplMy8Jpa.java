package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Cliente;
import restsofa.repository.ClienteRepository;

/**
 * Implementación de la interfaz ClienteService que utiliza Spring Data JPA.
 */

@Service

public class ClienteServiceImplMy8Jpa implements ClienteService {

	@Autowired
	private ClienteRepository clirepo;

	/**
	 * Busca un cliente por su identificador.
	 *
	 * @param idCliente El identificador único del cliente a buscar.
	 * @return El cliente encontrado, o null si no se encuentra.
	 */
	@Override
	public Cliente buscarCliente(int idCliente) {
		return clirepo.findById(idCliente).orElse(null);
	}

	/**
	 * Método que busca todos los clientes.
	 *
	 * @return Una lista de todos los clientes.
	 */
	@Override
	public List<Cliente> buscarTodosClientes() {
		return clirepo.findAll();
	}

	/**
	 * Método que de alta un nuevo cliente.
	 *
	 * @param cliente El cliente a dar de alta.
	 * @return El cliente dado de alta.
	 */
	@Override
	public Cliente altaCliente(Cliente cliente) {
		return clirepo.save(cliente);
	}

	/**
	 * Método que modifica un cliente existente.
	 *
	 * @param cliente El cliente a modificar.
	 * @return El cliente modificado, o null si ocurrió un error.
	 */
	@Override
	public Cliente modifCliente(Cliente cliente) {
		try {
			return clirepo.save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Método que elimina un cliente por su identificador.
	 *
	 * @param idCliente El identificador único del cliente a borrar.
	 * @return true si el cliente fue borrado correctamente, false de lo contrario.
	 */
	@Override
	public boolean borrarCliente(int idCliente) {
		try {
			if (buscarCliente(idCliente) != null) {
				clirepo.deleteById(idCliente);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}