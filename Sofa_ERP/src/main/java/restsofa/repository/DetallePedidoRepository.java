package restsofa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.DetallePedido;

/**
 * @author Anabella Aceto
 * @version 1.0
 * 
 * Interfaz que define un repositorio para la entidad Detalle de pedido.
 * Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <DetallePedido> El tipo de entidad gestionada por el repositorio.
 * @param <Integer> El tipo del identificador de la entidad DetallePedido.
 */

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

	@Query("select dp from DetallePedido dp where dp.pedido.idPedido=?1")
	public DetallePedido buscarPorPedido(int idPedido);
}
