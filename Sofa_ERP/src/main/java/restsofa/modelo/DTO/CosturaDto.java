package restsofa.modelo.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CosturaDto {
	
	private int idCostura;

	private int idPedido;
	
	private int idEstado;
	
	private Date fecha;

}
