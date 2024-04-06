package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.DetallePedido;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.repository.DetallePedidoRepository;

/**
 * Implementación de la interfaz DetallePedidoService que utiliza Spring Data
 * JPA.
 */
@Service
public class DetallePedidoServiceImplMy8Jpa implements DetallePedidoService {

	@Autowired
	private DetallePedidoRepository dprepo;

	@Autowired
	private SofaMaterialService sofaMaterialService;

	/**
	 * Método que busca un detalle de pedido por su identificador.
	 *
	 * @param idDePed El identificador único del detalle de pedido a buscar.
	 * @return El detalle de pedido encontrado, o null si no se encuentra.
	 */
	@Override
	public DetallePedido buscarDetPed(int idDePed) {
		return dprepo.findById(idDePed).orElse(null);
	}

	/**
	 * Método que lista todos los detalles de pedido.
	 *
	 * @return Una lista de todos los detalles de pedido.
	 */
	@Override
	public List<DetallePedido> buscarTodosDetPed() {
		return dprepo.findAll();
	}

	/**
	 * Método que permite crear un nuevo detalle de pedido.
	 *
	 * @param detPed El detalle de pedido a insertar.
	 * @return El detalle de pedido insertado.
	 */
	@Override
	public DetallePedido altaDetPed(DetallePedido detPed) {
		return dprepo.save(detPed);
	}

	/**
	 * Modifica un detalle de pedido existente.
	 *
	 * @param detPed El detalle de pedido a modificar.
	 * @return El detalle de pedido modificado, o null si ocurrió un error.
	 */
	@Override
	public DetallePedido modifDetPed(DetallePedido detPed) {
		try {
			return dprepo.save(detPed);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Elimina un detalle de pedido por su identificador.
	 *
	 * @param idDePed El identificador único del detalle de pedido a eliminar.
	 * @return true si el detalle de pedido fue eliminado correctamente, false de lo
	 *         contrario.
	 */
	@Override
	public boolean borrarDetPed(int idDePed) {
		try {
			if (buscarDetPed(idDePed) != null) {
				dprepo.deleteById(idDePed);
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
	 * Busca un detalle de pedido por el identificsdor del pedido asociado.
	 *
	 * @param idPedido El identificdador único del pedido asociado al detalle de
	 *                 pedido.
	 * @return El detalle de pedido encontrado, o null si no se encuentra.
	 */
	@Override
	public DetallePedido buscarPorPedido(int idPedido) {
		return dprepo.buscarPorPedido(idPedido);
	}
}
