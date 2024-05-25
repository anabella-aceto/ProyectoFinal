package restsofa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Pedido;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * @version 1.0
 * 
 * Interfaz que define un repositorio para la entidad Pedido. 
 * Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <Pedido>  El tipo de entidad gestionada por el repositorio.
 * @param <Integer> El tipo del identificador de la entidad Pedido.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	/**
	 * Busca una lista de pedidos por rango de fecha.
	 *
	 * @param fechaInicio La fecha de inicio del rango.
	 * @param fechaFin    La fecha de fin del rango.
	 * @return Una lista de pedidos cuyas fechas están dentro del rango dado.
	 */
	@Query("select p from Pedido p where p.fecha >=?1 and p.fecha <=?2")
	public List<Pedido> buscarPorFecha(Date fechaInicio, Date fechaFin);
	
}