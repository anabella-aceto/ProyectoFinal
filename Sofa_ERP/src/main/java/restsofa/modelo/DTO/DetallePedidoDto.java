package restsofa.modelo.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un DTO (Data Transfer Object) para el detalle de un pedido.
 */

@Component // Indica que esta clase es un componente gestionado por Spring.
@NoArgsConstructor // Genera un constructor sin argumentos usando Lombok.
@AllArgsConstructor // Genera un constructor con todos los argumentos usando Lombok.
@Data // Genera getters, setters, toString, equals y hashCode automáticamente usando Lombok.

public class DetallePedidoDto {

    /**
     * El identificador del detalle de pedido.
     */
    private int idDePed;

    /**
     * El identificador del pedido al que pertenece este detalle.
     */
    private int idPedido;

    /**
     * El identificador del sofá asociado a este detalle.
     */
    private int idSofa;

    /**
     * La cantidad de sofás en este detalle.
     */
    private int cantidad;

    /**
     * El número de plazas del sofá.
     */
    private int plazas;

    /**
     * La densidad del cojín del sofá.
     */
    private int densCojin;

    /**
     * La fecha del detalle del pedido.
     */
    private Date fecha;

    /**
     * El precio del detalle del pedido.
     */
    private double precio;

    /**
     * El identificador del estado del detalle del pedido.
     */
    private int idEstado;
}