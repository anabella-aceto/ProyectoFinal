package restsofa.restcontrollertest.materialrestcontrollertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.Pedido;
import restsofa.modelo.entities.Sofa;
import restsofa.restcontroller.DetallePedidoRestController;
import restsofa.restcontroller.MaterialRestController;
import restsofa.restcontroller.SofaRestController;

@SpringBootTest
public class MaterialRestControllerTestRestaurar {
	
	@Autowired
	MaterialRestController materialRestController;
	
	@Autowired
	SofaRestController SofaRestController;
	
	@Autowired
	DetallePedidoRestController detallePedidoRestController;
	
	   @Test
	    public void testRestaurarMateriales() {
	        // Crea un pedido ficticio que por defecto tiene estado 1 (Pendiente)
	        Pedido pedido = new Pedido();
	        DetallePedido detallePedido= new DetallePedido();
	        Sofa sofa = new Sofa();

	        // Ejecuta el método del controlador para restaurar los materiales del pedido
	        ResponseEntity<?> response = materialRestController.restaurarMateriales(12, 2);

	        // Verifica que la respuesta sea 200 y el cuerpo sea "Pedido restaurado"
	        assert HttpStatus.OK.equals(response.getStatusCode());
	        assert "Pedido restaurado".equals(response.getBody());
	    }

}
