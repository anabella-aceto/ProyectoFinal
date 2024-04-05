package restsofa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.DetallePedido;


public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{

	@Query("select dp from DetallePedido dp where dp.pedido.idPedido=?1")
	public DetallePedido buscarPorPedido(int idPedido);
}
