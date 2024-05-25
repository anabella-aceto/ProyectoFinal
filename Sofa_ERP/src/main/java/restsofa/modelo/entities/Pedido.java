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

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * @version 1.0
 * 
 * Clase que representa un pedido.
 */

@Entity // Indica que esta clase es una entidad JPA
@Table(name="pedidos") // Especifica el nombre de la tabla en la base de datos
@NoArgsConstructor // Anotación para generar un constructor sin argumentos
@AllArgsConstructor // Anotación para generar un constructor con argumentos
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;
	
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Empleado vendedor;

}
