package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Empleado;
import restsofa.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación de la interfaz EmpleadoService.
 */
@Service
public class EmpleadoServiceImplMy8 implements EmpleadoService {

	@Autowired
	private EmpleadoRepository emprepo;

	/**
	 * Método que permite dar de alta un nuevo empleado.
	 *
	 * @param empleado El empleado a insertar.
	 * @return El empleado insertado, o null si hay un error.
	 */
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

	/**
	 * Método que modifica los datos de un empleado existente.
	 *
	 * @param empleado El empleado a modificar.
	 * @return El empleado modificado, o null si no se encuentra el empleado.
	 */
	@Override
	public Empleado modificarEmpleado(Empleado empleado) {
		try {
			if (buscarUno(empleado.getIdEmpleado()) != null) {
				emprepo.save(empleado);
				return empleado;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que elimina un empleado por su identificador.
	 *
	 * @param idEmpleado El identificador único del empleado a eliminar.
	 * @return true si se eliminó correctamente, false de lo contrario.
	 */
	@Override
	public boolean deleteOne(int idEmpleado) {
		if (buscarUno(idEmpleado) != null) {
			emprepo.deleteById(idEmpleado);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que busca un empleado por su id.
	 *
	 * @param idEmpleado El identificador único del empleado a buscar.
	 * @return El empleado encontrado, o null si no se encuentra.
	 */
	@Override
	public Empleado buscarUno(int idEmpleado) {
		return emprepo.findById(idEmpleado).orElse(null);
	}

	/**
	 * Método que lista todos los empleados.
	 *
	 * @return Una lista de todos los empleados.
	 */
	@Override
	public List<Empleado> buscarTodos() {
		return emprepo.findAll();
	}

	/**
	 * Método que busca empleados por el identificador de departamento.
	 *
	 * @param idDepartamento El identificador único del departamento.
	 * @return Una lista de empleados del departamento especificado.
	 */
	@Override
	public List<Empleado> buscarPorDepto(int idDepartamento) {
		return emprepo.listarPorDepartamento(idDepartamento);
	}

	/**
	 * Método que busca empleados según su id de perfil.
	 *
	 * @param idPerfil El identificador único del perfil.
	 * @return Una lista de empleados con el perfil especificado.
	 */
	@Override
	public List<Empleado> buscarPorPerfil(int idPerfil) {
		return emprepo.listarPorPerfil(idPerfil);
	}

	/**
	 * Busca un empleado por su primer apellido.
	 *
	 * @param apellidos El primer apellido del empleado a buscar.
	 * @return El empleado encontrado, o null si no se encuentra.
	 */
	@Override
	public Empleado buscarPorApellidos(String apellidos) {
		return emprepo.buscarPorApellidos(apellidos);
	}

}
