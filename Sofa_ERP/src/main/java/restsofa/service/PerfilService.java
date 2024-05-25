package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Perfil;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad Perfil.
 */

public interface PerfilService {
	
	List<Perfil> buscarTodos();
	Perfil buscarUno(int idPerfil);
	Perfil insertOne(Perfil perfil);
	boolean deleteOne(int idPerfil);
	Perfil modificarPerfil(Perfil perfil);
	
	

}
