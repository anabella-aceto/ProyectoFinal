package restsofa.modelo.entities;

import java.io.Serializable;

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
public class Material implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_material")
	private int idMaterial; 
	
	private String nombre;
	
	private String descripcion;
	
	@Column(name="ref_material_prov")
	private String refMaterialProveedor;
	
	private int cantidad;
	
	private String categoria;
	
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor; 
	
	@Column(name="unidad_medida")
	private String unidadMedida;
	

}
