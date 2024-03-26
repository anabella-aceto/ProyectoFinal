package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Carpinteria;

public interface CarpinteriaService {

	Carpinteria insertOne(Carpinteria carpinteria);
	boolean deleteOne (int idCarpinteria);
	Carpinteria updateOne(int idCarpinteria);
	Carpinteria findById(int idCarpinteria);
	List<Carpinteria> buscarPorIdEstado(int idEstado);
	List<Carpinteria> buscarPorIdPedido(int idPedido);
	List<Carpinteria> buscarPorIdPedidoyEstado(int idPedido, int idEstado);
	
	
}
