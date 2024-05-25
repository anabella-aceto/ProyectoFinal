package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class MaterialRestControllerTestRestaurar_REVISAR {
	
	@Autowired
	MaterialRestController materialRestController;
	
	@Autowired
	SofaRestController SofaRestController;
	
	@Autowired
	DetallePedidoRestController detallePedidoRestController;
	
    /**
     * Prueba para verificar la restauración de materiales cuando el pedido y el sofá existen.
     *
     * @test Se crea un pedido ficticio que por defecto tiene estado 1 (Pendiente).
     * @test Se ejecuta el método del controlador para restaurar los materiales del pedido.
     * @param idPedido El ID del pedido a restaurar.
     * @param idSofa   El ID del sofá a restaurar.
     * @param idDeped el ID del detalle de pedido     
     * @return ResponseEntity con el estado de la operación y un mensaje.
     */
    @Test
    public void testRestaurarMateriales_PedidoYSofaExistentes() {
        // Crea un pedido ficticio que por defecto tiene estado 1 (Pendiente)
        Pedido pedido = new Pedido();
        DetallePedido detallePedido = new DetallePedido();
        Sofa sofa = new Sofa();

        // Ejecuta el método del controlador para restaurar los materiales del pedido
        ResponseEntity<?> response = materialRestController.restaurarMateriales(2, 3, 2);

        // Verifica que la respuesta sea 200 y el cuerpo sea "Pedido restaurado"
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pedido restaurado", response.getBody());
    }

    /**
     * Prueba para verificar la restauración de materiales cuando solo existe el pedido.
     *
     * @test Se crea un pedido ficticio que por defecto tiene estado 1 (Pendiente).
     * @test Se ejecuta el método del controlador para restaurar los materiales del pedido.
     * @param idPedido El ID del pedido a restaurar.
     * @param idSofa   El ID del sofá a restaurar.
     * @param idDeped el ID del detalle de pedido     
     * @return ResponseEntity con el estado de la operación y un mensaje.
     */
    @Test
    public void testRestaurarMateriales_SoloPedidoExistente() {
        // Crea un pedido ficticio que por defecto tiene estado 1 (Pendiente)
        Pedido pedido = new Pedido();

        // Ejecuta el método del controlador para restaurar los materiales del pedido
        ResponseEntity<?> response = materialRestController.restaurarMateriales(12, -1, 1);

        // Verifica que la respuesta sea 404 (Not Found) ya que no existe el sofá
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Prueba para verificar la restauración de materiales cuando no existe ningún pedido.
     *
     * @test Se ejecuta el método del controlador para restaurar los materiales del pedido.
     * @param idPedido El ID del pedido a restaurar.
     * @param idSofa   El ID del sofá a restaurar.
     * @param idDeped el ID del detalle de pedido     
     * @return ResponseEntity con el estado de la operación y un mensaje.
     */
    @Test
    public void testRestaurarMateriales_NingunPedidoExistente() {
        // Ejecuta el método del controlador para restaurar los materiales del pedido
        ResponseEntity<?> response = materialRestController.restaurarMateriales(-1, 2, 1);

        // Verifica que la respuesta sea 404 (Not Found) ya que no existe el pedido
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
