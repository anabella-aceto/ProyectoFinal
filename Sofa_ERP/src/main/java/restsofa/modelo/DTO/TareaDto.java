package restsofa.modelo.DTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data

public class TareaDto {
	
	private int idTarea;
	
	private int idEmpleado;
	
	private int idDepartamento;
	
	private int idPedido;

}
