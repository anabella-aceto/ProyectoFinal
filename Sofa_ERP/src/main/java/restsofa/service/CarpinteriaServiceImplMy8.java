package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restsofa.modelo.entities.Carpinteria;
import restsofa.modelo.entities.EstadoPedido;
import restsofa.modelo.entities.Pedido;
import restsofa.repository.CarpinteriaRepository;

@Service
public class CarpinteriaServiceImplMy8 implements CarpinteriaService {
	@Autowired
	private CarpinteriaRepository crepo;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EstadoPedidoService estadoPedidoService;
	
	//@Autowired
	//private SofaMaterialService sofaMaterialService;
	
	
	
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
	public List<Carpinteria> buscarPorEstado(int idEstado) {
		
		return crepo.buscarPorEstado(idEstado);
	}

	@Override
	public List<Carpinteria> buscarPorPedido(int idPedido) {
		
		return crepo.buscarPorPedido(idPedido);
	}

	
	@Override
	public boolean deleteOne(int idCarpinteria) {
		
		if(buscarUno(idCarpinteria)!=null) {
		crepo.deleteById(idCarpinteria);
		return true;
		}
		else 
			return false;
	}
	

	@Override
	public Carpinteria updateOne(Carpinteria carpinteria) {
		
		try {
			crepo.save(carpinteria);
				return carpinteria;				
		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Carpinteria buscarUno(int idCarpinteria) {
		// TODO Auto-generated method stub
		return crepo.findById(idCarpinteria).orElse(null);
	}
	
	
	//public void actualizarStock(int idPedido) {
		
		//Carpinteria carpinteria = (Carpinteria) buscarPorPedido(idPedido);
		
		//EstadoPedido estadoPedido = estadoPedidoService.buscarEstadoPedido(carpinteria.getEstadoPedido().getIdEstado());	
		
		
	//}

}
