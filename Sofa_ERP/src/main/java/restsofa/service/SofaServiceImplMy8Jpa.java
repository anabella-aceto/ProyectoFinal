package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Sofa;
import restsofa.repository.SofaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0 
 * 
 * Implementación de la interfaz SofaService.
 */
@Service
public class SofaServiceImplMy8Jpa implements SofaService {

	@Autowired
	private SofaRepository sofrepo;

	/**
	 * Método que busca un sofá por su identificador.
	 *
	 * @param idSofa El identificador único del sofá a buscar.
	 * @return El sofá encontrado, o null si no se encuentra.
	 */
	@Override
	public Sofa buscarSofa(int idSofa) {
		return sofrepo.findById(idSofa).orElse(null);
	}

	/**
	 * Método que devuelve una lista de todos los sofás.
	 *
	 * @return Una lista de todos los sofás.
	 */
	@Override
	public List<Sofa> buscarTodosSofas() {
		return sofrepo.findAll();
	}

	/**
	 * Métood que inserta un nuevo sofá.
	 *
	 * @param sofa El sofá a insertar.
	 * @return El sofá insertado.
	 */
	@Override
	public Sofa altaSofa(Sofa sofa) {
		return sofrepo.save(sofa);
	}

	/**
	 * Método que permite actualizar los datos de un sofá existente.
	 *
	 * @param sofa El sofá a modificar.
	 * @return El sofá modificado, o null si hay un error.
	 */
	@Override
	public Sofa modifSofa(Sofa sofa) {
		try {
			return sofrepo.save(sofa);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Método que elimina un sofá por su identificador.
	 *
	 * @param idSofa El identificador único del sofá a eliminar.
	 * @return true si se eliminó correctamente, false si no se pudo eliminar.
	 */
	@Override
	public boolean borrarSofa(int idSofa) {
		try {
			if (buscarSofa(idSofa) != null) {
				sofrepo.deleteById(idSofa);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
