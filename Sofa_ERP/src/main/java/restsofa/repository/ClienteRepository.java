package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restsofa.modelo.entities.Cliente;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * @version 1.0
 * 
 * Interfaz que define un repositorio para la entidad Cliente.
 * Extiende JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Cliente> El tipo de entidad gestionada por el repositorio.
 * @param <Integer> El tipo del identificador de la entidad Cliente.
 */

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
