package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0
 * 
 *          Clase de prueba para MaterialRestController. Utiliza Spring Boot
 *          Test para cargar el contexto de la aplicación y realizar pruebas de
 *          integración.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot que
 *                 permite cargar el contexto completo de la aplicación.
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas de los métodos de búsqueda por nombre.
 * 
 */
@SpringBootTest
public class MaterialRestControllerTestVerPorRefProveedor {

	@Autowired
	private MaterialRestController materialRestController;

    /**
     * Prueba el método buscarPorRefProveedor con una referencia de proveedor existente.
     *
     * @Test Anota este método como una prueba JUnit.
     * @param refMaterialProveedor La referencia del proveedor del material a buscar.
     */
	@Test
	public void testbuscarPorRefProveedorEncontrado() {
		// Asumiendo que '456' es la referencia de un proveedor existente en la base de
		// datos
		int refMaterialProveedor = 456;

		// Ejecutar la acción a probar
		ResponseEntity<?> respuesta = materialRestController.buscarPorRefProveedor(refMaterialProveedor);

        // Verifica el estado de la respuesta:
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	}

    /**
     * Prueba el método buscarPorRefProveedor con una referencia de proveedor que no existe.
     *
     * @Test Anota este método como una prueba JUnit.
     * @param refMaterialProveedor La referencia del proveedor del material a buscar.
     */
	@Test
	public void testbuscarPorRefProveedorNoEncontrado() {
		// Usar una referencia que se sabe que no existe en la base de datos
		int refMaterialProveedorInexistente = -1;

		// Ejecutar la acción a probar
		ResponseEntity<?> respuesta = materialRestController.buscarPorRefProveedor(refMaterialProveedorInexistente);

		// Aserciones para verificar que la respuesta contiene un error cuando el
		// proveedor no existe
		assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
		assertNotNull(respuesta.getBody());
		assertEquals("No se encuentra el material", respuesta.getBody());
	}
}
