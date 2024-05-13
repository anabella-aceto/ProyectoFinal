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
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Departamento;
import restsofa.service.DepartamentoService;

/**
 * Controlador para la gestión de departamentos.
 */

@RestController
@RequestMapping("/departamento")
@CrossOrigin(origins = "*")
public class DepartamentoRestController {

	@Autowired
	private DepartamentoService departamentoService;

	/**
	 * Método que devuelve todos los departamentos.
	 *
	 * @return ResponseEntity con la lista de todos los departamentos si se obtienen
	 *         correctamente, o un mensaje si la lista está vacía.
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> listarTodos() {
		try {
			// Obtener la lista de todos los departamentos
			List<Departamento> lista = departamentoService.listarTodos();

			// Verificar si la lista no está vacía
			if (!lista.isEmpty()) {
				// Si hay departamentos, devolver la lista con un estado OK
				return ResponseEntity.ok(lista);
			} else {
				// Si la lista está vacía, devolver un mensaje con un estado OK
				return ResponseEntity.ok("No se encontraron departamentos");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Busca un departamento por su identificador.
	 *
	 * @param idDepartamento El identificador único del departamento a buscar.
	 * @return ResponseEntity con el departamento encontrado si existe, o un mensaje
	 *         de error si no se encuentra ningún departamento con el ID
	 *         proporcionado. Si el departamento se encuentra, el cuerpo de la
	 *         respuesta contendrá los detalles del departamento. En caso contrario,
	 *         el cuerpo de la respuesta contendrá un mensaje explicando que no se
	 *         encontró ningún departamento con el ID proporcionado.
	 */
	@GetMapping("/uno/{idDepartamento}")
	public ResponseEntity<?> buscarUno(@PathVariable int idDepartamento) {
		try {
			// Buscar el departamento por su identificador
			Departamento departamento = departamentoService.buscarUno(idDepartamento);

			// Verificar si el departamento existe
			if (departamento != null) {
				// Si el departamento se encuentra, devolverlo con un estado OK
				return ResponseEntity.ok().body(departamento);
			} else {
				// Si el departamento no se encuentra, devolver un mensaje con un estado NOT
				// FOUND
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontró el departamento con el ID: " + idDepartamento);
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Crea un departamento.
	 *
	 * @param departamento Los datos del departamento a crear.
	 * @return ResponseEntity con el departamento si fue creado correctamente, o un
	 *         mensaje de error si no se pudo dar de alta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Departamento departamento) {
		try {
			// Intentar insertar el departamento
			Departamento nuevoDepartamento = departamentoService.insertOne(departamento);

			// Verificar si se insertó correctamente
			if (nuevoDepartamento != null) {
				// Si el departamento se creó correctamente, devolverlo con un estado OK
				return ResponseEntity.ok(nuevoDepartamento);
			} else {
				// Si no se pudo insertar el departamento, devolver un mensaje de error con un
				// estado INTERNAL SERVER ERROR
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("No se pudo insertar el departamento");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Modifica un departamento.
	 *
	 * @param departamento El departamento a modificar.
	 * @return ResponseEntity con el departamento modificado y el mensaje de éxito,
	 *         o un mensaje de error si no se realizó la modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificarDepto(@RequestBody Departamento departamento) {
		try {
			// Buscar el departamento a modificar por su ID
			Departamento departamentoExistente = departamentoService.buscarUno(departamento.getIdDepartamento());

			// Verificar si se encontró el departamento a modificar
			if (departamentoExistente != null) {
				// Si se encontró el departamento, actualizarlo en la base de datos
				departamentoService.updateOne(departamento);
				// Devolver un mensaje de éxito con el departamento modificado
				return ResponseEntity.ok("Modificación realizada correctamente" + departamento);
			} else {
				// Si no se encontró el departamento, devolver un mensaje de error con estado
				// NOT FOUND
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Elimina un departamento por su identificador.
	 *
	 * @param idDepartamento El identificador único del departamento a eliminar.
	 * @return ResponseEntity con un mensaje indicando el resultado de la
	 *         eliminación.
	 */
	@DeleteMapping("/eliminar/{idDepartamento}")
	public ResponseEntity<?> eliminarDepto(@PathVariable("idDepartamento") int idDepartamento) {
		try {
			// Intentar eliminar el departamento
			boolean eliminado = departamentoService.deleteOne(idDepartamento);
			// Verificar si el departamento fue eliminado correctamente
			if (eliminado) {
				// Si el departamento fue eliminado correctamente, devolver un mensaje de éxito
				return ResponseEntity.ok("Departamento eliminado correctamente");
			} else {
				// Si el departamento no fue encontrado para eliminar, devolver un mensaje con
				// estado NOT FOUND
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolver un error interno del servidor
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

}
