package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Estado;
import restsofa.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Implementación de la interfaz EstadoService.
 */
@Service
public class EstadoServiceImplMy8Jpa implements EstadoService {

	@Autowired
	private EstadoRepository estrepo;

	/**
	 * Método que busca un estado por su identificador.
	 *
	 * @param idEstado El identificador único del estado a buscar.
	 * @return El estado encontrado, o null si no se encuentra.
	 */
	@Override
	public Estado buscarEstado(int idEstado) {
		return estrepo.findById(idEstado).orElse(null);
	}

	/**
	 * Método que lista todos los estados.
	 *
	 * @return Una lista de todos los estados.
	 */
	@Override
	public List<Estado> buscarTodosEstado() {
		return estrepo.findAll();
	}

	/**
	 * Método que crea un nuevo estado.
	 *
	 * @param estado El estado a insertar.
	 * @return El estado insertado.
	 */
	@Override
	public Estado altaEstado(Estado estado) {
		return estrepo.save(estado);
	}

	/**
	 * Método que modifica un estado existente.
	 *
	 * @param estado El estado a modificar.
	 * @return El estado modificado, o null si hay un error.
	 */
	@Override
	public Estado modifEstado(Estado estado) {
		try {
			return estrepo.save(estado);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Método que elimina un estado por su identificador.
	 *
	 * @param idEstado El identificador único del estado a eliminar.
	 * @return true si se eliminó correctamente, false de lo contrario.
	 */
	@Override
	public boolean borrarEstado(int idEstado) {
		try {
			if (buscarEstado(idEstado) != null) {
				estrepo.deleteById(idEstado);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método que establece un estado por defecto.
	 *
	 * @param nombre El nombre del estado por defecto.
	 * @return El estado establecido por defecto.
	 */
	@Override
	public Estado porDefecto(String nombre) {
		return estrepo.establecerPorDefecto(nombre);
	}
}
