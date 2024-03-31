package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.EstadoPedido;

public interface EstadoPedidoService {
	
	EstadoPedido buscarEstado (int idEstado);
	List<EstadoPedido> buscarEstados ();
	EstadoPedido altaEstado (EstadoPedido estado);
	EstadoPedido modifEstado (EstadoPedido estado);
	boolean borrarEstado (int idEstado);

}
