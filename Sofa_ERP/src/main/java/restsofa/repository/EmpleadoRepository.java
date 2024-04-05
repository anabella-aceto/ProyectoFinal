package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

	@Query("select e from Empleado e where e.departamento.idDepartamento=?1")
	List<Empleado> listarPorDepartamento(int idDepartamento);
	
	@Query("select e from Empleado e where e.perfil.idPerfil=?1")
	List<Empleado> listarPorPerfil(int idPerfil);
	
	@Query("select e from Empleado e where e.apellidos=?1")
	Empleado buscarPorNombre(String apellidos);
	
}
