package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Tarea;
/**
 * Interfaz que define los servicios relacionados con la entidad Empleado.
 */

public interface TareaService {
	
	Tarea buscarTarea (int idTarea);
	List<Tarea> buscarTodasTareas();
	Tarea altaTarea (Tarea tarea);
	Tarea modifTarea (Tarea tarea);
	boolean borrarTarea (int idTarea);
	List<Tarea> buscarPorIdEmpleado(int idEmpleado);
}
