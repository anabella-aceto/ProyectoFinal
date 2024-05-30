package restsofa.modelo.DTO;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Clase que representa un DTO (Data Transfer Object) para un pedido.
 */

@Component // Indica que esta clase es un componente gestionado por Spring.
@NoArgsConstructor // Genera un constructor sin argumentos usando Lombok.
@AllArgsConstructor // Genera un constructor con todos los argumentos usando Lombok.
@Data // Genera getters, setters, toString, equals y hashCode automáticamente usando Lombok.

public class PedidoDto {

    /**
     * El identificador del pedido.
     */
    private int idPedido;

    /**
     * El identificador del cliente asociado al pedido.
     */
    private int idCliente;

    /**
     * La fecha del pedido.
     */
    private Date fecha;

    /**
     * El identificador del vendedor asociado al pedido.
     */
    private int vendedor;
    
    /*
     * Nombre del cliente 
     */
    private String nombreCliente;
    /*
     * Nombre del vendedor 
     */
    private String nombreVendedor;
}
