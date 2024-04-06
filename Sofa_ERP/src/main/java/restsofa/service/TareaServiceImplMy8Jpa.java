package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Tarea;
import restsofa.repository.TareaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación de la interfaz TareaService.
 */
@Service
public class TareaServiceImplMy8Jpa implements TareaService {

	@Autowired
	private TareaRepository tarepo;

	/**
	 * Método que busca una tarea por su identificador.
	 *
	 * @param idTarea El identificador único de la tarea a buscar.
	 * @return La tarea encontrada, o null si no se encuentra.
	 */
	@Override
	public Tarea buscarTarea(int idTarea) {
		return tarepo.findById(idTarea).orElse(null);
	}

	/**
	 * Método que devuelve una lista de todas las tareas.
	 *
	 * @return Una lista de todas las tareas.
	 */
	@Override
	public List<Tarea> buscarTodasTareas() {
		return tarepo.findAll();
	}

	/**
	 * Método que crea una nueva tarea.
	 *
	 * @param tarea La tarea a crear.
	 * @return La tarea insertada.
	 */
	@Override
	public Tarea altaTarea(Tarea tarea) {
		return tarepo.save(tarea);
	}

	/**
	 * Método que modifica una tarea existente.
	 *
	 * @param tarea La tarea a modificar.
	 * @return La tarea modificada, o null si hay un error.
	 */
	@Override
	public Tarea modifTarea(Tarea tarea) {
		try {
			return tarepo.save(tarea);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Método que elimina una tarea por su identificador.
	 *
	 * @param idTarea El identificador único de la tarea a eliminar.
	 * @return true si se eliminó correctamente, false si no se pudo eliminar.
	 */
	@Override
	public boolean borrarTarea(int idTarea) {
		try {
			if (buscarTarea(idTarea) != null) {
				tarepo.deleteById(idTarea);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que busca todas las tareas asociadas a un empleado por su
	 * identificador.
	 *
	 * @param idEmpleado El identificador único del empleado.
	 * @return Una lista de todas las tareas asociadas al empleado.
	 */
	@Override
	public List<Tarea> buscarPorIdEmpleado(int idEmpleado) {
		return tarepo.buscarPorEmpleado(idEmpleado);
	}
}
