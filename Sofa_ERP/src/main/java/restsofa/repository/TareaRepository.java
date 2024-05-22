package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Tarea;

/**
 * @author Anabella Aceto
 * @version 1.0
 * 
 * Interfaz que define un repositorio para la entidad Tarea.
 * Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Tarea>   El tipo de entidad gestionada por el repositorio.
 * @param <Integer> El tipo del identificador de la entidad Tarea.
 */

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    /**
     * Busca una lista de tareas por el identificador del empleado.
     *
     * @param idEmpleado. El identificador del empleado.
     * @return Una lista de tareas asignadas al empleado con el identificador dado.
     */
    @Query("select t from Tarea t where t.empleado.idEmpleado = ?1")
    public List<Tarea> buscarPorEmpleado(int idEmpleado);

    @Query("select t from Tarea t where t.estado.idEstado=?1")
    public Tarea buscarPorestado(int idEstado);
    
 
}