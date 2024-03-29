package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Tarea;

public interface TareaService {
	
	Tarea buscarTarea (int idTarea);
	List<Tarea> buscarTodasTareas();
	Tarea altaTarea (Tarea tarea);
	Tarea modifTarea (Tarea tarea);
	boolean borrarTarea (int idTarea);
}
