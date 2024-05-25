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
 * Clase que representa un departamento.
 */

@Entity // Indica que esta clase es una entidad JPA
@Table(name="detalle_pedido")
@NoArgsConstructor // Anotación para generar un constructor sin argumentos
@AllArgsConstructor // Anotación para generar un constructor con argumentos
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente

public class DetallePedido implements Serializable{	
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id_deped")
		private int idDePed;
		
		@ManyToOne
		@JoinColumn(name="id_pedido")
		private Pedido pedido;
		
		@ManyToOne
		@JoinColumn(name="id_sofa")
		private Sofa sofa;
		
		private Integer cantidad;
	
		private Integer plazas;
	
		@Column(name="dens_cojin")
		private Integer densCojin;
		
		private Date fecha;
		
		private Double precio;	

}
