package restsofa.service;

import java.util.List;
import java.util.Map;

import restsofa.modelo.entities.Tarea;
/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0 
 * 
 * Interfaz que define los servicios relacionados con la entidad Empleado.
 */

public interface TareaService {
	
	Tarea buscarTarea (int idTarea);
	List<Tarea> buscarTodasTareas();
	Tarea altaTarea (Tarea tarea);
	Tarea modifTarea (Tarea tarea);
	boolean borrarTarea (int idTarea);
	List<Tarea> buscarPorIdEmpleado(int idEmpleado);
	int altaEstadoTarea(int idTarea, int idEmpleado, int idDepartamento, int idDeped);
	Tarea buscarPorEstado(int idEstado);
	List<Tarea> buscarPorIdDepartamento(int idDepartamento);
	public List<Tarea> buscarPorDetalle(int idDePed);
	int revocarEstadoTarea(int idTarea, int idEmpleado, int idDepartamento, int idDeped);
	List<Tarea> listarPorEstado(int idEstado);
}
