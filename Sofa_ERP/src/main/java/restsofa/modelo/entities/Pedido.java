package restsofa.modelo.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;
	
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="id_ep")
	private EstadoPedido estadoPedido;
	
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Empleado vendedor;

}
