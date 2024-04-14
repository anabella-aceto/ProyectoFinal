package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.MaterialDto;
import restsofa.restcontroller.MaterialRestController;

@SpringBootTest
public class MaterialRestControllerTestModificar {
	
	@Autowired
	MaterialRestController materialRestController;
	
    @Test
    public void testModificar() {
        // Crea un material de ejemplo
        MaterialDto materialExistente = new MaterialDto();
        materialExistente.setIdMaterial(14);; // Establece un idMaterial existente
        materialExistente.setNombre("Cola"); // Establece el nombre del material

        // Llama al método "modificar"
        ResponseEntity<?> responseEntity = materialRestController.modificarMaterial(materialExistente);

        // Verifica que el código de estado de la respuesta sea OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtiene el mensaje de la respuesta
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la modificación fue exitosa
        assertTrue(mensaje.contains("Material modificado exitosamente"), "La modificación debería ser correcta");
    }

}
