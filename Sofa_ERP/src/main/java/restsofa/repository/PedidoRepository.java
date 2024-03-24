package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restsofa.modelo.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
