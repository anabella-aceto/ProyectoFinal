package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Carpinteria;

public interface CarpinteriaService {

	Carpinteria insertOne(Carpinteria carpinteria);
	boolean deleteOne (int idCarpinteria);
	Carpinteria updateOne(Carpinteria carpinteria);
	Carpinteria buscarUno(int idCarpinteria);
	List<Carpinteria> buscarPorIdEstado(int idEstado);
	List<Carpinteria> buscarPorIdPedido(int idPedido);
	Carpinteria buscarPorIdPedidoyEstado(int idPedido, int idEstado);
	
	
}
