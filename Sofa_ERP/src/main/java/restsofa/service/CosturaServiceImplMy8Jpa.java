package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Costura;
import restsofa.repository.CosturaRepository;

@Service

public class CosturaServiceImplMy8Jpa implements CosturaService {

	@Autowired
	private CosturaRepository cosrepo;

	@Override
	public Costura buscarCostura(int idCostura) {
		return cosrepo.findById(idCostura).orElse(null);
	}

	@Override
	public List<Costura> buscarTodasCostura() {
		return cosrepo.findAll();
	}

	@Override
	public Costura altaCostura(Costura costura) {
		return cosrepo.save(costura);
	}

	@Override
	public Costura modifCostura(Costura costura) {
		try {
			return cosrepo.save(costura);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarCostura(int idCostura) {
		try {
			if (buscarCostura(idCostura) != null) {
				cosrepo.deleteById(idCostura);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
