package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Carpinteria;

public interface CarpinteriaService {

	Carpinteria insertOne(Carpinteria carpinteria);
	List<Carpinteria> buscarPorIdEstado(int idEstado);
	List<Carpinteria> buscarPorIdPedido(int idPedido);
	List<Carpinteria> buscarPorIdPedidoyEstado(int idPedido, int idEstado);
	
	
}
