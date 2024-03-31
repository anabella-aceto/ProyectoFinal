package restsofa.modelo.DTO;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data

public class EstadoPedidoDto {
	
	private int idEp;
	
	private int idPedido;
	
	private int idEstado;
	
	private Date fecha;

}
