package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import restsofa.modelo.entities.Estado;


public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	@Query("SELECT e FROM Estado e WHERE e.nombre = ?1")
	public Estado establecerPorDefecto(String nombre);
}
