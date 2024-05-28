package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Estado;
import restsofa.modelo.entities.Tarea;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
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
   
    /**
     * Busca una tarea por el identificador de estado.
     *
     * @param idEstado El identificador del estado.
     * @return La tarea asociada al estado con el identificador dado, si existe; de lo contrario, null.
     */
    @Query("select t from Tarea t where t.estado.idEstado=?1")
    public Tarea buscarPorEstado(int idEstado);    
    
    /**
     * Busca una lista de tareas por el identificador del departamento.
     *
     * @param idDepartamento El identificador del departamento.
     * @return Una lista de tareas asignadas al departamento con el identificador dado.
     */
    @Query("select t from Tarea t where t.departamento.idDepartamento = ?1")
    public List<Tarea> buscarPorDepartamento(int idDepartamento);
    
    @Query("select t from Tarea t where t.detalle.idDePed = ?1")
    public List<Tarea> buscarPorDetalle(int idDePed);
 
    @Query("select t from Tarea t where t.estado.idEstado = ?1")
    public List<Tarea> listarPorEstado(int idEstado);
}