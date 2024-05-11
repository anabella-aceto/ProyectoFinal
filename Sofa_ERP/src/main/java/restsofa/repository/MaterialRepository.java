package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Material;

/**
 * @author Anabella Aceto
 * @version 1.0
 * 
 * Interfaz que define un repositorio para la entidad Material.
 * Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Material> El tipo de entidad gestionada por el repositorio.
 * @param <Integer>  El tipo del identificador de la entidad Material.
 */
public interface MaterialRepository extends JpaRepository<Material, Integer> {

	/**
	 * Busca una lista de materiales por su nombre.
	 *
	 * @param nombre El nombre del material.
	 * @return Una lista de materiales que tienen el nombre dado.
	 */
	@Query("select m from Material m where m.nombre=?1")
	List<Material> findByName(String nombre);

	/**
	 * Busca un material por su referencia proporcionada por el proveedor.
	 *
	 * @param refMaterialProveedor La referencia del material proporcionada por el
	 *                             proveedor.
	 * @return El material encontrado, o null si no se encuentra ningún material con
	 *         esa referencia de proveedor.
	 */
	@Query("select m from Material m  where m.refMaterialProveedor=?1")
	Material buscarPorProvedor(String refMaterialProveedor);

	/**
	 * Busca una lista de materiales por el identificador del proveedor.
	 *
	 * @param idProveedor El identificador del proveedor.
	 * @return Una lista de materiales que son suministrados por el proveedor con el
	 *         ID dado.
	 */
	@Query("select m from Material m  where m.proveedor.idProveedor=?1")
	List<Material> buscarMaterialPorProveedor(int idProveedor);

	/**
	 * Busca una lista de materiales por su categoría.
	 *
	 * @param categoria La categoría del material.
	 * @return Una lista de materiales que pertenecen a la categoría dada.
	 */
	@Query("select m from Material m where m.categoria=?1")
	List<Material> findBycategoria(String categoria);
}
