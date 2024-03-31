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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="detalle_pedido")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class detalle_pedido implements Serializable{	
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id_deped")
		private int idDePed;
		
		@ManyToOne
		@JoinColumn(name="id_pedido")
		private int idPedido;
		
		@ManyToOne
		@JoinColumn(name="id_sofa")
		private int idSofa;
		
		private int cantidad;
	
		private int plazas;
	
		@Column(name="dens_cojin")
		private int densCojin;
		
		private Date fecha;
		
		private double precio;
		
		@ManyToOne
		@JoinColumn(name="id_estado")
		private int idEstado;
		
	

}
