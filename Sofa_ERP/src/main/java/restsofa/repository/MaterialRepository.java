package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer>{

	@Query("select m from Material m where m.nombre=?1")
	List<Material> findByName(String nombre);
	
	@Query("select m from Material m  where m.refMaterialProveedor=?1")
	 Material buscarPorProvedor(String refMaterialProveedor);
	
}
