package restsofa.service;

import java.util.List;

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

	@Override
	public List<Empleado> buscarTodos() {
		
		return emprepo.findAll();
	}

	@Override
	public List<Empleado> buscarPorDepto(int idDepartamento) {
		
		return emprepo.listarPorDepartamento(idDepartamento);
	}

	@Override
	public List<Empleado> buscarPorPerfil(int idPerfil) {
		
		return emprepo.listarPorPerfil(idPerfil);
	}

	@Override
	public Empleado buscarPorNombre( String apellidos) {
		// TODO Auto-generated method stub
		return emprepo.buscarPorNombre(apellidos);
	}

}
