package restsofa.restcontrollertest.clienterestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.ClienteRestController;

@SpringBootTest
public class ClienteRestControllerTestBorrar {
	
    @Autowired
    private ClienteRestController clienteRestController;
	
    @Test
    public void testBorrar() {
        int clientId = 11; // Reemplaza con un idCliente existente en tus datos
        ResponseEntity<?> responseEntity = clienteRestController.borrar(clientId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Eliminación realizada correctamente"), "La eliminación debería ser correcta");
    }
 
}
