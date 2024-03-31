package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Estado;

public interface EstadoService {
	
	Estado buscarEstado (int idEstado);
	List<Estado> buscarTodosEstado ();
	Estado altaEstado (Estado estado);
	Estado modifEstado (Estado estado);
	boolean borrarEstado (int idEstado);

}
