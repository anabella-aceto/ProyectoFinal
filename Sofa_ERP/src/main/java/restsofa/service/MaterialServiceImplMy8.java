package restsofa.service;

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

}
