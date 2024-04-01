package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.EstadoPedido;

public interface EstadoPedidoService {
	
	EstadoPedido buscarEstadoPedido (int idEstadoPedido);
	List<EstadoPedido> buscarEstados ();
	EstadoPedido altaEstado (EstadoPedido estado);
	EstadoPedido modifEstado (EstadoPedido estado);
	boolean borrarEstado (int idEstado);
	EstadoPedido buscarPorNombre(String nombre);

}
