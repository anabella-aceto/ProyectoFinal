package restsofa.restcontrollertest.materialrestcontrollertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.MaterialRestController;

/**
 * @author Alberto Saboya
 * @version 1.0
 * 
 * Clase de prueba JUnit para el método "restaurarMateriales" en
 * MaterialRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `MaterialRestController` para realizar las
 *            pruebas.
 *           
 */
@SpringBootTest
public class MaterialRestControllerTestRestaurarMaterialesPedidoREVISAR {

    @Autowired
    MaterialRestController materialRestController;

    /**
     * Prueba el caso en el que se intenta restaurar materiales para un pedido existente y en el estado correcto.
     *
     * @param idPedido el ID del pedido existente
     * @param idSofa   el ID del sofá para restaurar los materiales
     * @param idDeped el ID del detalle de pedido
     * @return ResponseEntity con el estado de la operación y un mensaje
     */
    @Test
    void restaurarMateriales_PedidoExistenteYEstadoCorrecto_Devuelve200() {
        // Probamos un pedido existente y estado correcto (pendiente)
        int idPedido = 1;
        int idSofa = 1;
        int idDeped = 1;

        // Llama al método "restaurarMateriales"
        ResponseEntity<?> response = materialRestController.restaurarMateriales(idPedido, idSofa, idDeped);

        // Comprueba que devuelve 200 y el cuerpo del mensaje es el adecuado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pedido restaurado", response.getBody());
    }

    /**
     * Prueba el caso en el que se intenta restaurar materiales para un pedido que no existe.
     *
     * @param idPedido el ID del pedido que no existe
     * @param idSofa   el ID del sofá para restaurar los materiales
     * @param idDeped el ID del detalle de pedido     
     * @return ResponseEntity con el estado de la operación y un mensaje
     */
    @Test
    void restaurarMateriales_PedidoNoExiste_Devuelve404() {
        // Probamos un pedido que no existe y un idSofa que exista
        int idPedido = -1;
        int idSofa = 1;
        int idDeped = 1;

        // Llama al método "restaurarMateriales"
        ResponseEntity<?> response = materialRestController.restaurarMateriales(idPedido, idSofa, idDeped);

        // Comprueba que devuelve 404 y el cuerpo del mensaje es el adecuado
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("El pedido no existe o no está en el estado adecuado para restaurar los materiales", response.getBody());
    }

    /**
     * Prueba el caso en el que se intenta restaurar materiales para un pedido con estado incorrecto.
     *
     * @param idPedido el ID del pedido con estado incorrecto
     * @param idSofa   el ID del sofá para restaurar los materiales
     * @param idDeped el ID del detalle de pedido
     * @return ResponseEntity con el estado de la operación y un mensaje
     */
    @Test
    void restaurarMateriales_EstadoIncorrecto_Devuelve404() {
        // Probamos un pedido que exista, un idSofa que exista pero con un estado incorrecto (cancelado)
        int idPedido = 4;
        int idSofa = 3;
        int idDeped = 1;

        // Llama al método "restaurarMateriales"
        ResponseEntity<?> response = materialRestController.restaurarMateriales(idPedido, idSofa, idDeped);

        // Comprueba que devuelve 404 y el cuerpo del mensaje es el adecuado
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("El pedido no existe o no está en el estado adecuado para restaurar los materiales", response.getBody());
    }

}
