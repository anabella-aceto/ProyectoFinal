package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Tarea;
import restsofa.repository.TareaRepository;

@Service

public class TareaServiceImplMy8Jpa implements TareaService {

	@Autowired
	private TareaRepository tarepo;

	@Override
	public Tarea buscarTarea(int idTarea) {
		return tarepo.findById(idTarea).orElse(null);
	}

	@Override
	public List<Tarea> buscarTodasTareas() {
		return tarepo.findAll();
	}

	@Override
	public Tarea altaTarea(Tarea tarea) {
		return tarepo.save(tarea);
	}

	@Override
	public Tarea modifTarea(Tarea tarea) {
		try {
			return tarepo.save(tarea);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean borrarTarea(int idTarea) {
		try {
			if (buscarTarea(idTarea) != null) {
				tarepo.deleteById(idTarea);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
