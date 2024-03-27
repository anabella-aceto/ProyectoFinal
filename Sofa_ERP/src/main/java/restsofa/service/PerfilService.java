package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Perfil;

public interface PerfilService {
	
	List<Perfil> buscarTodos();
	Perfil buscarUno(int idPerfil);
	Perfil insertOne(Perfil perfil);
	boolean deleteOne(int idPerfil);
	Perfil modificarPerfil(Perfil perfil);
	
	

}
