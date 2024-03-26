package restsofa.modelo.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Proveedor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor")
	private int idProveedor;
	

	    private String nombre;
	    
    
    private int telefono;
    
      private String descripcion; 
}
