package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.MaterialDto;
import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 *          Clase de prueba JUnit para el método "modificarMaterial" en
 *          MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class MaterialRestControllerTestModificar {

	@Autowired
	MaterialRestController materialRestController;

	/**
	 * Prueba del método "modificarMaterial".
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea OK. Obtiene el
	 *       mensaje de la respuesta. Verifica que la modificación fue exitosa.
	 *
	 * @param materialExistente El cliente con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */

	@Test
	public void testModificarMaterialExistente() {
		// Crea un material de ejemplo
		MaterialDto materialExistente = new MaterialDto();
		materialExistente.setIdMaterial(13);
		; // Establece un idMaterial existente
		materialExistente.setNombre("Cola"); // Establece el nombre del material
		materialExistente.setDescripcion("Cola blanca para madera"); // Establece una descripción para el material
		materialExistente.setCantidad(50); // Establece la cantidad de ese material
		materialExistente.setRefMaterialProveedor(4); // Establece la referencia del material del proveedor
		materialExistente.setCategoria("ferreteria"); // Establece la categoria del material
		materialExistente.setUnidadMedida("unidad"); // Establece la unidad del material
		materialExistente.setIdProveedor(1); // Establece el proveedor del material

		// Llama al método "modificar"
		ResponseEntity<?> responseEntity = materialRestController.modificarMaterial(materialExistente);

		// Verifica que el código de estado de la respuesta sea OK
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que la modificación fue exitosa
		assertTrue(mensaje.contains("Material modificado exitosamente"), "La modificación debería ser correcta");
	}

	/**
	 * Prueba del método "modificarMaterial" cuando el material no existe.
	 *
	 * @Test Anota este método como una prueba JUnit.
	 *
	 *       Verifica que el código de estado de la respuesta sea BAD_REQUEST.
	 *       Obtiene el mensaje de la respuesta. Verifica que el mensaje indique que
	 *       el material no existe.
	 *
	 * @param materialNoExistente El material con los datos a modificar.
	 * @return ResponseEntity con el resultado de la operación de modificación.
	 */

	@Test
	public void testModificarMaterialNoExistente() {
		// Crea un material de ejemplo que no existe
		MaterialDto materialNoExistente = new MaterialDto();
		materialNoExistente.setIdMaterial(999); // Establece un idMaterial que no existe
		materialNoExistente.setNombre("Martillo"); // Establece el nombre del material
		materialNoExistente.setDescripcion("Martillo de carpintero"); // Establece una descripción para el material
		materialNoExistente.setCantidad(20); // Establece la cantidad de ese material
		materialNoExistente.setRefMaterialProveedor(5); // Establece la referencia del material del proveedor
		materialNoExistente.setCategoria("herramientas"); // Establece la categoria del material
		materialNoExistente.setUnidadMedida("unidad"); // Establece la unidad del material
		materialNoExistente.setIdProveedor(2); // Establece el proveedor del material

		// Llama al método "modificarMaterial"
		ResponseEntity<?> responseEntity = materialRestController.modificarMaterial(materialNoExistente);

		// Verifica que el código de estado de la respuesta sea NOT_FOUND
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		// Obtiene el mensaje de la respuesta
		String mensaje = (String) responseEntity.getBody();

		// Verifica que el mensaje no sea nulo
		assertNotNull(mensaje, "El mensaje de respuesta no debería ser nulo");

		// Verifica que el mensaje indique que el material no existe
		assertTrue(mensaje.contains("No se encuentra el material"),
				"El mensaje debería indicar que el material no existe");
	}

}
