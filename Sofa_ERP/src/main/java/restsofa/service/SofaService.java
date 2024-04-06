package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Sofa;
/**
 * Interfaz que define los servicios relacionados con la entidad Sofa.
 */

public interface SofaService {
	
	Sofa buscarSofa (int idSofa);
	List<Sofa> buscarTodosSofas ();
	Sofa altaSofa (Sofa sofa);
	Sofa modifSofa (Sofa sofa);
	boolean borrarSofa (int idSofa);

}
