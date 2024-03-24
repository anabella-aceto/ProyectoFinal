package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Costura;

public interface CosturaService {
	
	Costura buscarCostura (int idCostura);
	List<Costura> buscarTodasCostura ();
	Costura altaCostura (Costura costura);
	Costura modifCostura (Costura costura);
	boolean borrarCostura (int idCostura);

}
