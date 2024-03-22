package restsofa.modelo.entities;

import java.util.Date;

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
@Table(name="pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;
	private Cliente cliente;
	private Sofa sofa;
	private int cantidad;
	private int plazas;
	@Column(name="dens_cojin")
	private int densCojin;
	private Date fecha;
	private double precio;
	private Empleado vendedor;

}
