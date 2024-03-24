package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.EstadoPedido;
import restsofa.repository.EstadoPedidoRepository;

@Service

public class EstadoPedidoServiceImplMy8Jpa implements EstadoPedidoService {

	@Autowired
	private EstadoPedidoRepository estrepo;

	@Override
	public EstadoPedido buscarEstadoPedido(int idEstado) {
		return estrepo.findById(idEstado).orElse(null);
	}

	@Override
	public List<EstadoPedido> buscarTodosEstadoPedidos() {
		return estrepo.findAll();
	}

	@Override
	public EstadoPedido altaEstadoPedido(EstadoPedido estado) {
		return estrepo.save(estado);
	}

	@Override
	public EstadoPedido modifEstadoPedido(EstadoPedido estado) {
		try {
			return estrepo.save(estado);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarEstadoPedido(int idEstado) {
		try {
			if (buscarEstadoPedido(idEstado) != null) {
				estrepo.deleteById(idEstado);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}