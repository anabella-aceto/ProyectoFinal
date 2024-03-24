package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Pedido;
import restsofa.repository.PedidoRepository;

@Service

public class PedidoServiceImplMy8Jpa implements PedidoService{
	
	@Autowired
	private PedidoRepository pedrepo;

	@Override
//	public Pedido buscarPedido(int idPedido) {
//		return pedrepo.findById(idPedido);
	}

	@Override
//	public List<Pedido> buscarTodosPedidos() {
//		return pedrepo.findAll();
	}

	@Override
	public Pedido altaPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido modifPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrarPedido(int idPedido) {
		// TODO Auto-generated method stub
		return false;
	}

}
