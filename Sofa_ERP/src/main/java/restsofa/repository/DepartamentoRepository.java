package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restsofa.modelo.entities.Departamento;

/**
 * @author Anabella Aceto
 * @version 1.0
 * 
 * Interfaz que define un repositorio para la entidad Departamento. 
 * Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Departamento> El tipo de entidad gestionada por el repositorio.
 * @param <Integer> El tipo del identificador de la entidad Departamento.
 */

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
