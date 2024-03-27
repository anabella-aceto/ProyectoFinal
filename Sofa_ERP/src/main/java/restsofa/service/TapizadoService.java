package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Tapizado;


public interface TapizadoService {
	
	List<Tapizado> listarTodos();
	Tapizado insertOne(Tapizado tapizado);
	Tapizado updateOne(Tapizado tapizado);
	boolean deleteOne (int idTapizado);
	Tapizado buscarUno( int idTapizado);
	List<Tapizado> buscarPorPedido(int idPedido); 
	List<Tapizado> buscarPorEstado(int idEstado);

}
