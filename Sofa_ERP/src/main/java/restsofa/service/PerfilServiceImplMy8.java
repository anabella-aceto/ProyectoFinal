package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Perfil;
import restsofa.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Implementación de la interfaz PerfilService.
 */
@Service
public class PerfilServiceImplMy8 implements PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	/**
	 * Método que lista todos los perfiles.
	 *
	 * @return Una lista de todos los perfiles.
	 */
	@Override
	public List<Perfil> buscarTodos() {
		return perfilRepository.findAll();
	}

	/**
	 * Método que busca un perfil por su identificador.
	 *
	 * @param idPerfil El identificasdor único del perfil a buscar.
	 * @return El perfil encontrado, o null si no se encuentra.
	 */
	@Override
	public Perfil buscarUno(int idPerfil) {
		return perfilRepository.findById(idPerfil).orElse(null);
	}

	/**
	 * Método que crea un nuevo perfil.
	 *
	 * @param perfil El perfil a insertar.
	 * @return El perfil insertado.
	 */
	@Override
	public Perfil insertOne(Perfil perfil) {
		return perfilRepository.save(perfil);
	}

	/**
	 * Método que elimina un perfil por su identificador.
	 *
	 * @param idPerfil El identificador único del perfil a eliminar.
	 * @return true si se eliminó correctamente, false de lo contrario.
	 */
	@Override
	public boolean deleteOne(int idPerfil) {
		try {
			if (buscarUno(idPerfil) != null) {
				perfilRepository.deleteById(idPerfil);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método que modifica un perfil existente.
	 *
	 * @param perfil El perfil a modificar.
	 * @return El perfil modificado, o null si hay un error.
	 */
	@Override
	public Perfil modificarPerfil(Perfil perfil) {
		try {
			if (buscarUno(perfil.getIdPerfil()) != null) {
				perfilRepository.save(perfil);
				return perfil;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
