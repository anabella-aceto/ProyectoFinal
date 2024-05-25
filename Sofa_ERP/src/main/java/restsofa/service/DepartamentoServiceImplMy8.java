package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Departamento;
import restsofa.repository.DepartamentoRepository;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Implementación de la interfaz DepartamentoService que utiliza Spring Data
 * JPA.
 */

@Service
public class DepartamentoServiceImplMy8 implements DepartamentoService {

	@Autowired
	private DepartamentoRepository deptorepo;

	/**
	 * Métood que lista todos los departamentos.
	 *
	 * @return Una lista de todos los departamentos.
	 */

	@Override
	public List<Departamento> listarTodos() {
		return deptorepo.findAll();
	}

	/**
	 * Método que busca un departamento por su id.
	 *
	 * @param idDepartamento El identificador único del departamento a buscar.
	 * @return El departamento encontrado, o null si no se encuentra.
	 */
	@Override
	public Departamento buscarUno(int idDepartamento) {
		return deptorepo.findById(idDepartamento).orElse(null);
	}

	/**
	 * Método que crea un nuevo departamento.
	 *
	 * @param departamento El departamento a insertar.
	 * @return El departamento insertado.
	 */
	@Override
	public Departamento insertOne(Departamento departamento) {
		return deptorepo.save(departamento);
	}

	/**
	 * Método que actualiza un departamento existente.
	 *
	 * @param departamento El departamento a actualizar.
	 * @return El departamento actualizado, o null si ocurrió un error.
	 */
	@Override
	public Departamento updateOne(Departamento departamento) {
		try {
			if (buscarUno(departamento.getIdDepartamento()) != null) {
				return deptorepo.save(departamento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Elimina un departamento por su id.
	 *
	 * @param idDepartamento El identificador único del departamento a eliminar.
	 * @return true si el departamento fue eliminado correctamente, false de lo
	 *         contrario.
	 */
	@Override
	public boolean deleteOne(int idDepartamento) {
		try {
			if (buscarUno(idDepartamento) != null) {
				deptorepo.deleteById(idDepartamento);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
