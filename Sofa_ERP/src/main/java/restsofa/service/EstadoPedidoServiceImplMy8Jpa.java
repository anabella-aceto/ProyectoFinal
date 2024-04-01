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
	public List<EstadoPedido> buscarEstados() {
		return estrepo.findAll();
	}

	@Override
	public EstadoPedido altaEstado(EstadoPedido estado) {
		return estrepo.save(estado);
	}

	@Override
	public EstadoPedido modifEstado(EstadoPedido estado) {
		try {
			return estrepo.save(estado);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarEstado(int idEstado) {
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

	@Override
	public EstadoPedido buscarEstadoPedido(int idEstadoPedido) {
		// TODO Auto-generated method stub
		return estrepo.findById(idEstadoPedido).orElse(null);
	}

	@Override
	public EstadoPedido buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return estrepo.buscarPorNombreEstado(nombre);
	}

}
