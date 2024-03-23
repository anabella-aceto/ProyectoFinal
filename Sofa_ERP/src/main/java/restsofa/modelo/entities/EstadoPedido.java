package restsofa.modelo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="estado_pedido")
public class EstadoPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_estado")
	private int idEstado;
	
	private String nombre;

}
