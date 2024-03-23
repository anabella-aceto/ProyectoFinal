package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Cliente;
import restsofa.repository.ClienteRepository;


@Service

public class ClienteServiceImplMy8Jpa implements ClienteService{
	
	@Autowired
	private ClienteRepository clirepo;

	@Override
	public Cliente buscarCliente(int idCliente) {
		return clirepo.findById(idCliente).orElse(null);
	}

	@Override
	public List<Cliente> buscarTodosClientes() {
		return clirepo.findAll();
	}

}
