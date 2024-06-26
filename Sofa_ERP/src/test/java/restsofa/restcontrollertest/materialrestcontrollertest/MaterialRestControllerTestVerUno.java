package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "buscarUno" en MaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `MaterialRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class MaterialRestControllerTestVerUno {

	@Autowired
	MaterialRestController materialRestController;

    /**
     * Prueba del método "buscarUno".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el material del cuerpo de la respuesta.
     * Verifica que el material no sea nulo.
     * Verifica que el material tenga el idMaterial correcto.
     * Verifica si el nombre del material es correcto.
     *
     * @param materialId El identificador del material a buscar.
     * @return ResponseEntity con el resultado de la búsqueda.
     */
	@Test
	public void testUno() {
		int materialId = 3;
		ResponseEntity<?> responseEntity = materialRestController.buscarUno(materialId);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el material del cuerpo de la respuesta
		Material material = (Material) responseEntity.getBody();

		// Verifica que el material no sea nulo
		assertNotNull(material, "El material no debería ser nulo");

		// Verifica que el material tenga el idMaterial correcto
		assertEquals(materialId, material.getIdMaterial(), "El identificador del material no existe");

		// Verifica si el nombre del material es correcto
		assertEquals("Hilo", material.getNombre(), "El nombre del material no coincide");
	}
	
    /**
     * Caso de prueba para el método "buscarUno" cuando el material no se encuentra.
     * Llama al método "buscarUno" con un ID de material que no existe y verifica que se devuelva un código de estado 404 Not Found.
     */
    @Test
    public void testBuscarUnoMaterialNoEncontrado() {
        // ID de material que no existe
        int idMaterialNoExistente = -1;

        // Llama al método "buscarUno" del controlador
        ResponseEntity<?> responseEntity = materialRestController.buscarUno(idMaterialNoExistente);

        // Verifica que el código de estado de la respuesta sea Not Found (404)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}