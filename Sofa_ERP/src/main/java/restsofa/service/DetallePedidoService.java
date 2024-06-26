package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.DetallePedido;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad DetallePedido.
 */

public interface DetallePedidoService {
	
	DetallePedido buscarDetPed (int idDePed);
	List<DetallePedido> buscarTodosDetPed ();
	DetallePedido altaDetPed (DetallePedido detPed);
	DetallePedido modifDetPed (DetallePedido detPed);
	boolean borrarDetPed (int idDePed);
	boolean alta(DetallePedido detPed);
	List<DetallePedido> buscarPorIdPedido(int idPedido);
	DetallePedido findByDetalleYPedido(int idDeped, int idPedido);
	DetallePedido buscarPorDetalleSofaPedido(int idDeped, int idPedido, int idSofa);
	DetallePedido buscarPorPedido(int idPedido);
	

}
