package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.EstadoPedido;

public interface EstadoPedidoService {
	
	EstadoPedido buscarEstadoPedido (int idEstado);
	List<EstadoPedido> buscarTodosEstadoPedidos ();
	EstadoPedido altaEstadoPedido (EstadoPedido estado);
	EstadoPedido modifEstadoPedido (EstadoPedido estado);
	boolean borrarEstadoPedido (int idEstado);

}
