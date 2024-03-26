package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restsofa.modelo.entities.Carpinteria;
import restsofa.modelo.entities.Pedido;
import restsofa.repository.CarpinteriaRepository;

@Service
public class CarpinteriaServiceImplMy8 implements CarpinteriaService {
	@Autowired
	private CarpinteriaRepository crepo;

	@Autowired
	private PedidoService pedidoService;
	
	
	
	@Override
	public Carpinteria insertOne(Carpinteria carpinteria) {

		Pedido pedido = pedidoService.buscarPedido(carpinteria.getPedido().getIdPedido());

		try {
			if (pedido != null)
				crepo.save(carpinteria);
			return carpinteria;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Carpinteria> buscarPorIdEstado(int idEstado) {
		
		return crepo.buscarPorEstado();
	}

	@Override
	public List<Carpinteria> buscarPorIdPedido(int idPedido) {
		// TODO Auto-generated method stub
		return crepo.buscarPorIdPedido(idPedido);
	}

	@Override
	public List<Carpinteria> buscarPorIdPedidoyEstado(int idPedido, int idEstado) {
		// TODO Auto-generated method stub
		return null;
	}

}
