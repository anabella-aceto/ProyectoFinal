package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Perfil;
import restsofa.repository.PerfilRepository;

@Service
public class PerfilServiceImplMy8 implements PerfilService{

	@Autowired
	private PerfilRepository perfilRepository;
	
	
	@Override
	public List<Perfil> buscarTodos() {
		
		return perfilRepository.findAll();
	}

	@Override
	public Perfil buscarUno(int idPerfil) {
		
		return perfilRepository.findById(idPerfil).orElse(null);
	}

	@Override
	public Perfil insertOne(Perfil perfil) {
		
		return perfilRepository.save(perfil);
	}

	@Override
	public boolean deleteOne(int idPerfil) {
		try {
			if(buscarUno(idPerfil)!=null) {
				perfilRepository.deleteById(idPerfil);
				return true;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Perfil modificarPerfil(Perfil perfil) {
		try {
			if(buscarUno(perfil.getIdPerfil())!= null) {
				perfilRepository.save(perfil);
				return perfil;
			} 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
