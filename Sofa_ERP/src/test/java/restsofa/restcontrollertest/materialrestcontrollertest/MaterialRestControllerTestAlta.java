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
import restsofa.service.ProveedorService;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "altaMaterial" en
 *          MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class MaterialRestControllerTestAlta {

	@Autowired
	MaterialRestController materialRestController;

	@Autowired
	ProveedorService proveedorService;

	/**
	 * Prueba unitaria del método "altaMaterial".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Crea un material de ejemplo y lo pasa al método "altaMaterial".
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       material guardado del cuerpo de la respuesta. Verifica que el material
	 *       guardado no sea nulo y que su nombre coincida con el esperado.
	 */
	@Test
	public void testAlta() {
		// Crea un material de ejemplo
		MaterialDto nuevoMaterialDto = new MaterialDto();
		nuevoMaterialDto.setNombre("Grapa"); // Establece el nombre del material
		nuevoMaterialDto.setDescripcion("Grapas metálicas de acero"); // Establece una descripción para el material
		nuevoMaterialDto.setCantidad(50); // Establece la cantidad de ese material
		nuevoMaterialDto.setRefMaterialProveedor(3); // Establece la referencia del material del proveedor
		nuevoMaterialDto.setCategoria("textil"); // Establece la categoria del material
		nuevoMaterialDto.setUnidadMedida("unidad"); // Establece la unidad del material
		nuevoMaterialDto.setIdProveedor(1); // Establece el ID del proveedor del material

		// Llama al método "altaMaterial"
		ResponseEntity<?> responseEntity = materialRestController.altaMaterial(nuevoMaterialDto);

		// Verifica que el código de estado de la respuesta sea OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el material guardado del cuerpo de la respuesta
		Material materialGuardado = (Material) responseEntity.getBody();

		// Verifica que el material guardado no sea nulo
		assertNotNull(materialGuardado, "El material guardado no debería ser nulo");

		// Verifica que el nombre del material guardado coincida con el nombre esperado
		assertEquals("Grapa", materialGuardado.getNombre(), "Error al ingresar el material");
	}
}
