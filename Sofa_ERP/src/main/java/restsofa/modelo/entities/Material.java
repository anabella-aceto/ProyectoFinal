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
 * Clase que representa un material.
 */

@Entity // Indica que esta clase es una entidad JPA
@Table(name="materiales") // Especifica el nombre de la tabla en la base de datos
@NoArgsConstructor // Anotación para generar un constructor sin argumentos
@AllArgsConstructor // Anotación para generar un constructor con argumentos
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente
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
