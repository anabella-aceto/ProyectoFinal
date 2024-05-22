package restsofa.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Departamento;
import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.Empleado;
import restsofa.modelo.entities.Estado;
import restsofa.modelo.entities.Pedido;
import restsofa.modelo.entities.Tarea;
import restsofa.repository.TareaRepository;


/**
 * Implementación de la interfaz TareaService.
 */
@Service
public class TareaServiceImplMy8Jpa implements TareaService {

	@Autowired
	private TareaRepository tarepo;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private DetallePedidoService detallePedidoService;

	/**
	 * Método que busca una tarea por su identificador.
	 *
	 * @param idTarea El identificador único de la tarea a buscar.
	 * @return La tarea encontrada, o null si no se encuentra.
	 */
	@Override
	public Tarea buscarTarea(int idTarea) {
		return tarepo.findById(idTarea).orElse(null);
	}

	/**
	 * Método que devuelve una lista de todas las tareas.
	 *
	 * @return Una lista de todas las tareas.
	 */
	@Override
	public List<Tarea> buscarTodasTareas() {
		return tarepo.findAll();
	}

	/**
	 * Método que crea una nueva tarea.
	 *
	 * @param tarea La tarea a crear.
	 * @return La tarea insertada.
	 */
	@Override
	public Tarea altaTarea(Tarea tarea) {
         return tarepo.save(tarea);
	}
	/**
	 * Método que modifica una tarea existente.
	 *
	 * @param tarea La tarea a modificar.
	 * @return La tarea modificada, o null si hay un error.
	 */
	@Override
	public Tarea modifTarea(Tarea tarea) {
		try {
			return tarepo.save(tarea);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Método que elimina una tarea por su identificador.
	 *
	 * @param idTarea El identificador único de la tarea a eliminar.
	 * @return true si se eliminó correctamente, false si no se pudo eliminar.
	 */
	@Override
	public boolean borrarTarea(int idTarea) {
		try {
			if (buscarTarea(idTarea) != null) {
				tarepo.deleteById(idTarea);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que busca todas las tareas asociadas a un empleado por su
	 * identificador.
	 *
	 * @param idEmpleado El identificador único del empleado.
	 * @return Una lista de todas las tareas asociadas al empleado.
	 */
	@Override
	public List<Tarea> buscarPorIdEmpleado(int idEmpleado) {
		return tarepo.buscarPorEmpleado(idEmpleado);
	}

	/**
	 * Método que actualiza el estado de una tarea basado en el identificador de
	 * pedido, identificador de empleado y identificador de departamento
	 * proporcionados.
	 * 
	 * @param idPedido       El identificador único del pedido (tarea) a actualizar.
	 * @param idEmpleado     El identificador único del empleado asociado con la
	 *                       tarea.
	 * @param idDepartamento El identificador único del departamento asociado con la
	 *                       tarea.
	 * @return Devuelve 1 si el estado de la tarea se actualiza a estado1, devuelve
	 *         2 si el estado de la tarea se actualiza a estado2, devuelve 0 si no
	 *         se encuentra el pedido o el estado del pedido no es 1 o 2.
	 */
	@Override
	public int altaEstadoTarea(int idDeped, int idEmpleado, int idDepartamento) {
		DetallePedido pedido = detallePedidoService.buscarDetPed(idDeped);
		Tarea tarea1 = tarepo.buscarPorestado(1);
		Estado estado1 = estadoService.buscarEstado(2);
		Estado estado2 = estadoService.buscarEstado(3);
		
		Empleado empleado = empleadoService.buscarUno(idEmpleado);

		if (pedido != null && tarea1 == null ) {
			Tarea tarea = new Tarea();
			tarea.setDetalle(pedido);
			tarea.setEmpleado(empleado);
			tarea.setEstado(estado1);
			tarea.setDepartamento(departamentoService.buscarUno(idDepartamento));
			tarea.setFecha(new Date());
			tarepo.save(tarea);
			
			return 1;
		}

		if (pedido != null && tarea1!=null) {
			Tarea tarea = new Tarea();
			tarea.setDetalle(pedido);
			tarea.setEstado(estado2);
			tarea.setDepartamento(departamentoService.buscarUno(idDepartamento));
			tarea.setFecha(new Date());
			tarepo.save(tarea);
			
			return 2;
			
		} else {
			return 0;
		}
	}

	@Override
	public Tarea buscarPorEstado(int idEstado) {
		// TODO Auto-generated method stub
		return tarepo.buscarPorestado(idEstado);
	}
}
