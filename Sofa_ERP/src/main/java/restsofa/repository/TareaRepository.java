package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer>{

	
	@Query("select t from Tarea t where t.empleado.idEmpleado = ?1")
	public List<Tarea> buscarPorEmpleado(int idEmpleado);
}
