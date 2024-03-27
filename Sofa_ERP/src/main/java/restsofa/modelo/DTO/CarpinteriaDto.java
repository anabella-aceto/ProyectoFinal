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
public class CarpinteriaDto {
	
	private int idCarpinteria;

	private int idPedido;
	
	private int idEstadoPedido;
	
	private Date fecha;
}
