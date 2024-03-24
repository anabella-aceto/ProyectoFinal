package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Pedido;

public interface PedidoService {
	
	Pedido buscarPedido (int idPedido);
	List<Pedido> buscarTodosPedidos ();
	Pedido altaPedido (Pedido pedido);
	Pedido modifPedido (Pedido pedido);
	boolean borrarPedido (int idPedido);
}
