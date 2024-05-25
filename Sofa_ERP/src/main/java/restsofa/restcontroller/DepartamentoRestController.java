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
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
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
			List<Departamento> lista = departamentoService.listarTodos();

			if (!lista.isEmpty()) {
				return ResponseEntity.ok(lista);
			} else {
				return ResponseEntity.ok("No se encontraron departamentos");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Método que busca un departamento por su identificador.
	 *
	 * @param idDepartamento El identificador único del departamento a buscar.
	 * 
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
			Departamento departamento = departamentoService.buscarUno(idDepartamento);

			if (departamento != null) {
				return ResponseEntity.ok().body(departamento);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No se encontró el departamento con el ID: " + idDepartamento);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Crea un departamento.
	 *
	 * @param departamento Los datos del departamento a crear.
	 * 
	 * @return ResponseEntity con el departamento si fue creado correctamente, o un
	 *         mensaje de error si no se pudo dar de alta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Departamento departamento) {
		try {
			Departamento nuevoDepartamento = departamentoService.insertOne(departamento);

			if (nuevoDepartamento != null) {
				return ResponseEntity.ok(nuevoDepartamento);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("No se pudo insertar el departamento");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Método que permite actualizar un departamento.
	 *
	 * @param departamento El departamento a modificar.
	 * 
	 * @return ResponseEntity con el departamento modificado y el mensaje de éxito,
	 *         o un mensaje de error si no se realizó la modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificarDepto(@RequestBody Departamento departamento) {
		try {
			Departamento departamentoExistente = departamentoService.buscarUno(departamento.getIdDepartamento());

			if (departamentoExistente != null) {				
				departamentoService.updateOne(departamento);
				return ResponseEntity.ok("Modificación realizada correctamente" + departamento);
			
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

	/**
	 * Método que elimina un departamento por su identificador. 
	 * 
	 * @param idDepartamento El identificador único del departamento a eliminar.
	 * 
	 * @return ResponseEntity con un mensaje que contiene un mensaje de confirmación, un mensaje de 
	 * 		   que el departamento no fue encontrado, o un mensaje de error si se produce una excepción.	 
	 */
	
	@DeleteMapping("/eliminar/{idDepartamento}")
	public ResponseEntity<?> eliminarDepto(@PathVariable("idDepartamento") int idDepartamento) {
		try {
			boolean eliminado = departamentoService.deleteOne(idDepartamento);
			if (eliminado) {
				return ResponseEntity.ok("Departamento eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al procesar la solicitud: " + e.getMessage());
		}
	}

}
