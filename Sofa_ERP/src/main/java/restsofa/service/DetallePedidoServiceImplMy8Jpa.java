package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.DetallePedido;
import restsofa.repository.DetallePedidoRepository;

@Service

public class DetallePedidoServiceImplMy8Jpa implements DetallePedidoService{

	@Autowired
	DetallePedidoRepository dprepo;
	
	@Override
	public DetallePedido buscarDetPed(int idDePed) {
		return dprepo.findById(idDePed).orElse(null);
	}

	@Override
	public List<DetallePedido> buscarTodosDetPed() {
		return dprepo.findAll();
	}

	@Override
	public DetallePedido altaDetPed(DetallePedido detPed) {
		return dprepo.save(detPed);
	}

	@Override
	public DetallePedido modifDetPed(DetallePedido detPed) {
		try {
			return dprepo.save(detPed);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public boolean borrarDetPed(int idDePed) {
		try {
			if (buscarDetPed(idDePed) != null) {
				dprepo.deleteById(idDePed);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int restaurarMateriales(int idPedido) {
		
		DetallePedido detallePedido = 
		
		
		return 0;
	}

	@Override
	public DetallePedido buscarPorPedido(int idPedido) {
		// TODO Auto-generated method stub
		return dprepo.buscarPorPedido(idPedido);
	}

}
