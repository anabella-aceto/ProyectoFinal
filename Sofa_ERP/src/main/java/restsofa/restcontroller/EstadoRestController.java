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

import restsofa.modelo.entities.Estado;
import restsofa.service.EstadoService;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Controlador para la gestión de los estados.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estado")

public class EstadoRestController {

	@Autowired
	private EstadoService estadoService;

	/**
	 * Método que devuelve todos los estados de pedidos.
	 *
	 * @return ResponseEntity con el lista de estados de pedido si se pudo cargar
	 *         correctamente, o un mensaje de error si no.
	 */
	@GetMapping({ "/todos" })
	public ResponseEntity<?> todos() {
		try {
			List<Estado> lista = estadoService.buscarTodosEstado();
			if (lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron estados de pedido");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(lista);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error interno del servidor al cargar la lista de estados de pedido");
		}
	}

	/**
	 * Método que permite obtener un estado de pedido por su identificador.
	 *
	 * @param idEstado El identificador único del estado de pedido a buscar.
	 * 
	 * @return ResponseEntity con el estado de pedido encontrado si existe, o un
	 *         mensaje de error si no existe.
	 */
	@GetMapping("/uno/{idEstado}")
	public ResponseEntity<?> uno(@PathVariable int idEstado) {
		try {
			Estado estado = estadoService.buscarEstado(idEstado);

			if (estado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(estado);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("El estado de pedido con el ID proporcionado no existe");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error interno del servidor al buscar el estado de pedido");
		}
	}

	/**
	 * Método que permite crear un estado de pedido.
	 * 
	 * @param estado El estado de pedido a dar de alta.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         alta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> alta(@RequestBody Estado estado) {
		try {
			Estado estadoAlta = new Estado();
			estadoAlta.setNombre(estado.getNombre());

			if (estadoService.altaEstado(estado) != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body("Estado de pedido procesado correctamente" + estadoAlta);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar el estado de pedido");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error interno del servidor al procesar el estado de pedido");
		}
	}

	/**
	 * Método que modifica un estado pedido.
	 * 
	 * @param estado El estado de pedido con la información actualizada.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         modificación.
	 */
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody Estado estado) {
		try {
			Estado estadoModif = estadoService.buscarEstado(estado.getIdEstado());

			if (estadoModif != null) {
			    estadoModif.setNombre(estado.getNombre());
			    estadoService.modifEstado(estadoModif);
			    return ResponseEntity.status(HttpStatus.OK).body("Estado de pedido modificado correctamente");
			} else {
			    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede modificar el estado de pedido");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error interno del servidor al modificar el estado de pedido");
		}
	}

	/**
	 * Método que borra un estado pedido.
	 * 
	 * @param idEstado El identificador único del estado a eliminar.
	 * 
	 * @return ResponseEntity con un mensaje indicando el resultado del proceso de
	 *         eliminación.
	 */
	@DeleteMapping("/borrar/{idEstado}")
	public ResponseEntity<?> borrar(@PathVariable int idEstado) {
		try {
			Estado estadoBorrar = estadoService.buscarEstado(idEstado);

			if (estadoBorrar != null) {
				estadoService.borrarEstado(idEstado);
				return ResponseEntity.status(HttpStatus.OK).body("Estado de pedido eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado de pedido no se ha podido eliminar");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error interno del servidor al eliminar el estado de pedido");
		}
	}

}
