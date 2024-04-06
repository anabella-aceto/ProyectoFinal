package restsofa.restcontrollertest.empleadorestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.EmpleadoRestController;

@SpringBootTest
public class EmpleadoRestControllerTesBorrar {
	
	@Autowired
	private EmpleadoRestController empleadoRestController;
	
    @Test
    public void testBorrar() {
        int empId = 9; // Reemplaza con un idEmpleado
        ResponseEntity<?> responseEntity = empleadoRestController.borrarUno(empId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        String mensaje = (String) responseEntity.getBody();

        // Verifica que la eliminación fue correcta
        assertTrue(mensaje.contains("Empleado eliminado correctamente"), "La eliminación debería ser correcta");
    }

}
