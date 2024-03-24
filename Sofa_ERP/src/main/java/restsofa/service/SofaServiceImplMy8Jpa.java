package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Sofa;
import restsofa.repository.SofaRepository;

@Service

public class SofaServiceImplMy8Jpa implements SofaService {

	@Autowired
	private SofaRepository sofrepo;

	@Override
	public Sofa buscarSofa(int idSofa) {
		return sofrepo.findById(idSofa).orElse(null);
	}

	@Override
	public List<Sofa> buscarTodosSofas() {
		return sofrepo.findAll();
	}

	@Override
	public Sofa altaSofa(Sofa sofa) {
		return sofrepo.save(sofa);
	}

	@Override
	public Sofa modifSofa(Sofa sofa) {
		try {
			return sofrepo.save(sofa);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarSofa(int idSofa) {
		try {
			if (buscarSofa(idSofa) != null) {
				sofrepo.deleteById(idSofa);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
