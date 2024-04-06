package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Empleado;

/**
 * Interfaz que define un repositorio para la entidad Empleado. 
 * Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Empleado> El tipo de entidad gestionada por el repositorio.
 * @param <Integer>  El tipo del identificador de la entidad Empleado.
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	/**
	 * Busca una lista de empleados por el identificador del departamento.
	 *
	 * @param idDepartamento. El identificador del departamento.
	 * @return Una lista de empleados que pertenecen al departamento dado.
	 */
	@Query("select e from Empleado e where e.departamento.idDepartamento=?1")
	List<Empleado> listarPorDepartamento(int idDepartamento);

	/**
	 * Busca una lista de empleados por el identificador del perfil.
	 *
	 * @param idPerfil. El identificador del perfil.
	 * @return Una lista de empleados que tienen el perfil dado.
	 */
	@Query("select e from Empleado e where e.perfil.idPerfil=?1")
	List<Empleado> listarPorPerfil(int idPerfil);

	/**
	 * Busca un empleado por sus apellidos.
	 *
	 * @param apellidos. Los apellidos del empleado.
	 * @return El empleado encontrado, o null si no se encuentra ningún empleado con
	 *         esos apellidos.
	 */
	@Query("select e from Empleado e where e.apellidos=?1")
	Empleado buscarPorNombre(String apellidos);
}
