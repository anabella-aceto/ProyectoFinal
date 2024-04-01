package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Material;
import restsofa.repository.MaterialRepository;

@Service
public class MaterialServiceImplMy8 implements MaterialService{
	
	@Autowired	
	private MaterialRepository mrepo;	
	

	@Override
	public Material insertOne(Material material) {
		try {
			mrepo.save(material);
			return material;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Material updateOne(Material material) {
		
		try {
			if( findById(material.getIdMaterial()) !=null) 
				return mrepo.save(material);
			else 
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}			
		
	}
	
	

	@Override
	public boolean deleteOne(int idMaterial) {
		try {
			if(findById(idMaterial)!=null) {
				mrepo.deleteById(idMaterial);
				return true;
			}	
			else return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
		
		
	}
	@Override
	public Material findById(int idMaterial) {
		
		return mrepo.findById(idMaterial).orElse(null);
	}

	
	
	@Override
	public List<Material> findAll() {
		
		return mrepo.findAll();
	}

	@Override
	public List<Material> buscarPorNombre(String nombre) {
		
		return mrepo.findByName(nombre);
	}

	@Override
	public Material findByProveedor(String refMaterialProveedor) {
		
		return mrepo.buscarPorProvedor(refMaterialProveedor);
	}

	@Override
	public Material buscarUno(int idMaterial) {
		// TODO Auto-generated method stub
		return mrepo.findById(idMaterial).orElse(null);
	}

	


}
