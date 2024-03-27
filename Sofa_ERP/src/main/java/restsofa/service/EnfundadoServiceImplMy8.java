package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Enfundado;
import restsofa.repository.EnfundadoRepository;

@Service
public class EnfundadoServiceImplMy8 implements EnfundadoService{
	
	@Autowired
	private EnfundadoRepository enfundadoRepository;

	@Override
	public List<Enfundado> listarTodos() {
		
		return enfundadoRepository.findAll();
	}

	@Override
	public Enfundado insertOne(Enfundado enfundado) {
		
		return enfundadoRepository.save(enfundado);
	}

	@Override
	public Enfundado updateOne(Enfundado enfundado) {
		try {
			if(buscarUno(enfundado.getIdEnfundado()) != null) {
				enfundadoRepository.save(enfundado);
				return enfundado;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteOne(int idEnfundado) {
		try {
			if(buscarUno(idEnfundado) != null) {
				enfundadoRepository.deleteById(idEnfundado);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Enfundado buscarUno(int idEnfundado) {
		
		return enfundadoRepository.findById(idEnfundado).orElse(null);
	}

	@Override
	public List<Enfundado> buscarPorPedido(int idPedido) {
		
		return enfundadoRepository.buscarPorIdPedido(idPedido);
	}

	@Override
	public List<Enfundado> buscarPorEstado(int idEstado) {
		// TODO Auto-generated method stub
		return enfundadoRepository.buscarPorEstado(idEstado);
	}

}
