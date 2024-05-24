package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.Tarea;

/**
 * @author Anabella Aceto
 * @version 1.0
 * 
 *          Interfaz que define un repositorio para la entidad Detalle de
 *          pedido. Extiende de JpaRepository para obtener métodos de acceso a
 *          datos comunes.
 *
 * @param <DetallePedido> El tipo de entidad gestionada por el repositorio.
 * @param <Integer>       El tipo del identificador de la entidad DetallePedido.
 */

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

	@Query("select dp from DetallePedido dp where dp.pedido.idPedido=?1")
	public DetallePedido buscarPorPedido(int idPedido);
	
	@Query("select dp from DetallePedido dp where dp.pedido.idPedido=?1")
	public List<DetallePedido> buscarPorIdPedido(int idPedido);
	
	@Query("select dp from DetallePedido dp where dp.idDePed=?1 and dp.pedido.idPedido=?2")
	public DetallePedido buscarPorDetalleYPedido( int idDeped, int idPedido);
		
}
