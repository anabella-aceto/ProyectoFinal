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
@Table(name = "sofas")
public class Sofa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sofa")
	private int idSofa;
	
	private String nombre;
	
	@Column(name="descripción")
	private String descripcion;
	
	private int patas;
	
	@Column(name="medida_cojin")
	private double medidaCojin;
	
	private double precio;

}
