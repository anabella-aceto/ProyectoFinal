package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Sofa;
/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0 
 * 
 * Interfaz que define los servicios relacionados con la entidad Sofa.
 */

public interface SofaService {
	
	Sofa buscarSofa (int idSofa);
	List<Sofa> buscarTodosSofas ();
	Sofa altaSofa (Sofa sofa);
	Sofa modifSofa (Sofa sofa);
	boolean borrarSofa (int idSofa);

}
