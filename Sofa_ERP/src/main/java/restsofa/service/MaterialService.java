package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Material;

public interface MaterialService {
	
	Material insertOne (Material material);
	Material buscarUno(int idMaterial);
	Material updateOne (Material material);
	boolean deleteOne(int idMaterial);
	Material findById(int idMaterial);
	List<Material> findAll();
	List<Material> buscarPorNombre(String nombre);
	Material findByProveedor(String refMaterialProveedor);
	List<Material> buscarPorProveedor(int idProveedor);
	
	
	

}
