package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.SofaMaterial;

public interface SofaMaterialService {
	
	List<SofaMaterial> todos();
	SofaMaterial buscarUno(int idSofaMaterial);
	SofaMaterial insertOne (SofaMaterial  sofaMaterial);
	SofaMaterial updateOne (SofaMaterial  sofaMaterial);
	boolean deleteOne (int idSofaMaterial);
	

}
