package restsofa.modelo.DTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialDTO {
	
	private String nombre;
	
	private String descripcion;
	
	
	private String refMaterialProveedor;
	
	private int cantidad;
	
	
	private int idProveedor; 
	

}
