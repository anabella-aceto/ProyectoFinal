package restsofa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
     * 
     * @param fechaFin    La fecha de fin del rango.
     * 
     * @return Una lista de pedidos dentro del rango de fechas especificado.
     */
    @Override
    public List<Pedido> filtrarPorFecha(Date fechaInicio, Date fechaFin) {
        return pedrepo.buscarPorFecha(fechaInicio, fechaFin);
    }
    
    /**
     * Obtiene una lista de pedidos realizados en el día actual.
     *
     * @return una lista de pedidos realizados hoy.
     */
	@Override
	public List<Pedido> findPedidosDeHoy() {
		
		return pedrepo.findPedidosDeHoy();
	}

	/**
     * Obtiene una lista de pedidos realizados en el desde inicios de mes hasta el día actual.
     *
     * @return una lista de pedidos realizados en corriente mes.
     */
	@Override
	public int contarPedidosDesdeInicioMes() {
		 LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
	        List<Pedido> pedidos = pedrepo.findPedidosDesdeInicioMes(startOfMonth);
	        return pedidos.size();
	}

	/**
     * Cuenta la cantidad de pedidos realizados desde el inicio del trimestre actual.
     *
     * @return el número de pedidos realizados desde el inicio del trimestre.
     */
	@Override
	public long contarPedidosDesdeInicioTrimestre() {
		LocalDate now = LocalDate.now();
        LocalDate startOfQuarter = getStartOfQuarter(now);
        LocalDateTime startOfQuarterDateTime = LocalDateTime.of(startOfQuarter, LocalTime.MIN);
        
        List<Pedido> pedidos = pedrepo.findPedidosDesdeInicioTrimestre(startOfQuarterDateTime);
        return pedidos.size();
    }

	/**
     * Obtiene la fecha de inicio del trimestre actual.
     *
     * @param date la fecha actual
     * 
     * @return la fecha de inicio del trimestre actual
     */
    private LocalDate getStartOfQuarter(LocalDate date) {
        int currentMonth = date.getMonthValue();
        int startMonth = currentMonth - (currentMonth - 1) % 3;
        return LocalDate.of(date.getYear(), startMonth, 1);
    }
}
