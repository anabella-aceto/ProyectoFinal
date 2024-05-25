package restsofa.service;

import java.util.Date;
import java.util.List;

import restsofa.modelo.entities.Pedido;


/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad Pedido.
 */

public interface PedidoService {
	
	Pedido buscarPedido (int idPedido);
	List<Pedido> buscarTodosPedidos ();
	Pedido altaPedido (Pedido pedido);
	Pedido modifPedido (Pedido pedido);
	boolean borrarPedido (int idPedido);
	boolean cancelarPedido(int idPedido);
	List<Pedido> filtrarPorFecha(Date fechaInicio, Date fechaFin);
}
