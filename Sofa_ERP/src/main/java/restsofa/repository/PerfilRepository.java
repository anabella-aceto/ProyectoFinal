package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restsofa.modelo.entities.Perfil;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * @version 1.0
 * 
 * Interfaz que define un repositorio para la entidad Perfil.
 * Extiende JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Perfil> El tipo de entidad gestionada por el repositorio.
 * @param <Integer> El tipo del identificador de la entidad Perfil.
 */

public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
