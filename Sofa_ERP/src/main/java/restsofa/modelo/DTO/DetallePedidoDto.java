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

public class DetallePedidoDto {
	
	private int idDePed;
	
	private int idPedido;
	
	private int idSofa;
	
	private int cantidad;
	
	private int plazas;
	
	private double densCojin;
	
	private Date fecha;
	
	private double precio;
	
	private int idEstado;

}
