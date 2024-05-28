package restsofa.restcontrollertest.tarearestcontrollertest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restsofa.restcontroller.TareaRestController;

/**
 * @author Alberto Saboya, Anabella Aceto, David Rodríguez
 * 
 * @version 1.0 * 
 *          Clase de prueba JUnit para el método "cambiarEstado" en
 *          TareaRestController.
 *
 * @SpringBootTest Indica que esta clase es una prueba de Spring Boot.
 *
 * @Autowired Inyecta la instancia de `TareaRestController` para realizar las
 *            pruebas.
 * 
 */
@SpringBootTest
public class TareaRestControllerTestEstadoTarea {
	
	@Autowired
	TareaRestController tareaRestController;
	
	/**
     * Prueba para cambiar el estado de un pedido a "procesando".
     *
     * @param idPedido      ID del pedido válido.
     * @param idEmpleado    ID del empleado válido.
     * @param idDepartamento ID del departamento válido.
     * @return ResponseEntity con el resultado de la operación.
     * @throws Exception si ocurre algún error durante la ejecución de la prueba.
     */
	@Test
    public void testCambiarEstadoProcesando() throws Exception {
        int idTarea = 3; // Cambia esto al ID de un pedido válido
        int idEmpleado = 1; // Cambia esto al ID de un empleado válido
        int idDepartamento = 2; // Cambia esto al ID de un departamento válido
        int idDeped = 7; // Cambia esto al ID de un detalle de pedido válido

        ResponseEntity<?> response = tareaRestController.cambiarEstado(idTarea, idEmpleado, idDepartamento, idDeped);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Se ha actualizado el pedido a 'procesando'");
    }

	/**
     * Prueba para cambiar el estado de un pedido a "finalizado".
     *
     * @param idPedido2      ID del pedido válido.
     * @param idEmpleado2    ID del empleado válido.
     * @param idDepartamento2 ID del departamento válido.
     * @return ResponseEntity con el resultado de la operación.
     * @throws Exception si ocurre algún error durante la ejecución de la prueba.
     */
    @Test
    public void testCambiarEstadoFinalizado() throws Exception {
        int idTarea2 = 3; // Cambia esto al ID de un pedido válido
        int idEmpleado2 = 2; // Cambia esto al ID de un empleado válido
        int idDepartamento2 = 2; // Cambia esto al ID de un departamento válido
        int idDeped2 = 7; // Cambia esto al ID de un detalle de pedido válido

        ResponseEntity<?> response = tareaRestController.cambiarEstado(idTarea2, idEmpleado2, idDepartamento2, idDeped2 );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Se ha actualizado el pedido a 'finalizado'");
    }

    /**
     * Prueba para verificar que se maneje correctamente el error al cargar una tarea.
     *
     * @param idPedido3      ID del pedido inválido.
     * @param idEmpleado3    ID del empleado inválido.
     * @param idDepartamento3 ID del departamento inválido.
     * @return ResponseEntity con el resultado de la operación.
     * @throws Exception si ocurre algún error durante la ejecución de la prueba.
     */
    @Test
    public void testErrorAlCargarTarea() throws Exception {
        // Similar al caso anterior, pero con valores que generen un error
        int idTarea3 = 50; // Cambia esto al ID de un pedido inválido
        int idEmpleado3 = 252; // Cambia esto al ID de un empleado inválido
        int idDepartamento3 = 245; // Cambia esto al ID de un departamento inválido
        int idDeped3 = 150; // Cambia esto al ID de un detalle de pedido válido

        ResponseEntity<?> response = tareaRestController.cambiarEstado(idTarea3, idEmpleado3, idDepartamento3, idDeped3);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Error al cargar tarea");
    }

}
