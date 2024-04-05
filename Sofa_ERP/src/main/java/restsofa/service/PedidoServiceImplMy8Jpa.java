package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Pedido;
import restsofa.repository.EstadoRepository;
import restsofa.repository.PedidoRepository;

@Service

public class PedidoServiceImplMy8Jpa implements PedidoService {

	@Autowired
	private PedidoRepository pedrepo;
	
	@Autowired
	
	private EstadoService estadoService;

	public Pedido buscarPedido(int idPedido) {
		return pedrepo.findById(idPedido).orElse(null);
	}

	@Override
	public List<Pedido> buscarTodosPedidos() {
		return pedrepo.findAll();
	}

	@Override
	public Pedido altaPedido(Pedido pedido) {
		return pedrepo.save(pedido);
	}

	@Override
	public Pedido modifPedido(Pedido pedido) {
		try {
			return pedrepo.save(pedido);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarPedido(int idPedido) {
		try {
			if (buscarPedido(idPedido) != null) {
				pedrepo.deleteById(idPedido);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean cancelarPedido(int idPedido) {
		Pedido pedido = buscarPedido(idPedido);
		
		if (pedido !=null) {
			pedido.setEstado(estadoService.buscarEstado(4));
			pedrepo.save(pedido);
			return true;
		}
			
		return false;
	}

	@Override
	public List<Pedido> buscarPorEstado(int idEstado) {
		// TODO Auto-generated method stub
		return pedrepo.buscarPorestado(idEstado);
	}
}
