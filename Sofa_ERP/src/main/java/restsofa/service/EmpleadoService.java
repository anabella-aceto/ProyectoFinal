package restsofa.service;

import restsofa.modelo.entities.Empleado;

public interface EmpleadoService {
	
	Empleado altaEmpleado(Empleado empleado);
	Empleado modificarEmpleado(Empleado empelado);
	boolean deleteOne(int idEmpleado);
	Empleado buscarUno (int idEmpleado);
	
	

}
