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

/**
 * Clase que representa un empleado.
 */

@Entity // Indica que esta clase es una entidad JPA
@Table(name="empleados")
@NoArgsConstructor // Anotación para generar un constructor sin argumentos
@AllArgsConstructor // Anotación para generar un constructor con argumentos
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente
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
	
	/**
	 * Marca el empleado como inactivo al establecer el atributo 'estado' en 0 (baja).
	 *
	 * @param fechaBaja La fecha de fin del contrato del empleado.
	 */
	public void darDeBaja(Date fechaBaja) {
	    // Cambia el atributo "estado" a 0 (baja)
	    this.estado = 0;
	    // Cambia el atributo "fechaBaja" a la fecha que se le introduce por parámetro
	    this.fechaBaja = fechaBaja;
	}
	
	public void darDeAltaEmpAntiguo(Date fechaAlta) {
	    // Cambia el atributo "estado" a 1 (alta)
	    this.estado = 1;
	    // Cambia el atributo "fechaIngreso" a la fecha que se le introduce por parámetro
	    this.fechaIngreso = fechaAlta;
	}
	
}
