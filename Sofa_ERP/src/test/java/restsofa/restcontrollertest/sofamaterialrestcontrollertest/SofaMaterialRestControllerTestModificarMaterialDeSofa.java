package restsofa.restcontrollertest.sofamaterialrestcontrollertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.DTO.SofaMaterialDto;
import restsofa.restcontroller.SofaMaterialRestController;

@SpringBootTest
public class SofaMaterialRestControllerTestModificarMaterialDeSofa {
	
	@Autowired
	private SofaMaterialRestController sofaMaterialRestController;
	
    @Test
    public void testModificar() {
        // Crea un objeto SofaMaterialDto con datos de prueba
        SofaMaterialDto sofaMaterialDto = new SofaMaterialDto();
        sofaMaterialDto.setIdSofaMateriales(41);
        sofaMaterialDto.setIdSofa(3);
        sofaMaterialDto.setIdMaterial(5);
        sofaMaterialDto.setCantidadUtilizada(10);

        // Ejecuta el método a probar
        ResponseEntity<?> response = sofaMaterialRestController.modificarSofaMaterial(sofaMaterialDto);

        // Verifica que la respuesta sea 200 OK y contenga el mensaje correcto
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals("Modificación realizada correctamente" + sofaMaterialDto);
    }
}
