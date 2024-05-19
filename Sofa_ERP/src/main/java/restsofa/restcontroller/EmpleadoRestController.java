package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.DTO.EmpleadoDto;
import restsofa.modelo.entities.Empleado;
import restsofa.modelo.entities.Perfil;
import restsofa.service.DepartamentoService;
import restsofa.service.EmpleadoService;
import restsofa.service.PerfilService;

/**
 * Controlador para la gestión de empleados.
 */

@RestController
@RequestMapping("/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoRestController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private PerfilService perfilService;

	/**
	 * Método que busca un empleado por su identificador único.
	 *
	 * @param idEmpleado El identificador único del empleado a buscar.
	 * @return ResponseEntity con el empleado encontrado si existe, o un mensaje de
	 *         error si no existe.
	 */
	@GetMapping("/uno/{idEmpleado}") // probado y funcionando
	public ResponseEntity<?> buscarPorId(@PathVariable("idEmpleado") int idEmpleado) {
		try {
			Empleado empleado = empleadoService.buscarUno(idEmpleado);
			if (empleado != null)
				return ResponseEntity.status(HttpStatus.OK).body(empleado);
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el empleado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/**
	 * Método que permite cargar datos de un nuevo empleado.
	 *
	 * @param empleadoDto El DTO del empleado a dar de alta.
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         alta.
	 */
	@PostMapping("/alta") // probado y funcionando
	public ResponseEntity<?> alta(@RequestBody EmpleadoDto empleadoDto) {
		try {
			Empleado empleado = new Empleado();
			empleado.setDepartamento(departamentoService.buscarUno(empleadoDto.getIdDepartamento()));
			empleado.setPerfil(perfilService.buscarUno(empleadoDto.getIdPerfil()));
			empleado.setNombre(empleadoDto.getNombre());
			empleado.setApellidos(empleadoDto.getApellidos());
			empleado.setEstado("activo");
			empleado.setFechaIngreso(empleadoDto.getFechaIngreso());
			empleado.setSalario(empleadoDto.getSalario());

			Empleado empNuevo = empleadoService.altaEmpleado(empleado);

			if (empNuevo != null) {
				return ResponseEntity.status(HttpStatus.OK).body(empNuevo);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al insertar datos de empleado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/**
	 * Método que elimina un empleado.
	 *
	 * @param idEmpleado El identificador único del empleado a eliminar.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/eliminar/{idEmpleado}") // probado y funcionando
	public ResponseEntity<?> borrarUno(@PathVariable("idEmpleado") int idEmpleado) {
		try {
			Empleado empleado = empleadoService.buscarUno(idEmpleado);

			if (empleado != null) {
				empleadoService.deleteOne(idEmpleado);
				return ResponseEntity.status(HttpStatus.OK).body("Empleado eliminado correctamente");
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al borrar empleado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/**
	 * Método que obtiene todos los empleados.
	 *
	 * @return ResponseEntity con la lista de todos los empleados si se obtienen
	 *         correctamente, o un mensaje de error si no hay elementos en la lista.
	 */
	@GetMapping("/todos") // probado y funcionando
	public ResponseEntity<?> listarEmpleados() {
		try {
			List<Empleado> lista = empleadoService.buscarTodos();

			if (!lista.isEmpty())
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/**
	 * Busca empleados por el identificador del departamento.
	 *
	 * @param idDepartamento El identificador único del departamento para filtrar
	 *                       los empleados.
	 * @return ResponseEntity con la lista de empleados encontrados si existen, o un
	 *         mensaje de error si no hay empleados en ese departamento.
	 */
	@GetMapping("/porDepto/{idDepartamento}") // probado y funcionando
	public ResponseEntity<?> buscarPorDpto(@PathVariable("idDepartamento") int idDepartamento) {
		try {
			List<Empleado> lista = empleadoService.buscarPorDepto(idDepartamento);

			if (!lista.isEmpty())
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/**
	 * Método que modifica los datos de un empleado.
	 *
	 * @param empleadoDto El DTO del empleado a modificar.
	 * @return ResponseEntity con el mensaje del resultado de la modificación.
	 */
	@PutMapping("/modificar") // probado y funcionnado
	public ResponseEntity<?> modificarEmpleado(@RequestBody EmpleadoDto empleadoDto) {
		try {
			Empleado empleado = empleadoService.buscarUno(empleadoDto.getIdEmpleado());

			if (empleado != null) {
				empleadoDto.setIdEmpleado(empleado.getIdEmpleado());
				empleado.setDepartamento(departamentoService.buscarUno(empleadoDto.getIdDepartamento()));
				empleado.setPerfil(perfilService.buscarUno(empleadoDto.getIdPerfil()));
				empleado.setApellidos(empleadoDto.getApellidos());
				empleado.setFechaIngreso(empleadoDto.getFechaIngreso());
				empleado.setNombre(empleadoDto.getNombre());
				empleado.setSalario(empleadoDto.getSalario());
				empleadoService.modificarEmpleado(empleado);

				return ResponseEntity.status(HttpStatus.OK).body("Modificación realizada correctamente " + empleado);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al insertar datos de empleado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/**
	 * Método que lista los empleados por el identificador de perfil.
	 *
	 * @param idPerfil El identificador único del perfil para filtrar los empleados.
	 * @return ResponseEntity con la lista de empleados encontrados si existe el
	 *         perfil, o un mensaje de error si no existe.
	 */
	@GetMapping("/porPerfil/{idPerfil}") // probado y funcionando
	public ResponseEntity<?> listarPorPerfil(@PathVariable("idPerfil") int idPerfil) {
		try {
			Perfil perfil = perfilService.buscarUno(idPerfil);

			if (perfil != null) {
				List<Empleado> lista = empleadoService.buscarPorPerfil(idPerfil);
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el perfil");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

	/**
	 * Método que busca un empleado por su apellido.
	 *
	 * @param apellidos Primer apellido del empleado a buscar.
	 * @return ResponseEntity con el empleado encontrado si existe, o un mensaje de
	 *         error si no existe.
	 */
	@GetMapping("/porApellido") // probado y funcionando
	public ResponseEntity<?> buscarPorApellido(@RequestParam String apellidos) {
		try {
			Empleado empleado = empleadoService.buscarPorApellidos(apellidos);

			if (empleado != null)
				return ResponseEntity.status(HttpStatus.OK).body(empleado);
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún empleado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
		}
	}

}
