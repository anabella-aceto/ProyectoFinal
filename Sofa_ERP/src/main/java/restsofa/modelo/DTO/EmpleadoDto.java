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
public class EmpleadoDto {
	
	private int idEmpleado;
	private String nombre;
	private String apellidos;
	private int idDepartamento;
	private int idPerfil;
	private Date fecha;
	private double salario;
	
	
	}
