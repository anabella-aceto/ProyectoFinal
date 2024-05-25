package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Empleado;
import restsofa.restcontroller.EmpleadoRestController;

/**
 * @author Alberto Saboya, Anabella Av¡ceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "listarEmpleados" en
 *          EmpleadoRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `EmpleadoRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class EmpleadoRestControllerTestVerTodos {

	@Autowired
	private EmpleadoRestController empleadoRestController;

	/**
	 * Prueba para verificar que la respuesta del método listarEmpleados() del
	 * controlador no es nula.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarEmpleadosRespuestaNoNula() {
		// Llamar al método listarEmpleados() del controlador
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();

		// Verificar que la respuesta no sea nula
		assertNotNull(responseEntity);
	}

	/**
	 * Prueba para verificar el código de estado de la respuesta del método
	 * listarEmpleados() del controlador.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarEmpleadosCodigoEstado() {
		// Llamar al método listarEmpleados() del controlador
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();

		// Verificar el código de estado de la respuesta
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método
	 * listarEmpleados() del controlador no es nulo.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarEmpleadosCuerpoRespuestaNoNulo() {
		// Llamar al método listarEmpleados() del controlador
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();

		// Verificar que el cuerpo de la respuesta no sea nulo
		assertNotNull(responseEntity.getBody());
	}

	/**
	 * Prueba para verificar que el cuerpo de la respuesta del método
	 * listarEmpleados() del controlador es una lista.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarEmpleadosRespuestaListaEmpleados() {
		// Llamar al método listarEmpleados() del controlador
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();

		// Verificar que el cuerpo de la respuesta sea una lista de empleados
		assertTrue(responseEntity.getBody() instanceof List<?>);
	}

	/**
	 * Prueba para verificar que la lista de empleados devuelta por el método
	 * listarEmpleados() del controlador no está vacía.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarEmpleadosListaNoVacia() {
		// Llamar al método listarEmpleados() del controlador
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();

		// Verificar que la lista de empleados no esté vacía
		List<?> listaEmpleados = (List<?>) responseEntity.getBody();
		assertFalse(listaEmpleados.isEmpty(), "La lista de empleados no debería estar vacía");
	}

	/**
	 * Prueba para verificar si la lista de empleados contiene un empleado
	 * específico.
	 * 
	 * @Test Anota este método como una prueba JUnit.
	 */
	@Test
	public void testListarEmpleadosContieneEmpleadoEspecifico() {
		// Llamar al método listarEmpleados() del controlador
		ResponseEntity<?> responseEntity = empleadoRestController.listarEmpleados();

		// Verificar que la lista de empleados contenga un empleado específico
		List<Empleado> empleados = (List<Empleado>) responseEntity.getBody();
		boolean contieneEmpleadoEspecifico = false;
		for (Empleado empleado : empleados) {
			if (empleado.getIdEmpleado() == 4 || "Ana".equals(empleado.getNombre())) {
				contieneEmpleadoEspecifico = true;
				break;
			}
		}
		assertTrue(contieneEmpleadoEspecifico, "La lista debe contener empleados específicos");
	}
}
