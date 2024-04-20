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
 * @author David Rodriguez Moral
 * @version 1.0
 * 
 * Clase que representa una tarea.
 */

@Entity //Indica que esta clase es una entidad JPA.
@NoArgsConstructor // Anotación para generar un constructor sin argumentos.
@AllArgsConstructor // Anotación para generar un constructor con argumentos.
@Data // Anotación Lombok para generar getters, setters, toString, equals, y hashCode automáticamente.
@Table(name = "tareas") // Especifica el nombre de la tabla en la base de datos.

public class Tarea implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarea")
	private int idTarea;

	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;

	@ManyToOne
	@JoinColumn(name = "id_depto")
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

	private Date fecha; 
}
