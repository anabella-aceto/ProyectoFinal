package restsofa.modelo.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="costura")
public class Costura {
	
	private Pedido pedido;
	private EstadoPedido estadoPedido;
	private Date fecha;

}
