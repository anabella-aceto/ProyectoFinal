package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Carpinteria;

public interface CarpinteriaRepository extends JpaRepository<Carpinteria, Integer>{
	
	@Query("select c from Carpinteria c join c.estadoPedido ep where ep.idEstado = ?1")
	List<Carpinteria> buscarPorEstado();
	
	@Query("select c from Carpinteria c where c.pedido.idPedido = ?1")
	List<Carpinteria>  buscarPorIdPedido(int idPedido);
}
