package restsofa.modelo.entities;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="empleados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empleado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_empleado")
	private int idEmpleado;
	
	private String nombre;
	
	private String apellidos;
	
	private String password;
	
	@ManyToOne
	@JoinColumn(name="id_depto")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

	@Column(name="fecha_ingreso")
	private Date fechaIngreso;
	
	@Column(name="fecha_baja")
	private Date fechaBaja;
	
	private int estado;
	
	private double salario;
	
}
