package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Pedido;
import restsofa.repository.PedidoRepository;

@Service

public class PedidoServiceImplMy8Jpa implements PedidoService {

	@Autowired
	private PedidoRepository pedrepo;
/*
	@Override
<<<<<<< HEAD
//	public Pedido buscarPedido(int idPedido) {
//		return pedrepo.findById(idPedido);
//	}

	@Override
//	public List<Pedido> buscarTodosPedidos() {
//		return pedrepo.findAll();
//	}
*/
=======
	public Pedido buscarPedido(int idPedido) {
		return pedrepo.findById(idPedido).orElse(null);
	}

	@Override
	public List<Pedido> buscarTodosPedidos() {
		return pedrepo.findAll();
	}

>>>>>>> 24ee6ae367287d25b16827184b5c2254d0b6f572
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
<<<<<<< HEAD

	@Override
	public Pedido buscarPedido(int idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> buscarTodosPedidos() {
		// TODO Auto-generated method stub
		return null;
	}

=======
>>>>>>> 24ee6ae367287d25b16827184b5c2254d0b6f572
}
