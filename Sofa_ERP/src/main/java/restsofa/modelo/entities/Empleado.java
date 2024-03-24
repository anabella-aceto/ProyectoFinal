package restsofa.modelo.entities;

<<<<<<< HEAD
import java.util.Date;

=======
>>>>>>> 24ee6ae367287d25b16827184b5c2254d0b6f572
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
=======
>>>>>>> 24ee6ae367287d25b16827184b5c2254d0b6f572
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="empleados")
<<<<<<< HEAD
=======

>>>>>>> 24ee6ae367287d25b16827184b5c2254d0b6f572
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_empleado")
	private int idEmpleado;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_empleado")
	private int id_empleado;
	
	private String nombre;
	private String apellidos;
	
	@ManyToOne
	@JoinColumn(name="id_depto")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;
	
	private Date fecha;
	
	private double salario;
	
}
