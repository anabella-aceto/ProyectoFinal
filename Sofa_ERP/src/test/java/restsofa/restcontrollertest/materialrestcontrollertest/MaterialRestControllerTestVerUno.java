package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.Cliente;
import restsofa.modelo.entities.Material;
import restsofa.restcontroller.MaterialRestController;

@SpringBootTest
public class MaterialRestControllerTestVerUno {

	@Autowired
	MaterialRestController materialRestController;

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

}
