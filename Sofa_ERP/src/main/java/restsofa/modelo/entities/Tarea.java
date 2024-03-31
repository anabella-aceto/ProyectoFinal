package restsofa.modelo.entities;

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
@Table(name = "tareas")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Tarea {

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
	private EstadoPedido estado;

	private Date fecha; 
}
