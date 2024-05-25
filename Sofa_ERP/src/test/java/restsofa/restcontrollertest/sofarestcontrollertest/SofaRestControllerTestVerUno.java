package restsofa.restcontrollertest.sofarestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Sofa;
import restsofa.restcontroller.SofaRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "uno" en SofaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestVerUno {

	@Autowired
	private SofaRestController sofaRestController;

	/**
	 * Prueba del método "uno" cuando el sofá existe.
	 *
	 * Anota este método como una prueba JUnit. Verifica que el código de estado de
	 * la respuesta sea OK. Obtiene el sofá del cuerpo de la respuesta. Verifica que
	 * el sofá no sea nulo. Verifica que el sofá tenga el idSofa correcto. Verifica
	 * que el nombre del sofá sea correcto.
	 */
	@Test
	public void testUnoSofaExiste() {
		int sofaId = 3;
		ResponseEntity<?> responseEntity = sofaRestController.uno(sofaId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el sofá del cuerpo de la respuesta
		Sofa sofa = (Sofa) responseEntity.getBody();

		// Verifica que el sofá no sea nulo
		assertNotNull(sofa, "El sofá no debería ser nulo");

		// Verifica que el sofá tenga el idSofa correcto
		assertEquals(sofaId, sofa.getIdSofa(), "El idSofa no coincide");

		// Verifica si el nombre del sofá es correcto
		assertEquals("Luna", sofa.getNombre(), "No se encuentra el sofá");
	}

    /**
     * Prueba del método "uno" cuando el sofá no existe.
     *
     * Anota este método como una prueba JUnit.
     * Verifica que el código de estado de la respuesta sea NOT FOUND.
     * Verifica que el cuerpo de la respuesta sea nulo.
     */
    @Test
    public void testUnoSofaNoExiste() {
        int sofaId = 999; // Usar un ID que no exista en la base de datos
        ResponseEntity<?> responseEntity = sofaRestController.uno(sofaId);

        // Verifica que el código de estado de la respuesta sea NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verifica que el cuerpo de la respuesta sea nulo
        assertEquals("No se encuentra el sofá", responseEntity.getBody(), "El mensaje de error no coincide");
    }
}
