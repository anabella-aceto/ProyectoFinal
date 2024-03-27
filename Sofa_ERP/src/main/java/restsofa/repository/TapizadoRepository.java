package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Tapizado;

public interface TapizadoRepository extends JpaRepository<Tapizado, Integer>{

	@Query("select t from Tapizado t where t.estadoPedido.idEstado = ?1")
	List<Tapizado> buscarPorEstado(int idEstado);
	
	@Query("select t from Tapizado t where t.pedido.idPedido = ?1")
	List<Tapizado>  buscarPorPedido(int idPedido);
}
