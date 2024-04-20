package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.MaterialDto;
import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

/**
 * Clase de prueba JUnit para el método "altaMaterial" en
 * MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas.
 *            
 * @author Alberto Saboya
 * @version 1.0
 */
@SpringBootTest
public class MaterialRestControllerTestAlta {

	@Autowired
	MaterialRestController materialRestController;

	/**
	 * Prueba del método "altaMaterial".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Crea un material de ejemplo y lo pasa al método "altaMaterial".
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       material guardado del cuerpo de la respuesta. Verifica que el material
	 *       guardado no sea nulo y que su nombre coincida con el esperado.
	 *
	 * @return ResponseEntity con el material guardado.
	 */
	@Test
	public void testAlta() {
		// Crea un material de ejemplo
		MaterialDto nuevoMaterial = new MaterialDto();
		nuevoMaterial.setNombre("Grapa"); // Establece el nombre del material

		// Llama al método "alta"
		ResponseEntity<?> responseEntity = materialRestController.altaMaterial(nuevoMaterial);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el material guardado del cuerpo de la respuesta
		Material materialGuardado = (Material) responseEntity.getBody();

		// Verifica que el material guardado no sea nulo
		assertNotNull(materialGuardado, "El material guardado no debería ser nulo");

		// Verifica que el nombre del material guardado coincida con el nombre esperado
		assertEquals("Grapa", materialGuardado.getNombre(), "Error al ingresar el material");
	}
}
