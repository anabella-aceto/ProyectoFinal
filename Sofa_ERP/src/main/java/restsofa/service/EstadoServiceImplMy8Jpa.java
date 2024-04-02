package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Estado;
import restsofa.repository.EstadoRepository;

@Service

public class EstadoServiceImplMy8Jpa implements EstadoService {

	@Autowired
	private EstadoRepository estrepo;

	@Override
	public Estado buscarEstado(int idEstado) {
		return estrepo.findById(idEstado).orElse(null);
	}

	@Override
	public List<Estado> buscarTodosEstado() {
		return estrepo.findAll();
	}

	@Override
	public Estado altaEstado(Estado estado) {
		return estrepo.save(estado);
	}

	@Override
	public Estado modifEstado(Estado estado) {
		try {
			return estrepo.save(estado);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarEstado(int idEstado) {
		try {
			if (buscarEstado(idEstado) != null) {
				estrepo.deleteById(idEstado);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Estado porDefecto(String nombre) {
		// TODO Auto-generated method stub
		return estrepo.establecerPorDefecto(nombre);
	}

}
