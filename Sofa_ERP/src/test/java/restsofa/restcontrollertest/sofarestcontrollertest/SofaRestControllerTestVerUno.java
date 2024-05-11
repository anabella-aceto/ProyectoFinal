package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Sofa;
import restsofa.restcontroller.SofaRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "uno" en SofaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestVerUno {

	@Autowired
	private SofaRestController sofaRestController;

    /**
     * Prueba del método "uno".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el sofa del cuerpo de la respuesta.
     * Verifica que el sofa no sea nulo.
     * Verifica que el sofa tenga el idSofa correcto.
     * Verifica si el nombre del sofa es correcto.
     *
     * @param sofaId El identificador del sofa a buscar.
     * @return ResponseEntity con el resultado de la búsqueda.
     */
	@Test
	public void testUno() {
		int sofaId = 3;
		ResponseEntity<?> responseEntity = sofaRestController.uno(sofaId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el sofa del cuerpo de la respuesta
		Sofa sofa = (Sofa) responseEntity.getBody();

		// Verifica que el sofa no sea nulo
		assertNotNull(sofa, "El sofa no debería ser nulo");

		// Verifica que el sofa tenga el idSofa correcto
		assertEquals(sofaId, sofa.getIdSofa(), "El idSofa no coincide");

		// Verifica si el nombre del cliente es correcto
		assertEquals("Luna", sofa.getNombre(), "No se encuentra el sofa");
	}

}
