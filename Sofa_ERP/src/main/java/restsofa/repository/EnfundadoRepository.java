package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.Enfundado;

public interface EnfundadoRepository extends JpaRepository<Enfundado, Integer>{

	@Query("select c from Enfundado c where c.pedido.idPedido = ?1")
	List<Enfundado>  buscarPorIdPedido(int idPedido);
	
	@Query("select c from Enfundado c where c.estadoPedido.idEstado = ?1")
	List<Enfundado>  buscarPorEstado(int idEnfundado);
}
