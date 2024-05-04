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
 * Clase de prueba JUnit para el método "alta" en SofaRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaRestController` para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaRestControllerTestAlta {
	
	@Autowired
	private SofaRestController sofaRestController;
	
    /**
     * Prueba del método "alta".
     *
     * @Test
     * Anota este método como una prueba JUnit.
     *
     * Verifica que el código de estado de la respuesta sea OK.
     * Obtiene el sofa guardado del cuerpo de la respuesta.
     * Verifica que el sofa guardado no sea nulo.
     * Verifica que el nombre del sofa guardado coincida con el nombre esperado.
     *
     * @param nuevoSofa El sofa a dar de alta.
     * @return ResponseEntity con el resultado de la operación de alta.
     */
    @Test
    public void testAlta() {
        // Crea un sofa de ejemplo
        Sofa nuevoSofa = new Sofa();
        nuevoSofa.setNombre("Alhambra"); // Establece el nombre del sofá
        nuevoSofa.setDescripcion("Sofa de diseño vanguardista"); // Establece la descripción del sofá
        nuevoSofa.setPatas(4); // Establece el número de patas del sofá
        nuevoSofa.setMedidaCojin("52.00"); // Establece las medidas del coj´n
        nuevoSofa.setPrecio(750); // Establece el precio del sofá
        
        // Llama al método "alta"
        ResponseEntity<?> responseEntity = sofaRestController.alta(nuevoSofa);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el sofa guardado del cuerpo de la respuesta
        Sofa sofaGuardado = (Sofa) responseEntity.getBody();

        // Verifica que el sofa guardado no sea nulo
        assertNotNull(sofaGuardado, "El sofa guardado no debería ser nulo");

        // Verifica que el nombre del sofa guardado coincida con el nombre esperado
        assertEquals("Alhambra", sofaGuardado.getNombre(), "El nombre del cliente guardado no coincide");
    }

}
