package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Tapizado;
import restsofa.repository.TapizadoRepository;

@Service
public class TapizadoServiceImplMy8 implements TapizadoService{
	
	@Autowired
	private TapizadoRepository tapizadoRepository;

	@Override
	public List<Tapizado> listarTodos() {
		
		return tapizadoRepository.findAll();
	}

	@Override
	public Tapizado insertOne(Tapizado tapizado) {
		
		return tapizadoRepository.save(tapizado);
	}

	@Override
	public Tapizado updateOne(Tapizado tapizado) {
		try {
			if(buscarUno(tapizado.getIdTapizado()) != null) {
				tapizadoRepository.save(tapizado);
				return tapizado;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteOne(int idTapizado) {
		try {
			if(buscarUno(idTapizado) != null) {
				tapizadoRepository.deleteById(idTapizado);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Tapizado buscarUno(int idTapizado) {
		
		return tapizadoRepository.findById(idTapizado).orElse(null);
	}

	@Override
	public List<Tapizado> buscarPorPedido(int idPedido) {
		
		return tapizadoRepository.buscarPorPedido(idPedido);
	}

	@Override
	public List<Tapizado> buscarPorEstado(int idEstado) {
		
		return tapizadoRepository.buscarPorEstado(idEstado);
	}

}
