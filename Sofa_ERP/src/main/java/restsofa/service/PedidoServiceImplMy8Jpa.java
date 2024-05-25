package restsofa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Pedido;
import restsofa.repository.PedidoRepository;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Implementación de la interfaz PedidoService.
 */
@Service
public class PedidoServiceImplMy8Jpa implements PedidoService {

    @Autowired
    private PedidoRepository pedrepo;

    @Autowired
    private EstadoService estadoService;

    /**
     * Método que busca un pedido por su identificador.
     *
     * @param idPedido El identificador único del pedido a buscar.
     * @return El pedido encontrado, o null si no se encuentra.
     */
    @Override
    public Pedido buscarPedido(int idPedido) {
        return pedrepo.findById(idPedido).orElse(null);
    }

    /**
     * Método que lista todos los pedidos.
     *
     * @return Una lista de todos los pedidos.
     */
    @Override
    public List<Pedido> buscarTodosPedidos() {
        return pedrepo.findAll();
    }

    /**
     * Método que inserta un nuevo pedido.
     *
     * @param pedido El pedido a insertar.
     * @return El pedido insertado.
     */
    @Override
    public Pedido altaPedido(Pedido pedido) {
        return pedrepo.save(pedido);
    }

    /**
     * Método que modifica un pedido existente.
     *
     * @param pedido El pedido a modificar.
     * @return El pedido modificado, o null si hay un error.
     */
    @Override
    public Pedido modifPedido(Pedido pedido) {
        try {
            return pedrepo.save(pedido);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Elimina un pedido por su identificador.
     *
     * @param idPedido El identificador único del pedido a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    @Override
    public boolean borrarPedido(int idPedido) {
        try {
            if (buscarPedido(idPedido) != null) {
                pedrepo.deleteById(idPedido);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Método que cancela un pedido por su identificador.
     *
     * @param idPedido El identificador único del pedido a cancelar.
     * @return true si se canceló correctamente, false de lo contrario.
     */
    @Override
    public boolean cancelarPedido(int idPedido) {
        Pedido pedido = buscarPedido(idPedido);
        if (pedido != null) {
            pedrepo.save(pedido);
            return true;
        }
        return false;
    }

    /**
     * Método que filtra los pedidos por rango de fechas.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin    La fecha de fin del rango.
     * @return Una lista de pedidos dentro del rango de fechas especificado.
     */
    @Override
    public List<Pedido> filtrarPorFecha(Date fechaInicio, Date fechaFin) {
        return pedrepo.buscarPorFecha(fechaInicio, fechaFin);
    }
}
