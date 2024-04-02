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

public class PedidoDto {
	
	private int idPedido;
	
	private int idCliente;	
	
	private Date fecha;	
	
	private int idEstado;
	
	private int idEmpleado;

}
