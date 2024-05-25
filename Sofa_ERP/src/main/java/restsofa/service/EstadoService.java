package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Estado;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad Estado.
 */

public interface EstadoService {
	
	Estado buscarEstado (int idEstado);
	List<Estado> buscarTodosEstado ();
	Estado altaEstado (Estado estado);
	Estado modifEstado (Estado estado);
	boolean borrarEstado (int idEstado);
	Estado porDefecto(String nombre);

}
