package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.SofaMaterial;
import restsofa.restcontroller.SofaMaterialRestController;
import restsofa.service.SofaMaterialService;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "eliminarSofaMaterial" en SofaMaterialRestController.
 *
 * @SpringBootTest
 * Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired
 * Inyecta la instancia de `SofaMaterialRestController` y 'SofaMaterialService' para realizar las pruebas.
 * 
 */
@SpringBootTest
public class SofaMaterialRestControllerTestBorrarMaterialSofa {
	
	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;
	
    @Autowired
    private SofaMaterialService sofaMaterialService;

    /**
     * Prueba el escenario donde la eliminación del material de sofá es exitosa.
     *
     * @Test
     * Anota este método como una prueba JUnit.
     * 
     * @param idSofa    El ID del sofá del que se eliminará el material.
     * @param idMaterial El ID del material que se eliminará del sofá.
     * @return ResponseEntity con el resultado de la eliminación.
     * @throws AssertionError Si la eliminación no tiene éxito o el material no existe para el sofá proporcionado.
     */
    @Test
    public void testEliminarSofaMaterial_Exito() {
        int idSofa = 5;
        int idMaterial = 1;

        // Se verifica que el material de sofá exista antes de la eliminación
        SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(idSofa, idMaterial);
        assertNotNull(sofaMaterial);

        // Se intenta eliminar el material de sofá
        ResponseEntity<?> response = sofaMaterialRestController.eliminarSofaMaterial(idSofa, idMaterial);

        // Se espera que la eliminación sea exitosa
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eliminación exitosa", response.getBody());
    }

    /**
     * Prueba el escenario donde la eliminación del material de sofá falla porque el material no existe.
     *
     * @Test
     * Anota este método como una prueba JUnit.
     * 
     * @param idSofa    El ID del sofá del que se intentará eliminar el material.
     * @param idMaterial El ID del material que se intentará eliminar del sofá.
     * @return ResponseEntity con el resultado de la eliminación.
     * @throws AssertionError Si la eliminación tiene éxito o el material no existe para el sofá proporcionado.
     */
    @Test
    public void testEliminarSofaMaterial_Fallo() {
        int idSofa = 9; // Este idSofa no existe
        int idMaterial = 1;

        // Se verifica que el material de sofá no exista antes de la eliminación
        SofaMaterial sofaMaterial = sofaMaterialService.buscarPorSofaAndmaterial(idSofa, idMaterial);
        assertEquals(null, sofaMaterial);

        // Se intenta eliminar el material de sofá
        ResponseEntity<?> response = sofaMaterialRestController.eliminarSofaMaterial(idSofa, idMaterial);

        // Se espera que la eliminación falle y devuelva un código de estado de "Bad Request"
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("No se puede eliminar el material", response.getBody());
    }

}
