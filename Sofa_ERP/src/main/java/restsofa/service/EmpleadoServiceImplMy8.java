package restsofa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Empleado;
import restsofa.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImplMy8 implements EmpleadoService {
	
	@Autowired
	private EmpleadoRepository emprepo;

	@Override
	public Empleado altaEmpleado(Empleado empleado) {
		try {
			emprepo.save(empleado);
			return empleado;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Empleado modificarEmpleado(Empleado empleado) {
		try {
			if (buscarUno(empleado.getIdEmpleado())!=null) {
				emprepo.save(empleado);
				return empleado;
			}
			else 
				return null;
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteOne(int idEmpleado) {
		if(buscarUno(idEmpleado) != null) {
			emprepo.deleteById(idEmpleado);
			return true;
		}
		else
			return false;
	}

	@Override
	public Empleado buscarUno(int idEmpleado) {
		
		return emprepo.findById(idEmpleado).orElse(null);
	}

}
