package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.DetallePedido;

public interface DetallePedidoService {
	
	DetallePedido buscarDetPed (int idDePed);
	List<DetallePedido> buscarTodosDetPed ();
	DetallePedido altaDetPed (DetallePedido detPed);
	DetallePedido modifDetPed (DetallePedido detPed);
	boolean borrarDetPed (int idDePed);

}
