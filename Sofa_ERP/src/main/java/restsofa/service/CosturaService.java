package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Costura;

public interface CosturaService {
	
	Costura buscarCostura (int idCostura);
	Costura altaCostura (Costura costura);
	Costura modifCostura (Costura costura);
	boolean borrarCostura (int idCostura);
	List<Costura> buscarCosturaPorEstado(int idEstado);
	List<Costura> buscarCosturaPorPedido(int idPedido);

}
