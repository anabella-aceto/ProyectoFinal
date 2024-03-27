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
	
	private int idSofa;
	
	private int idCliente;
	
	private int cantidad;

	private int plazas;

	private int densCojin;

	private Date fecha;

	private double precio;
	
	private int idEmpleado;

}
