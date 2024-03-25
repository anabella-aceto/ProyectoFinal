package restsofa.modelo.entities;

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
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;
	
	
	@ManyToOne
	@JoinColumn(name="sofa")
	private Sofa sofa;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	private int cantidad;
	
	private int plazas;
	
	@Column(name="dens_cojin")
	private int densCojin;
	
	private Date fecha;
	
	private double precio;
	
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Empleado vendedor;

}
