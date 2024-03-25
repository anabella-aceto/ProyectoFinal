package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Carpinteria;

public interface CarpinteriaService {

	Carpinteria insertOne(int idPedido);
	Carpinteria addEstado(int idEstado);
	List<Carpinteria> buscarPorIdPedido(int idPedido);
	
	
}
