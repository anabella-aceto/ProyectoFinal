package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Costura;

public interface CosturaRepository extends JpaRepository<Costura, Integer>{

	@Query("select c from Costura c where c.estadoPedido.idEstado = ?1")
	List<Costura> buscarCosturaPorEstado(int idEstado);
	
	@Query("select c from Costura c where c.pedido.idPedido = ?1")
	List<Costura>  buscarCosturaPorPedido(int idPedido);
}
