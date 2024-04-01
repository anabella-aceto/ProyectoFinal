package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.SofaMaterial;
import restsofa.repository.SofaMaterialRepository;

@Service
public class SofaMaterialServiceImplMy8 implements SofaMaterialService{
	
	@Autowired
	private SofaMaterialRepository sofaMaterialRepository;

	@Override
	public List<SofaMaterial> todos() {
		
		return sofaMaterialRepository.findAll();
	}

	@Override
	public SofaMaterial buscarUno(int idSofaMaterial) {
		
		return sofaMaterialRepository.findById(idSofaMaterial).orElse(null);
	}

	@Override
	public SofaMaterial insertOne(SofaMaterial sofaMaterial) {
		
		return sofaMaterialRepository.save(sofaMaterial);
	}

	@Override
	public SofaMaterial updateOne(SofaMaterial sofaMaterial) {
		try {
			if (buscarUno(sofaMaterial.getIdSofaMateriales()) != null) {
				sofaMaterialRepository.save(sofaMaterial);
				return sofaMaterial;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteOne(SofaMaterial sofaMaterial) {
			
		if (buscarUno(sofaMaterial.getIdSofaMateriales()) != null) {
			sofaMaterialRepository.delete(sofaMaterial);
			return true;
		}
		return false;
	}

	@Override
	public List<SofaMaterial> buscarPorSofa(int idSofa) {
		
		List<SofaMaterial> sofaMaterial = sofaMaterialRepository.buscarPorIdSofa(idSofa);
		
		if ( sofaMaterial!=null)
			return sofaMaterial;
		else
		return null;
	}

	@Override
	public SofaMaterial buscarPorSofaAndmaterial(int idSofa, int idMaterial) {
		// TODO Auto-generated method stub
		return sofaMaterialRepository.buscarPorSofaYMaterial(idSofa, idMaterial);
	}
	
	

}
