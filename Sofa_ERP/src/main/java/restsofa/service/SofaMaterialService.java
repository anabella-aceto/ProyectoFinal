package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.SofaMaterial;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad SofaMaterial.
 */

public interface SofaMaterialService {
	
	List<SofaMaterial> todos();
	SofaMaterial buscarUno(int idSofaMaterial);
	SofaMaterial insertOne (SofaMaterial  sofaMaterial);
	SofaMaterial updateOne (SofaMaterial  sofaMaterial);
	boolean deleteOne (SofaMaterial sofaMaterial);
	List<SofaMaterial> buscarPorSofa(int idSofa);
	SofaMaterial buscarPorSofaAndmaterial(int idSofa, int idMaterial);
	

}
