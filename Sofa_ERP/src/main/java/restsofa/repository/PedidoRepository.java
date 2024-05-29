package restsofa.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	 * 
	 * @param fechaFin    La fecha de fin del rango.
	 * 
	 * @return Una lista de pedidos cuyas fechas están dentro del rango dado.
	 */
	@Query("select p from Pedido p where p.fecha >=?1 and p.fecha <=?2")
	public List<Pedido> buscarPorFecha(Date fechaInicio, Date fechaFin);
	
	/**
     * Encuentra los pedidos que fueron realizados en la fecha actual.
     *
     * @return una lista de pedidos que fueron realizados hoy.
     */
	 @Query("select p from Pedido p where DATE(p.fecha) = CURRENT_DATE")
	   public List<Pedido> findPedidosDeHoy();
	 
	 /**
     * Encuentra los pedidos que fueron realizados desde el inicio del mes especificado.
     *
     * @param startOfMonth la fecha y hora que marca el inicio del mes
     * 
     * @return una lista de objetos Pedido que fueron realizados desde el inicio del mes.
     */
	 @Query("select p from Pedido p where p.fecha >= :startOfMonth")
	 public List<Pedido> findPedidosDesdeInicioMes(@Param("startOfMonth") LocalDateTime startOfMonth);
	 
	 /**
     * Encuentra los pedidos que fueron realizados desde el inicio del trimestre.
     *
     * @param startOfMonth la fecha y hora que marca el inicio del trimestre
     * 
     * @return una lista de objetos Pedido que fueron realizados desde el inicio del trimestre.
     */
	 @Query("select p from Pedido p where p.fecha >= :startOfQuarter")
	 public List<Pedido> findPedidosDesdeInicioTrimestre(@Param("startOfQuarter") LocalDateTime startOfQuarter);
	
}