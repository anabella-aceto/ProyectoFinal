package restsofa.modelo.entities;

import java.util.Date;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tapizado")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tapizado {

	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="id_estado")
	private EstadoPedido estadoPedido;
	
	private Date fecha;
	
	
	}
