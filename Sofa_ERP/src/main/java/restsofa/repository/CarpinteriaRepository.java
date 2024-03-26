package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restsofa.modelo.entities.Carpinteria;

public interface CarpinteriaRepository extends JpaRepository<Carpinteria, Integer>{
	
	@Query("select c from Carpinteria c where c.estadoPedido.idEstado = ?1")
	List<Carpinteria> buscarPorEstado();
	
	@Query("select c from Carpinteria c where c.pedido.idPedido = ?1")
	List<Carpinteria>  buscarPorIdPedido(int idPedido);
	
	@Query("select c from Carpinteria c where c.pedido.idPedido = ?1 and c.estadoPedido.idEstado=?2")
	List<Carpinteria>  buscarPorIdPedidoyEstado(int idPedido, int estado);
	
	
}
