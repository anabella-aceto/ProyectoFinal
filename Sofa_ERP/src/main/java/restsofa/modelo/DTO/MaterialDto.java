package restsofa.modelo.DTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialDto {
	
	private String nombre;
	
	private String descripcion;
	
	private int cantidad;
	
	private int idProveedor; 
	
	private String refMaterialProveedor;
	
	
	
	
	

}
