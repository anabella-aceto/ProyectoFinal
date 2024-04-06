package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import restsofa.modelo.entities.Estado;

/**
* Interfaz que define un repositorio para la entidad Estado.
* Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
*
* @param <Estado> El tipo de entidad gestionada por el repositorio.
* @param <Integer> El tipo del identificador de la entidad Estado.
*/


public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	@Query("SELECT e FROM Estado e WHERE e.nombre = ?1")
	public Estado establecerPorDefecto(String nombre);
}
