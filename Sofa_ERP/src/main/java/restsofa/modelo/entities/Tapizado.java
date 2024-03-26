package restsofa.modelo.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tapizado")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tapizado implements Serializable{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tapizado")
	private int idTapizado;
	
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="id_estado")
	private EstadoPedido estadoPedido;
	
	private Date fecha;
	
	
	}
