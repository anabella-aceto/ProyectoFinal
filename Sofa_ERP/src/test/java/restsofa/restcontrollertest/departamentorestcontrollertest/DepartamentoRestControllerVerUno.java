package restsofa.restcontrollertest.departamentorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Departamento;
import restsofa.restcontroller.DepartamentoRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método método "buscarUno" en
 * DepartamentoRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `DepartamentoRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class DepartamentoRestControllerVerUno {

	@Autowired
	private DepartamentoRestController departamentoRestController;

	/**
	 * Prueba del método "buscarUno".
	 *
	 * @Test
	 * Verifica que el código de estado de la respuesta sea OK.
	 * Obtiene el departamento del cuerpo de la respuesta.
	 * Verifica que el departamento no sea nulo.
	 * Verifica que el departamento tenga el idDepartamento correcto.
	 * Verifica si el nombre del departamento es correcto.
	 *
	 * @param idDepartamento El identificador del departamento a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
	 */
	@Test
	public void testUnoDepartamentoExiste() {
		int idDepartamento = 3;
		ResponseEntity<?> responseEntity = departamentoRestController.buscarUno(idDepartamento);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el departamento del cuerpo de la respuesta
		Departamento departamento = (Departamento) responseEntity.getBody();

		// Verifica que el departamento no sea nulo
		assertNotNull(departamento, "El departamento no debería ser nulo");

		// Verifica que el departamento tenga el idDepartamento correcto
		assertEquals(idDepartamento, departamento.getIdDepartamento(), "El idDepartamento no coincide");

		// Verifica si el nombre del departamento es correcto
		assertEquals("tapizado", departamento.getNombre(), "El nombre del departamento no coincide");
	}
	
    /**
     * Prueba del método "buscarUno" cuando el departamento no existe.
     * 
     * @Test
     * Verifica que el código de estado de la respuesta sea 404 (Not Found).
     * Verifica que el departamento obtenido del cuerpo de la respuesta sea nulo.
     *
	 * @param idDepartamento El identificador del departamento a buscar.
	 * @return ResponseEntity con el resultado de la búsqueda.
     */
    @Test
    public void testUnoDepartamentoNoExiste() {
        int idDepartamento = -1; // ID de un departamento que no existe
        ResponseEntity<?> responseEntity = departamentoRestController.buscarUno(idDepartamento);

        // Verifica que el código de estado de la respuesta sea 404 (Not Found)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Obtiene el departamento del cuerpo de la respuesta
        Departamento departamento = (Departamento) responseEntity.getBody();

        // Verifica que el departamento obtenido del cuerpo de la respuesta sea nulo
        assertNull(departamento, "El departamento debería ser nulo");
    }

}
