package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restsofa.modelo.entities.Sofa;

/**
 * Interfaz que define un repositorio para la entidad Sofa.
 * Extiende JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Sofa> El tipo de entidad gestionada por el repositorio.
 * @param <Integer> El tipo del identificador de la entidad Sofa.
 */

public interface SofaRepository extends JpaRepository<Sofa, Integer>{

}
