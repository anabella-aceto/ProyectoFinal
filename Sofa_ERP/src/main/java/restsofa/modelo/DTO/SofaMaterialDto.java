package restsofa.modelo.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SofaMaterialDto {

	private int idSofa;
	private int idMaterial;
	private int cantidadUtilizada; 
	
	
}
