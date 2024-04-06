package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Empleado;

/**
 * Interfaz que define los servicios relacionados con la entidad Empleado.
 */

public interface EmpleadoService {
	
	Empleado altaEmpleado(Empleado empleado);
	Empleado modificarEmpleado(Empleado empelado);
	boolean deleteOne(int idEmpleado);
	Empleado buscarUno (int idEmpleado);
	List<Empleado> buscarTodos();
	List<Empleado> buscarPorDepto(int idDepartamento);
	List<Empleado> buscarPorPerfil(int idPerfil);
	Empleado buscarPorNombre(String apellidos);
	

}
