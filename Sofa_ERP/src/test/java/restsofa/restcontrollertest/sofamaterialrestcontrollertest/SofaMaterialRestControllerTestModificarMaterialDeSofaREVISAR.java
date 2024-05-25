package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.SofaMaterialDto;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.restcontroller.SofaMaterialRestController;
import restsofa.service.MaterialService;
import restsofa.service.SofaMaterialService;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0 * 
 *          Clase de prueba JUnit para el método "modificarSofaMaterial" en
 *          SofaMaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `SofaMaterialRestController`, 'SofaMaterialService' y 'MaterialService' para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestModificarMaterialDeSofaREVISAR {

	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;

	@Autowired
	private SofaMaterialService sofaMaterialService;

	@Autowired
	private MaterialService materialService;

    /**
     * Prueba el escenario donde la modificación del material de sofá es exitosa.
     *
     * @param sofaMaterialDto Objeto de tipo {@link SofaMaterialDto} que contiene los datos de la modificación.
     * @return ResponseEntity con el resultado de la modificación.
     * @throws AssertionError Si la modificación no tiene éxito o el material no existe para el sofá proporcionado.
     */
	@Test
	public void testModificarSofaMaterial_Exito() {
		// Creamos un objeto de tipo SofaMaterialDto con datos de ejemplo
		SofaMaterialDto sofaMaterialDto = new SofaMaterialDto();
		sofaMaterialDto.setIdSofa(8);
		sofaMaterialDto.setIdMaterial(1);
		sofaMaterialDto.setCantidadUtilizada(55); // Nueva cantidad utilizada

		// Supongamos que la búsqueda devuelve un objeto existente
		SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(sofaMaterialDto.getIdSofa(),
				sofaMaterialDto.getIdMaterial());
		assertNotNull(sofaMaterial);

		// Modificamos el objeto sofaMaterial con los nuevos datos
		sofaMaterial.setMaterial(materialService.buscarUno(sofaMaterialDto.getIdMaterial()));
		sofaMaterial.setCantidadUtilizada(sofaMaterialDto.getCantidadUtilizada());
		sofaMaterialService.updateOne(sofaMaterial);

		ResponseEntity<?> response = sofaMaterialRestController.modificarSofaMaterial(sofaMaterialDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Modificación realizada correctamente" + sofaMaterial, response.getBody());
	}

    /**
     * Prueba el escenario donde la modificación del material de sofá falla porque el material no existe.
     *
     * @param sofaMaterialDto Objeto de tipo {@link SofaMaterialDto} que contiene los datos de la modificación.
     * @return ResponseEntity con el resultado de la modificación.
     * @throws AssertionError Si la modificación tiene éxito o el material no existe para el sofá proporcionado.
     */
	@Test
	public void testModificarSofaMaterial_Fallo() {
		// Creamos un objeto de tipo SofaMaterialDto con datos de ejemplo
		SofaMaterialDto sofaMaterialDto = new SofaMaterialDto();
		sofaMaterialDto.setIdSofa(12);
		sofaMaterialDto.setIdMaterial(1);
		sofaMaterialDto.setCantidadUtilizada(5); // Nueva cantidad utilizada

		// Supongamos que la búsqueda no encuentra ningún objeto
		SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(sofaMaterialDto.getIdSofa(),
				sofaMaterialDto.getIdMaterial());
		assertEquals(null, sofaMaterial);

		ResponseEntity<?> response = sofaMaterialRestController.modificarSofaMaterial(sofaMaterialDto);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("No se ha podido modificar el material", response.getBody());
	}
}
