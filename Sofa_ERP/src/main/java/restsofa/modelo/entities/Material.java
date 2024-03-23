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
@Table(name="materiales")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Material {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_material")
	private int idMaterial; 
	
	private String nombre;
	
	private String descripcion;
	
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor; 
	

}
