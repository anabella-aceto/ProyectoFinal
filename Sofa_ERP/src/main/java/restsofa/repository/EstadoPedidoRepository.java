package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.EstadoPedido;

public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer>{
	
	@Query("select ep from EstadoPedido ep where ep.estado.nombre=?1")
	EstadoPedido buscarPorNombreEstado(String nombre);

}
