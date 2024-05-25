package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.SofaMaterialDto;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.restcontroller.SofaMaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "altaSofaMaterial" en SofaMaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaMaterialRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestAlta {
	
	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;
	
    /**
     * Prueba del método "altaSofaMaterial".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Verifica que el material del sofá guardado no sea nulo.
     * Verifica si el cuerpo de la respuesta es un objeto SofaMaterial.
     *
     * @param nuevoMaterial El material a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crea un material de sofá de ejemplo
        SofaMaterialDto nuevoMaterial = new SofaMaterialDto();
        nuevoMaterial.setIdSofa(1);; // Establece el idSofa
        nuevoMaterial.setIdMaterial(1); // Establece el idMaterial
        nuevoMaterial.setCantidadUtilizada(8); // Establece la cantidad de material necesaria para fabricar el sofá

        // Llama al método "altaSofaMaterial"
        ResponseEntity<?> responseEntity = sofaMaterialRestController.altaSofaMaterial(nuevoMaterial);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifica que el cuerpo de la respuesta no sea nulo
        assertNotNull(responseEntity.getBody(), "El cuerpo de la respuesta no debería ser nulo");

        // Verifica que el cuerpo de la respuesta sea un objeto SofaMaterial
        assertTrue(responseEntity.getBody() instanceof SofaMaterial, "El objeto devuelto no es un SofaMaterial");
    }

}
